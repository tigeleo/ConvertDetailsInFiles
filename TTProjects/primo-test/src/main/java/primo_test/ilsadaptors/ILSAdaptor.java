package primo_test.ilsadaptors;


/**
 * @author  mosheh
 */
public interface ILSAdaptor extends AdaptorInterface {

	public void updateConfiguration(ILSGAdaptorConfig adaptorConfig) throws Exception;
	public ILSAdaptorResponse executeService(String serviceName,String serviceURI)throws PrimoILSGException;
	public boolean isAdaptorConfigured();
//	public Collection<ILSService> mapService(Object serviceName,Object serviceParameters);
	/**
	 * @uml.property  name="adaptorId"
	 */
	public String getAdaptorId();
	/**
	 * @param adaptorId
	 * @uml.property  name="adaptorId"
	 */
	public void setAdaptorId(String adaptorId);
////	public Object mergeResults(Collection results);
//	public void setAdaptorConfig(ILSGAdaptorConfig adaptorConfig);
	public ILSGAdaptorConfig getAdaptorConfig();

	public String displayConfiguration();
}
