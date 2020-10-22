package primo_test.primo_test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestLinkCleaner {
	public static void main(String[] args) {
		String text = readAllBytesJava7("c:\\Documents\\Temp\\view-source_https___www.youtube.com_watch_v=ihn-2-NBUtc.html");
		String nt = cleanHREF(text, " href='#'");
		nt = cleanSRC(nt, " src='#'");
		
		
		System.out.print(nt);
	}
	
	
	private static final String HTML_HREF_TAG_PATTERN = 	"\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";	
	private static final String HTML_SRC_TAG_PATTERN = 	"\\s*(?i)src\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";	
	
	public static String cleanURL(String targetText, String replaceBy) {
		
	    String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

	    String nText = targetText.replaceAll(regex, replaceBy);
		
		return nText;
	}

	
	public static String cleanHREF(String targetText, String replaceBy) {
		
	    String regex = HTML_HREF_TAG_PATTERN; //"href=\"([^\"]*)\"";

	    String nText = targetText.replaceAll(regex, replaceBy);
		
		return nText;
	}
	
	public static String cleanSRC(String targetText, String replaceBy) {
		
	    String regex = HTML_SRC_TAG_PATTERN;

	    String nText = targetText.replaceAll(regex, replaceBy);
		
		return nText;
	}
		
    private static String readAllBytesJava7(String filePath)
    {
        String content = "";
 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
 
        return content;
    }	
	
}
