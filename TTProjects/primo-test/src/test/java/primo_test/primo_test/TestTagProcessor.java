package primo_test.primo_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.text.*;
import org.junit.jupiter.api.Test;

class TestTagProcessor {

	@Test
	void test() {
		String test = "A;B;C;D";
		String val=removeDoubles(test,";");
		assertEquals(test,val );
		
		test="1,2,3,4,5,5,6,,7";
		String esp="1,2,3,4,5,6,,7";
		val=removeDoubles(test,";");
		assertEquals(test,val);
		
		test="1;5;;5;1";
		esp="1;5;";
		val=removeDoubles(test,";");
		assertEquals(esp, val);
		
		test="1;1;1;1;1;1;1;;1;1";
		esp="1;";
		val=removeDoubles(test,";");
		assertEquals(esp, val);
		
		
		test=";Cited &lt;b&gt;2&lt;/b&gt; times in  &lt;Web of Science&lt;;";
		esp=";Cited &lt;b&gt;2&lt;/b&gt; times in  &lt;Web of Science&lt;;";
		val=removeDoubles(test,";");
		assertEquals(esp, val);
		
		test="Cited &lt;b&gt;2&lt;/b&gt; times in  &lt;Web of Science&lt;";
		esp="Cited &lt;b&gt;2&lt;/b&gt; times in  &lt;Web of Science&lt;";
		val=removeDoubles(test,";");
		assertEquals(esp, val);
		
		test=";;Cited &lt;b&gt;2&lt;/b&gt;;;;; times in  &lt;Web of Science";
		esp=";Cited &lt;b&gt;2&lt;/b&gt;; times in  &lt;Web of Science";
		val=removeDoubles(test,";");
		assertEquals(esp, val);
		test=";;Cited &lt;b&gt;2&lt;/b&gt;;;;; times in  &lt;Web of Science";
		esp=";Cited &lt;b&gt;2&lt;/b&gt;; times in  &lt;Web of Science";
		val=removeDoubles(test,";");
		assertEquals(esp, val);
		
		
		test="Cited &lt;&lt;b&gt;&gt;2&lt;&lt;/b&gt;&gt; times in  &lt;&lt;Web of Science&lt;&lt;";
		esp="Cited &lt;&lt;b&gt;&gt;2&lt;&lt;/b&gt;&gt; times in  &lt;&lt;Web of Science&lt;&lt;";
		val=removeDoubles(test,";");
		assertEquals(esp, val);

	}
	
		
	private static String removeDoubles(String value, String delim) {
		value = StringEscapeUtils.unescapeXml(value);
		String[] vals=value.split(delim);
		ArrayList<String> valwd=new ArrayList<String>();
		for(String val: vals) {
			if(!valwd.contains(val))	valwd.add(val);
		}
		if(value.endsWith(delim)) valwd.add("");
		return StringEscapeUtils.escapeXml11(valwd.stream().collect(Collectors.joining(delim)));
		
	}
}
