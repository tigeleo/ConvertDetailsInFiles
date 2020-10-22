package primo_test.primo_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;

public class TestEmailSender {

	@Test
	public void test() {
		String[] rcpt_to = {"LIB.IT.Alerts@unsw.edu.au", "james.wright@zhbluzern.ch",""};
		InternetAddress[] rec = getAddressArray(rcpt_to);
		assertEquals(2, rec.length);
	}
	
	
	private InternetAddress[] getAddressArray(String[] recipients){
		
		if ((recipients ==null) || (recipients.length == 0)){
			
			return null;
		}
		else{
			
			List<InternetAddress> addrList = new ArrayList<InternetAddress>();
			
			for(int i=0; i < recipients.length; i++) {
				try{	
					addrList.add(new InternetAddress(recipients[i]));
				}
				catch (AddressException e){
					System.out.println("EmailSender: Illigal recipient address: ["+recipients[i]+"]" + e.getMessage());
				}
			}
			
			return (InternetAddress[])addrList.toArray(new InternetAddress[]{}); 
		}
	}

}
