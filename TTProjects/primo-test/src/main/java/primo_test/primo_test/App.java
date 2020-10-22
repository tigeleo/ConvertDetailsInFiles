package primo_test.primo_test;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Pattern pattern = Pattern.compile("\\d{4}");
        
        String dates="[1510 TO 2004]";
        String[] datesplits = dates.split("\\d{4}");
        
        System.out.println( datesplits );

        Matcher matcher = pattern.matcher(dates);
        // check all occurance
        
        while (matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(matcher.group());
        }
        
        String nfval= "%s |,| %s";
        String[] vals = new String[2];
        
        matcher = pattern.matcher(dates);
        for(int i=0;i<2&&matcher.find();i++){
        	vals[i]=matcher.group();
        }
        
        System.out.println(String.format(nfval, vals));
        
    }
}
