package primo_test.primo_test;
import java.util.Random;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;


public class TestAsychObjects {
	
	public static int readNumber=0;
	
	public static void main(String[] args) {
		TestRead tr = new TestRead();
		for(int i=0; i<1000; i++) {
			TestThread tt = new TestThread(tr);
			tt.start();
		}
	}
	

	
		
	

}



