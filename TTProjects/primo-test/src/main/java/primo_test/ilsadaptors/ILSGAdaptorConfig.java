package primo_test.ilsadaptors;

import java.util.List;
import java.util.Map;


public interface ILSGAdaptorConfig {
	public ClassLoader getClassLoader();
	public Map<String, String> getConfigMap();
//	public List<ILSTemplateConfiguration> getDbServiceConfInfo();
	public Object getAttribute(String attributeName);
	public boolean addAttribute(String attributeName,Object attributeValue);
}
