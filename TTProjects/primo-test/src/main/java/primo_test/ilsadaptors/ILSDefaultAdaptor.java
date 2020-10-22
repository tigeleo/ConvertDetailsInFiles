package primo_test.ilsadaptors;

import static primo_test.ilsadaptors.GatewayConstants.HTTP_ACTION_PARAM_NAME;
import static primo_test.ilsadaptors.GatewayConstants.HTTP_DELETE;
import static primo_test.ilsadaptors.GatewayConstants.HTTP_GET;
import static primo_test.ilsadaptors.GatewayConstants.HTTP_POST;
import static primo_test.ilsadaptors.GatewayConstants.HTTP_POST_INPUT_XML_FILE_PARAM_NAME;
import static primo_test.ilsadaptors.GatewayConstants.HTTP_PUT;
import static primo_test.ilsadaptors.GatewayConstants.POST_INPUT_XML_FILE_REQ_PARAM_NAME;
import static primo_test.ilsadaptors.GatewayConstants.STS_HTTP_CALL_ILS_FAILURE;
import static primo_test.ilsadaptors.GatewayConstants.STS_IO_ILS_FAILURE;
import static primo_test.ilsadaptors.GatewayConstants.STS_CALL_URI_PREPARE_ERROR;
import static primo_test.ilsadaptors.GatewayConstants.STS_CALL_ILS_FAILURE;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.exlibris.common.utils.StringUtils;
import com.exlibris.primo.infra.utils.IOUtil;
import com.exlibris.primo.logger.PrimoLogger;


public class ILSDefaultAdaptor implements ILSAdaptor {
	private static PrimoLogger logger = (PrimoLogger) PrimoLogger.getLogger(ILSDefaultAdaptor.class);

	private static final String DEFAULT_FORM_CONTENT_TYPE ="application/x-www-form-urlencoded; charset=utf-8";
	private static final String DEFAULT_XML_TEXT_CONTENT_TYPE ="text/xml; charset=utf-8";
	private static final String POST_CONTENT_TYPE = "Post Content Type";
	private static final String PUT_CONTENT_TYPE = "Put Content Type";
	private String postContentType = DEFAULT_FORM_CONTENT_TYPE;
	private String putContentType = DEFAULT_FORM_CONTENT_TYPE;
	private String adaptorId;
	private int retryCount = 1;
	private float timeoutFactor = 0.9f;
	private String encoding = "UTF-8";
	private boolean configured;
	private int DEFAULT_STATUS_CODE = -1;
	private static Integer DISABLE_LINGER = new Integer(0); // do not keep connection a bit more alive for sending data.
	private static int DEAULT_MAX_HTTP_CONNNECTIONS_PER_HOST = 10;
	volatile HttpClient client=new HttpClient(new MultiThreadedHttpConnectionManager()); //create a thread safe connection manager, and a new HttpClient

	private ILSGAdaptorConfig adaptorConfig;



	public void setAdaptorId(String adaptorId) {
		this.adaptorId = adaptorId;
	}

	/**
	 * @return
	 * @uml.property  name="adaptorId"
	 */
	public String getAdaptorId() {
		return adaptorId;
	}

	public void updateConfiguration(ILSGAdaptorConfig adaptorConfig)
			throws Exception {
		this.adaptorConfig = adaptorConfig;
		this.retryCount = 10;

		String httpConnectionsStr = "10";
		int httpConnections = DEAULT_MAX_HTTP_CONNNECTIONS_PER_HOST;
		try {
			httpConnections = Integer.parseInt(httpConnectionsStr);
		} catch (Throwable tr) {
			// Nothing to do here
		}

		String contentType = adaptorConfig.getConfigMap().get(POST_CONTENT_TYPE);
		this.postContentType = StringUtils.isEmptyString(contentType)? DEFAULT_FORM_CONTENT_TYPE : contentType;

		contentType = adaptorConfig.getConfigMap().get(PUT_CONTENT_TYPE);
		this.putContentType = StringUtils.isEmptyString(contentType)? DEFAULT_FORM_CONTENT_TYPE : contentType;


		MultiThreadedHttpConnectionManager conMan =  (MultiThreadedHttpConnectionManager)client.getHttpConnectionManager();
		HttpConnectionManagerParams params =   conMan.getParams();
		try {
				params.setDefaultMaxConnectionsPerHost(httpConnections);
				client.getParams().setConnectionManagerTimeout(5000);
		} catch (Throwable tr) {
			logger.error("Problem setting number of connections",tr);
		}

		configured = true;

	}





	public boolean isAdaptorConfigured() {
		return configured;
	}




	
	public ILSAdaptorResponse executeService(String serviceName, String serviceURI) throws PrimoILSGException{
		logger.debug("executeService - begin");
		String res = null;
		ILSAdaptorResponse response = null;
		HttpMethod method = null;
		try {
			if(serviceURI == null){
				logger.error("Missing calling URI - please verify that there is a mapping for this service:'"+serviceName + "' ; adaptor:'" + adaptorId + "'");
				throw new PrimoILSGException(STS_CALL_URI_PREPARE_ERROR);

			}
			method = getMethdod(serviceURI);
			res = callILS(serviceName,serviceURI,method);

			logger.debug("executeService - end");
		    return response;

		} catch (PrimoILSGException piex) {
			logger.error("Error calling ILS - original error:" + piex.getMessage(),piex);
			throw piex;
		} catch (Throwable tr) {
	    	String specificMessageError = "Error calling ILS - no status code:'";
	    	PrimoILSGException piex =logError(serviceName, 0, tr, specificMessageError,serviceURI,STS_CALL_ILS_FAILURE);
	    	throw piex;
		}finally{

		}
	}
	/**
	 * @param serviceName
	 * @param serviceURI
	 * @param method
	 * @param request
	 * @return
	 */
	private String callILS(String serviceName, String serviceURI, HttpMethod method) {
		String res = null;

		int statusCode = DEFAULT_STATUS_CODE;
	    try {
		      // Execute the method.
		      statusCode = client.executeMethod(method);
		      if (statusCode != HttpStatus.SC_OK) {
		        logger.error("Method failed: " + method.getStatusLine());
		        throw new HttpException("Method failed: " + method.getStatusLine());
		      }
		      else{
		    	  System.out.println("Method started OK: " + serviceURI);
		    	  res = handleResponse(method);
		    	  System.out.println(res);
		      }
		    } catch (HttpException e) {
		    	String specificMessageError = "Http exception when calling ILS: with status code:'";
		    	String callUri = getMethodPath(method,serviceURI);
		    	PrimoILSGException piex = logError(serviceName, statusCode, e, specificMessageError,callUri,STS_HTTP_CALL_ILS_FAILURE);
		    	throw piex;
		    } catch (IOException e) {
		    	String specificMessageError = "IO error occured when calling ILS. with status code:'";
		    	statusCode = getStatusCode(method, statusCode);
		    	String callUri = getMethodPath(method,serviceURI);
		    	PrimoILSGException piex = logError(serviceName, statusCode, e, specificMessageError,callUri,STS_IO_ILS_FAILURE);
		    	throw piex;
		    } catch (Throwable tr) {
		    	String specificMessageError = "Unexpected error occured when calling ILS. with status code:'";
		    	statusCode = getStatusCode(method, statusCode);
		    	String callUri = getMethodPath(method,serviceURI);
		    	PrimoILSGException piex = logError(serviceName, statusCode, tr, specificMessageError,callUri,STS_HTTP_CALL_ILS_FAILURE);
				throw piex;
		    } finally {
		      // Release the connection.
		    	try {
		    		method.releaseConnection();
				} catch (Throwable tr) {
					logger.error("Error closing http connection",tr);
				}
		    }
		return res;
	}

	public String handleResponse(HttpMethod method) throws IOException,
			UnsupportedEncodingException {
		String res;
		Header contentEncodingHeader = method.getResponseHeader("Content-Encoding");
		  if (contentEncodingHeader != null && contentEncodingHeader.getValue().equalsIgnoreCase("gzip")){
			  InputStream in =  method.getResponseBodyAsStream();
			  GZIPInputStream gzipStream =   new GZIPInputStream(in);
			  StringWriter writer = new StringWriter();
			  IOUtil.copy(gzipStream, writer, encoding);
			  res = writer.toString();
		  }else{
			  // Read the response body.
			  byte[] responseBody = method.getResponseBody();

			  // Deal with the response.
			  // Use caution: ensure correct character encoding and is not binary data

			  res=new String(responseBody,encoding);
		  }
		return res;
	}

	/**
	 * @param method
	 * @param serviceURI
	 * @return
	 */
	private String getMethodPath(HttpMethod method, String serviceURI) {
		try {
			String callUri = method != null ? method.getURI().getURI():"";
			return callUri;
		} catch (Throwable e) {// for some reason we get here null pointer from the method.No handling necessary
		}
		return serviceURI;
	}

	/**
	 * @param method
	 * @param statusCode
	 * @return
	 */
	private int getStatusCode(HttpMethod method, int statusCode) {
		if(statusCode != DEFAULT_STATUS_CODE){
			return statusCode;
		}

		try {
			statusCode = method.getStatusCode();
		} catch (Throwable e) {// for some reason we get here null pointer from the method.
			statusCode = DEFAULT_STATUS_CODE;
		}

		return statusCode;
	}

	/**
	 * @param serviceName
	 * @param statusCode
	 * @param e
	 * @param specificMessageError
	 * @param callUri
	 * @param errorCode
	 */
	private PrimoILSGException logError(String serviceName, int statusCode, Throwable tr,
			String specificMessageError, String callUri, String errorCode) {
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append(specificMessageError).append(statusCode).append("' ; error:").append(tr.getMessage()).append(System.getProperty("line.separator"));
		errorMessage.append("serviceName:'").append(serviceName).append("' ; method:'").append(callUri).append("'");
		logger.error(errorMessage.toString(),tr);
		PrimoILSGException piex = new PrimoILSGException(errorCode,tr);
		return piex;
	}

	private HttpMethod getMethdod(String callURI) {
		logger.debug("getMethdod - begin");
		String action = "alma_get_services_info";
		action = action != null ?action : HTTP_GET;
		HttpMethod method = null;
		try {
			URI uri = new URI(callURI,false,encoding);
			callURI= uri.getEscapedURI();
		} catch (Throwable tr) {
			logger.warn("Problem creating or escaping URI for the following String:"+callURI,tr);
			// we will try with the original and if we get an error it will be logged with event.
		}

		method = new GetMethod(callURI);

		 setTimeoutValues( method);
		 method.addRequestHeader("Accept-Encoding", "gzip,deflate");



		 logger.debug("getMethdod - finish");
		return method;


	}

	/**
	 * @param request
	 * @param method
	 */
	private void setTimeoutValues( HttpMethod method) {
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,	new DefaultHttpMethodRetryHandler(retryCount, false));
		 method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,	new Integer(Math.round(getILSGTimeoutInMilliseconds()*timeoutFactor)));
		 method.getParams().setParameter(HttpMethodParams.HEAD_BODY_CHECK_TIMEOUT,	 new Integer(Math.round(getILSGTimeoutInMilliseconds()*timeoutFactor)));
		 method.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT,	 new Integer(Math.round(getILSGTimeoutInMilliseconds()*timeoutFactor)));
		 method.getParams().setParameter(HttpConnectionParams.SO_LINGER,DISABLE_LINGER);

	}

	private float getILSGTimeoutInMilliseconds() {
		// TODO Auto-generated method stub
		return 1000;
	}

	/**
	 * @param request
	 * @param put
	 */
	private void prepareInputXML(ILSGRequest request, PutMethod put,String contentType) {
		String inputXML = (String)request.getAttribute(POST_INPUT_XML_FILE_REQ_PARAM_NAME);
		if(inputXML != null){
			put.addRequestHeader("Connection", "close");
			put.addRequestHeader("Content-type", contentType);
			String httpParamName = (String)request.getAttribute(HTTP_POST_INPUT_XML_FILE_PARAM_NAME);
			String content = null;
			if("NO_PARAM_NAME".equals(httpParamName)){
				content = inputXML;
			}else{
				content = new StringBuilder(httpParamName).append("=").append(inputXML).toString();	
			}
			
			try {
				StringRequestEntity reqEntity = new StringRequestEntity(content,contentType,"UTF-8");
				put.setRequestEntity(reqEntity);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				logger.error("Error occured",e);
			}
		}
	}

	/**
	 * @param request
	 * @param post
	 * @param contentType
	 */
	private void prepareInputXML(ILSGRequest request, PostMethod post, String contentType) {
		String inputXML = (String)request.getAttribute(POST_INPUT_XML_FILE_REQ_PARAM_NAME);
		if(inputXML != null){
			post.addRequestHeader("Connection", "close");

//				post.addRequestHeader("Content-length", String.valueOf(inputXML.length()));
			post.addRequestHeader("Content-type", contentType);
			String httpParamName = (String)request.getAttribute(HTTP_POST_INPUT_XML_FILE_PARAM_NAME);
			if("NO_PARAM_NAME".equals(httpParamName)){
				try {
					StringRequestEntity reqEntity = new StringRequestEntity(inputXML,contentType,"UTF-8");
					post.setRequestEntity(reqEntity);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					logger.error("Error occured",e);
				}
			}else{
				NameValuePair[] data = new NameValuePair[]{new NameValuePair(httpParamName, inputXML)};
				post.setRequestBody(data);
			}
			//post.setRequestBody(inputXML);
		}
	}

	public ILSGAdaptorConfig getAdaptorConfig() {
		return adaptorConfig;
	}

	public String displayConfiguration(){
		StringBuilder configuration = new StringBuilder();
		configuration.append("adaptor Id:'").append(adaptorId).append("'; ")
			.append("is configured:'").append(isAdaptorConfigured()).append("'; ")
			.append("class:'").append(getClass().getName()).append("'; ")
			.append("retry count:'").append(retryCount).append("'")
			.append("POST Content Type:'").append(postContentType).append("'")
			.append("PUT Content Type:'").append(putContentType).append("'");
		return configuration.toString();

	}



}
