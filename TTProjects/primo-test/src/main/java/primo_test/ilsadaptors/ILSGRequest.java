package primo_test.ilsadaptors;

import java.util.Map;



public interface ILSGRequest {


	public String getParameter(String parameterName);

	public Object getAttribute(String parameterName);

	public void setAttribute(String name,Object value);

	public Map<String,String> getParameterMap();

	public Map<String,Object> getAttributeMap();

	public float getILSGTimeout();

	public long getILSGTimeoutInMilliseconds();

	public int getILSGSlowThreshhold();

	public int getILSGChunkSize();


	public String getPatronId();


	public String getAdaptorId();

	public String getTemplateId();


	public String getServiceName();

}
