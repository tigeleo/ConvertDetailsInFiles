package primo_test.primo_test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LinuxCMPUtils {
	public static String printTop() {
		String content = "";
		long pid = ProcessHandle.current().pid();
		System.out.println("Pid is: "+pid);
		try {
			Process process = Runtime.getRuntime().exec("top -p " +pid+" -n 1 -b > top.txt");
			content = new String ( Files.readAllBytes( Paths.get("top.txt") ) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	public static void printTop2() {
		long pid = ProcessHandle.current().pid();
		ProcessBuilder pb = new ProcessBuilder("top", "-l", "1");
		pb.redirectError();
		try {
		    Process p = pb.start();
		    InputStream is = p.getInputStream();
		    int value = -1;
		    while ((value = is.read()) != -1) {
		        System.out.print(((char)value));
		    }
		    int exitCode = p.waitFor();
		    System.out.println("Top exited with " + exitCode);
		} catch (IOException exp) {
		    exp.printStackTrace();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void printTop3() {
		long pid = ProcessHandle.current().pid();
		String content = "";
		System.out.println("Pid is: "+pid);
		try {
			Files.delete(Paths.get("top.txt"));
			Process process = Runtime.getRuntime().exec("./runtop.sh "+pid);
			Thread.sleep(1000);
			content = readLineByLineJava8("top.txt") ;
			System.out.println("p is: "+content);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static String readLineByLineJava8(String filePath) 
	{
	    StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
	    {
	        stream.forEach(s -> contentBuilder.append(s).append("\n"));
	    }
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}	
	
	public static void main(String[] args) {
		//String p=printTop();
		//System.out.println("p:" + p);
		//printTop2();
		printTop3();
	}
}
