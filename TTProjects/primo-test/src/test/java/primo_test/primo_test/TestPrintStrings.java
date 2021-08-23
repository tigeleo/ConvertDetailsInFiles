package primo_test.primo_test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

class TestPrintStrings {
	private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	
	@Test
	void test() {
		String testString="報";
		
		
		System.out.println(testString+":"+toHex(testString) );
	}

	@Test
	void test1() {
		String testString="中国 社会 科学院 文学 硏 究 所";
		
		
		System.out.println(testString+":"+toHex(testString) );
	}
	
	
	public String toHex(String arg) {
		  return String.format("%x", new BigInteger(1, arg.getBytes(UTF8_CHARSET)));
	}
}
