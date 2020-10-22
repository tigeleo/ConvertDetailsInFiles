import java.util.Locale;

public class TestLocales {

	public static void main(String[] args) {
		
		PrintLocales("zh_CN");		
		PrintLocales("zh_TW");		
	}


	private static void PrintLocales(String languageContryCodes) {
		String countryCode = "";
		String languageCode = "";
		try {

				languageCode = languageContryCodes.substring(0, 2);
				if (languageContryCodes.length() == 5) {
					countryCode = languageContryCodes.substring(3, 5);
				}


				Locale locale= new Locale(languageCode,countryCode);
				
				locale=setLocale(locale.getLanguage(),locale.getCountry());
				
				String localeLang =  "";


				localeLang = locale!=null?locale.getISO3Language():"";
				
				System.out.println("locale: " + locale);
				System.out.println("localeLang: " + localeLang);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}

	
	public static Locale setLocale( String language,	String country) {
			Locale locale = new Locale(language);
			locale = new Locale(language, country);
			return locale;
	}
}
