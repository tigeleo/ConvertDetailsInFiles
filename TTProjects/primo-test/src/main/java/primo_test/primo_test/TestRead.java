package primo_test.primo_test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TestRead{
	private Table<String,String,String> translations;
	
	public TestRead() {
		// TODO Auto-generated constructor stub
		translations = HashBasedTable.create();
	}
	
	public  String getTranslation(String view, String lang) {
			
			if (!translations.contains(view, lang)) {
				setTranslations(view,lang);
			}
			String response = translations.get(view, lang);
			return response;
	}
	
	
	private synchronized void setTranslations(String view, String locale) {
			System.out.println("PUT:" + view + " and " +locale);
			translations.put(view, locale, view+locale);

	}
}