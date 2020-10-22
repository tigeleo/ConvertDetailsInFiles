package primo_test.primo_test;

public class TestBooleanParsing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean testval=Boolean.parseBoolean("true");
		System.out.println("Test1 " + testval);
		testval=Boolean.parseBoolean("True");
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean("TruE");
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean("Yes");
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean("Y");
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean("false");
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean("N");
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean("XXXXXX");
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean("");
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean(null);
		System.out.println("Test2 " + testval);
		testval=Boolean.parseBoolean("1");
		System.out.println("Test2 " + testval);
	}

}
