package primo_test.primo_test;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("X1:"+ "0123456789".substring(0,10));
		System.out.println("X1:"+ "0123456789".substring(0,0));
		System.out.println("X1:"+ "0123456789".substring(0,1));
		System.out.println("X1:"+ "0123456789".substring(0,9));
		System.out.println("X1:"+ "0123456789".substring(0,"0123456789".length()));
	}

	
	
	
	@Test
	public void testCreatorParse() {
		String teststr = "Daisetz Teitaro Suzuki 1870-1966.$$QSuzuki, Daisetz Teitaro, 1870-1966."; 
		String res=teststr.split("\\$\\$Q")[0];

		assertEquals("Daisetz Teitaro Suzuki 1870-1966.", res);
		
		teststr = "Daisetz Teitaro Suzuki 1870-1966."; 
		res=teststr.split("\\$\\$Q")[0];

		assertEquals("Daisetz Teitaro Suzuki 1870-1966.", res);
		
		
		teststr = "Daiset$ Teitaro $uzu$$....,,,,,,####----ki $870-1966."; 
		res=teststr.split("\\$\\$Q")[0];

		assertEquals(teststr, res);
		
		teststr = "Daiset$ Teitaro $uzu$$..$$q,,,,,,####----ki $870-1966."; 
		res=teststr.split("\\$\\$Q")[0];

		assertEquals(teststr, res);
		
		teststr = "Daiset$ Teitaro $uzu$$..$$Q,,,,,,####----ki $870-1966."; 
		res=teststr.split("\\$\\$Q")[0];

		assertEquals("Daiset$ Teitaro $uzu$$..", res);
	}	
}
