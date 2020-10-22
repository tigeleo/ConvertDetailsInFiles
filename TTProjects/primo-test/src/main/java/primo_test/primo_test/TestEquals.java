package primo_test.primo_test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestEquals {

	@Test
	void test() {
		
		String org = "";
		String instNameByLang = null;
		if(!org.equals(instNameByLang)){
			assertTrue("It ok", !org.equals(instNameByLang));
		}else {
			fail("Not supported");
			
		}
		
	}

}
