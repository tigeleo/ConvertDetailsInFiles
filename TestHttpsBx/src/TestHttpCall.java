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
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class TestHttpCall {
	final static Logger logger = Logger.getLogger(TestHttpCall.class);
	private static final String DEFAULT_FORM_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=utf-8";
	private static final String DEFAULT_XML_TEXT_CONTENT_TYPE = "text/xml; charset=utf-8";
	private static final String POST_CONTENT_TYPE = "Post Content Type";
	private static final String PUT_CONTENT_TYPE = "Put Content Type";
	private static final String ALMA_SERVICE = "alma_get_services_info";
	// HTTP Actions
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	public static final String HTTP_DELETE = "DELETE";
	public static final String HTTP_PUT = "PUT";
	private String postContentType = DEFAULT_FORM_CONTENT_TYPE;
	private String putContentType = DEFAULT_FORM_CONTENT_TYPE;
	private static int DEAULT_MAX_HTTP_CONNNECTIONS_PER_HOST = 10;
	private static int DEAULT_TIMEOUT = 160000;
	private String encoding = "UTF-8";
	private int retryCount = 1;
	private static Integer DISABLE_LINGER = new Integer(0); // do not keep connection a bit more alive for sending data.
	private int DEFAULT_STATUS_CODE = -1;
	private float timeoutFactor = 0.9f;

	volatile HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager()); // create
																							// a
																							// thread
																							// safe
																							// connection
																							// manager,
																							// and
																							// a
																							// new
																							// HttpClient

	public static void main(String[] args) {
		TestHttpCall thc = new TestHttpCall();
		try {
			thc.updateConfiguration();
			thc.executeService("xxx", "https://haifa.alma.exlibrisgroup.com/view/rest-dlf/collections?lang=eng&institution=972HAI_MAIN&expand=links_to_images");
		} catch (Exception e) {
			logger.error(e);
		}

	}

	public void updateConfiguration() throws Exception {
		retryCount = 10;
		int httpConnections = DEAULT_MAX_HTTP_CONNNECTIONS_PER_HOST;
		int iconnectionTimeout = DEAULT_TIMEOUT;

		this.postContentType = DEFAULT_FORM_CONTENT_TYPE;
		this.putContentType = DEFAULT_FORM_CONTENT_TYPE;

		MultiThreadedHttpConnectionManager conMan = (MultiThreadedHttpConnectionManager) client.getHttpConnectionManager();
		HttpConnectionManagerParams params = conMan.getParams();
		try {
			params.setDefaultMaxConnectionsPerHost(httpConnections);
			client.getParams().setConnectionManagerTimeout(iconnectionTimeout);
			client.getParams().setSoTimeout(iconnectionTimeout);
			logger.info("Ils connection [httpConnections:" + httpConnections + ",iconnectionTimeout:" + iconnectionTimeout + ",this.postContentType:" + this.postContentType
					+ ",this.putContentType:" + this.putContentType + "]");
		} catch (Throwable tr) {
			logger.error("Problem setting number of connections and timeout");
		}

	}
	public void executeService(String serviceName, String serviceURI) throws Exception{
		logger.info("executeService - begin:"+serviceURI);
		String res = null;
		String response = null;
		HttpMethod method = null;
		try {
			if(serviceURI == null){
				logger.error("Missing calling URI - please verify that there is a mapping for this service:'"+serviceName + "' ; adaptor:'" + serviceURI + "'");
				throw new Exception("STS_CALL_URI_PREPARE_ERROR");

			}
			method = getMethdod(serviceURI, serviceName);
			res = callILS(serviceName,serviceURI,method);
		    response = res;
		    logger.info("executeService - end:"+serviceName);

		} catch (Exception piex) {
			logger.error("Error calling ILS - original error:" + piex.getMessage());
			piex.printStackTrace();
			throw piex;
		} catch (Throwable tr) {
	    	String specificMessageError = "Error calling ILS - no status code:'";
	    	logger.error(specificMessageError,tr);
	    	throw tr;
		}finally{
			logger.info("response:"+response);
		}
	}
	
	private HttpMethod getMethdod(String callURI, String serviceName) {
		logger.info("getMethdod - begin");
		String action = HTTP_GET;
		action = action != null ?action : HTTP_GET;
		HttpMethod method = null;
		if (!ALMA_SERVICE.equals(serviceName)){
			try {
				URI uri = new URI(callURI,false,encoding);
				callURI= uri.getEscapedURI();
			}catch (Throwable tr) {
				logger.error("Problem creating or escaping URI for the following String:"+callURI);
				tr.printStackTrace();
				// we will try with the original and if we get an error it will be logged with event.
			}
		}
		if(HTTP_GET.equalsIgnoreCase(action)){
			method = new GetMethod(callURI);
		}else if(HTTP_POST.equalsIgnoreCase(action)){
			PostMethod post = new PostMethod(callURI);
			method = post;
		}else if(HTTP_DELETE.equalsIgnoreCase(action)){
				method = new DeleteMethod(callURI);
		}else if(HTTP_PUT.equalsIgnoreCase(action)){
			PutMethod put = new PutMethod(callURI);
			method = put;

		}else{
			method = new GetMethod(callURI);

		}
		 setTimeoutValues( method);
		 method.addRequestHeader("Accept-Encoding", "gzip,deflate");



		logger.info("getMethdod - finish");
		return method;


	}
	/**
	 * @param request
	 * @param method
	 */
	private void setTimeoutValues(HttpMethod method) {
		long ILSGTimeoutInMilliseconds=60000;
		Integer itimeOutFactor=new Integer(Math.round(ILSGTimeoutInMilliseconds*timeoutFactor));
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(retryCount, false));
		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, itimeOutFactor);
		method.getParams().setParameter(HttpMethodParams.HEAD_BODY_CHECK_TIMEOUT, itimeOutFactor);
		method.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, itimeOutFactor);
		 // set at client level
//		 method.getParams().setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT,
//				 new Integer(Math.round(request.getILSGTimeoutInMilliseconds()*timeoutFactor)));
		method.getParams().setParameter(HttpConnectionParams.SO_LINGER,DISABLE_LINGER);
	}


	private String callILS(String serviceName, String serviceURI, HttpMethod method)  {

		int ret = 0;
		String res = null;

		while (ret++ <= this.retryCount) {
			logger.info("Call of callILS:"+ret);
			int statusCode = DEFAULT_STATUS_CODE;
			try {
				// Execute the method.
				statusCode = client.executeMethod(method);
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("Method failed: " + method.getStatusLine());
					throw new HttpException("Method failed: " + method.getStatusLine());
				} else {
					res = handleResponse(method);
					logger.info("handleResponse:" + res.length());
					break;
				}
			} catch (HttpException e) {
				String specificMessageError = "Http exception when calling ILS: with status code:'";
				String callUri = getMethodPath(method, serviceURI);
				logger.error(specificMessageError+statusCode+"["+callUri+"]", e);
				//throw e;
			} catch (IOException e) {
				String specificMessageError = "IO error occured when calling ILS. with status code:'";
				statusCode = getStatusCode(method, statusCode);
				String callUri = getMethodPath(method, serviceURI);
				logger.error(specificMessageError+statusCode+"["+callUri+"]", e);
				//throw e;
			} catch (Throwable tr) {
				String specificMessageError = "Unexpected error occured when calling ILS. with status code:'";
				statusCode = getStatusCode(method, statusCode);
				String callUri = getMethodPath(method, serviceURI);
				logger.error(specificMessageError+statusCode+"["+callUri+"]", tr);
				//throw tr;
			} finally {
				// Release the connection.
				try {
					method.releaseConnection();
				} catch (Throwable tr) {

					logger.error("Error closing http connection", tr);
				}
			}
		}
		return res;
	}

	public String handleResponse(HttpMethod method) throws IOException, UnsupportedEncodingException {
		String res;
		Header contentEncodingHeader = method.getResponseHeader("Content-Encoding");
		if (contentEncodingHeader != null && contentEncodingHeader.getValue().equalsIgnoreCase("gzip")) {
			InputStream in = method.getResponseBodyAsStream();
			GZIPInputStream gzipStream = new GZIPInputStream(in);
			StringWriter writer = new StringWriter();
			IOUtil.copy(gzipStream, writer, encoding);
			res = writer.toString();
		} else {
			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data

			res = new String(responseBody, encoding);
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
			String callUri = method != null ? method.getURI().getURI() : "";
			return callUri;
		} catch (Throwable e) {// for some reason we get here null pointer from
								// the method.No handling necessary
			logger.warn("for some reason we get here null pointer from",e);
		}
		return serviceURI;
	}

	/**
	 * @param method
	 * @param statusCode
	 * @return
	 */
	private int getStatusCode(HttpMethod method, int statusCode) {
		if (statusCode != DEFAULT_STATUS_CODE) {
			return statusCode;
		}

		try {
			statusCode = method.getStatusCode();
		} catch (Throwable e) {// for some reason we get here null pointer from
								// the method.
			statusCode = DEFAULT_STATUS_CODE;
		}

		return statusCode;
	}

}
