package primo_test.primo_test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestGson {

	@Test
	public void test() {
		  JsonParser parser = new JsonParser();
		  JsonElement jsonElement = parser.parse("{\"message\":\"Hi\",\"place\":{\"name\":\"World!\"}}");
		  JsonObject rootObject = jsonElement.getAsJsonObject();
		  String message = rootObject.get("message").getAsString(); // get property "message"
		  JsonObject childObject = rootObject.getAsJsonObject("place"); // get place object
		  String place = childObject.get("name").getAsString(); // get property "name"
		  System.out.println(message + " " + place); // print "Hi World!"*/
		  assertEquals("Not good", "Hi", message);
		  assertEquals("Not good", "World!", place);
	}

}
