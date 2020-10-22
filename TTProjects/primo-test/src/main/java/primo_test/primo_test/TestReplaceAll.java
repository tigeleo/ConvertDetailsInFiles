package primo_test.primo_test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestReplaceAll {

	@Test
	public void test() {
		String teststr = " asdkaskd {{EMAIL_CONTEXT}} sadlaskjdaskdlkasdlkas "; 
		String transstr=teststr.replaceAll("\\{\\{EMAIL_CONTEXT\\}\\}", "ppppp");
		System.out.println(transstr);
		assertNotSame(teststr, transstr);
		
		StringBuffer sb = new StringBuffer();
		transstr=teststr.replaceAll("\\{\\{EMAIL_CONTEXT\\}\\}", sb.toString());
		System.out.println(transstr);
		assertNotSame(teststr, transstr);
		
		transstr=teststr.replace("{{EMAIL_CONTEXT}}", "Pacific Ties: Recovering the Lives of Chinese Railroad Workers in North America: The Chinese and the Iron R\r\n" + 
				"oad: Building the Transcontinental Railroad. Stanford, CA: Stanford University Press, 2019. xiii + 539 pp. $90.00 (hardcover)\r\n" + 
				", ISBN 978-1-50-360829-0 ; $30.00 (paper), ISBN 978-1-50-360924-2 .");
		System.out.println(transstr);
		assertNotSame(teststr, transstr);
	}
	@Test
	public void test2() {
		String teststr = "                <tr style=\"background-color: #fff; text-decoration: none;\">\r\n" + 
				"                    <td style=\"padding: 15px 15px; position: relative;\">\r\n" + 
				"                        <span style=\"text-transform: uppercase; letter-spacing: .07em; font-weight: 600; font-size: .8em; color: #6d6d6d\">{{type}}</span>\r\n" + 
				"<br>\r\n" + 
				"                        <a href=\"{{permalink}}\" style=\"display: block; margin: 5px 0; text-decoration: none; color: #44707b; font-size: 1.4em; font-weight: 600; \">{{title}}</a>\r\n" + 
				"<br>\r\n" + 
				"                        <span style=\"color: #3a3a3a; font-weight: 400; font-size: .65rem;\">{{creator}}</span>\r\n" + 
				"                    </td>\r\n" + 
				"                </tr> "; 
		String transstr=teststr.replace("{{title}}", "The Loyal Republic: Traitors, Slaves, and the Remaking of Citizenship in Civil War America ( Chapel Hill: University of North Carolina Press, 2018, $34.95). Pp. 240. isbn 978 1 4696 3632 0 .");
		System.out.println(transstr);
		assertNotSame(teststr, transstr);
		
		transstr=teststr.replaceAll("{{creator}}", "");
		System.out.println(transstr);
		assertNotSame(teststr, transstr);
		
		transstr=teststr.replace("{{EMAIL_CONTEXT}}", "Pacific Ties: Recovering the Lives of Chinese Railroad Workers in North America: The Chinese and the Iron R\r\n" + 
				"oad: Building the Transcontinental Railroad. Stanford, CA: Stanford University Press, 2019. xiii + 539 pp. $90.00 (hardcover)\r\n" + 
				", ISBN 978-1-50-360829-0 ; $30.00 (paper), ISBN 978-1-50-360924-2 .");
		System.out.println(transstr);
		assertNotSame(teststr, transstr);
	}

}
