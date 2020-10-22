package primo_test.primo_test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.LinkedList;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEncoding {

//	@Test
//	public void testExconign() {
//		
//		String ie="any,contains,ø";
//		String ch="any,contains,Ã¸";
//		
//		Charset utf8charset = Charset.forName("UTF-8");
//		Charset utf16charset = Charset.forName("UTF-16");
//		Charset iso88591charset = Charset.forName("ISO-8859-1");
//
//		ByteBuffer inputBuffer = ByteBuffer.wrap(ie.getBytes(iso88591charset));
//
//		// decode UTF-8
//		CharBuffer data = utf8charset.decode(inputBuffer);
//
//		// encode ISO-8559-1
//		ByteBuffer outputBuffer = iso88591charset.encode(data);
//		byte[] outputData = outputBuffer.array();	
//		
//		System.out.println(new String(outputData));
//
//	}
	
	
	@Test
	public void testExconign2() {
		
		String ie="any,contains,ø";
		String ch="any,contains,Ã¸";
		
		
		String res=ie;
		if(isEncoded(res)) {
			System.out.println("Is encoded: " + res);
			try {
				res=new String(res.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(res);
		
		res=ch;
		if(isEncoded(res)) {
			System.out.println("Is encoded: " + res);
			
			try {
				res=new String(res.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(res);
		
		
	}

	@Test
	public void testParcingFacets() {
		String url="https://primo-demo.hosted.exlibrisgroup.com/primo_library/libweb/action/search.do?frbg=&&fn=search&indx=1&dscnt=0&tb=t&vid=NORTH&mode=Basic&ct=search&srt=rank&tab=all_resources&dum=true&vl(freeText0)=waggle dance&dstmp=1532867120142&ctSearch=true&ctType=citation&ctSeedId=TN_sciversesciencedirect_elsevierS0003-3472(14)00236-X&fctN=facet_citing&fctV=-1557294017809780985&fctN=facet_citing&fctV=-1557294017809780985&fctN=citing&fctV=-1557294017809780985";
		
		
		// parse facets
		int fn_next = 6;
		int fv_next = 6;
		int fn_ind = -1;
		int fv_ind = -1;

		while ((fn_ind = url.indexOf("&fctN=facet_", fn_next) + 12) > fn_next && (fv_ind = url.indexOf("&fctV=", fv_next) + 6) > fv_next) {
			int fn_end = url.indexOf("&", fn_ind);
			int fv_end = url.indexOf("&", fv_ind);
			if(fn_end<fn_ind) fn_end=url.length(); 
			if(fv_end<fv_ind) fv_end=url.length(); 
			String fn_val = url.substring(fn_ind, fn_end);
			String fv_val = url.substring(fv_ind, fv_end);

			System.out.println("Get Facet Name " + fn_val + " , facet value: "+ fv_val);
			fn_next = fn_end;
			fv_next = fv_end;
		}		
	}
	
	@Test
	public void testPathes() {
		String s = "http://il-primoqa13.corp.exlibrisgroup.com:1701/primo_library/libweb/action/search.do?frbg=&&fn=search&indx=1&vl(1UI0)=contains&dscnt=0&scp.scps=primo_central_multiple_fe&tb=t&vid=BC_VIEW&mode=Basic&ct=search&srt=rank&tab=pc&dum=true&vl(freeText0)=nuclear%20N%C3%83%C2%A4sholm&dstmp=1594715511960&initializeIndex=true&isMobile=false";
		String s1 = "http%3a%2f%2fil-primoqa13.corp.exlibrisgroup.com%3a1701%2fprimo_library%2flibweb%2faction%2fsearch.do%3ffrbg%3d%26amp%3b%26amp%3bfn%3dsearch%26amp%3bindx%3d1%26amp%3bvl(1UI0)%3dcontains%26amp%3bdscnt%3d0%26amp%3bscp.scps%3dprimo_central_multiple_fe%26amp%3btb%3dt%26amp%3bvid%3dBC_VIEW%26amp%3bmode%3dBasic%26amp%3bct%3dsearch%26amp%3bsrt%3drank%26amp%3btab%3dpc%26amp%3bdum%3dtrue%26amp%3bvl(freeText0)%3dnuclear%2520N%25C3%2583%25C2%25A4sholm%26amp%3bdstmp%3d1594715511960%26amp%3binitializeIndex%3dtrue%26isMobile%3dfalse" ;
		String res="";
		try {
			res=URLDecoder.decode(s1, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res=URLDecoder.decode(res, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res= new String(res.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		
	}
	
	@Test
	public void testPathes2() {
		String s = "http://il-primoqa13.corp.exlibrisgroup.com:1701/primo_library/libweb/action/search.do?frbg=&&fn=search&indx=1&vl(1UI0)=contains&dscnt=0&scp.scps=primo_central_multiple_fe&tb=t&vid=BC_VIEW&mode=Basic&ct=search&srt=rank&tab=pc&dum=true&vl(freeText0)=nuclear%20N%C3%83%C2%A4sholm&dstmp=1594715511960&initializeIndex=true&isMobile=false";
		String s1 = "http://il-primoqa13.corp.exlibrisgroup.com:1701/primo_library/libweb/action/search.do?frbg=&&fn=search&indx=1&vl(1UI0)=contains&dscnt=0&scp.scps=primo_central_multiple_fe&tb=t&vid=BC_VIEW&mode=Basic&ct=search&srt=rank&tab=pc&dum=true&vl(freeText0)=nuclear%20N%C3%A4sholm&dstmp=1594794565284&initializeIndex=true&isMobile=false" ;
		String res="";
		System.out.println("s1:"+s1);
		try {
			res=URLDecoder.decode(s1, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res=URLDecoder.decode(res, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res= new String(res.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		
	}
	
	
	@Test
	public void testPathes3() {
		String s = "http://il-primoqa13.corp.exlibrisgroup.com:1701/primo_library/libweb/action/search.do?frbg=&&fn=search&indx=1&vl(1UI0)=contains&dscnt=0&scp.scps=primo_central_multiple_fe&tb=t&vid=BC_VIEW&mode=Basic&ct=search&srt=rank&tab=pc&dum=true&vl(freeText0)=nuclear%20N%C3%A4sholm&dstmp=1594794565284&initializeIndex=true&isMobile=false" ;
		System.out.println("target:"+s);
		String s1 = "http://il-primoqa13.corp.exlibrisgroup.com:1701/primo_library/libweb/action/search.do?frbg=&&fn=search&indx=1&vl(1UI0)=contains&dscnt=0&scp.scps=primo_central_multiple_fe&tb=t&vid=BC_VIEW&mode=Basic&ct=search&srt=rank&tab=pc&dum=true&vl(freeText0)=nuclear%20N%C3%83%C2%A4sholm&dstmp=1594796306316&initializeIndex=true&isMobile=false&afterPDS=true" ;
		String res="";
		System.out.println("s1:"+s1);
		try {
			res=URLDecoder.decode(s1, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res=URLDecoder.decode(res, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res= new String(res.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		
	}
	
	@Test
	public void testPathes4() {
		String s = "http://il-primoqa13.corp.exlibrisgroup.com:1701/primo_library/libweb/action/search.do?frbg=&&fn=search&indx=1&vl(1UI0)=contains&dscnt=0&scp.scps=primo_central_multiple_fe&tb=t&vid=BC_VIEW&mode=Basic&ct=search&srt=rank&tab=pc&dum=true&vl(freeText0)=nuclear%20N%C3%A4sholm&dstmp=1594794565284&initializeIndex=true&isMobile=false" ;
		System.out.println("target:"+s);
		String s1 = "http%3a%2f%2fil-primoqa13.corp.exlibrisgroup.com%3a1701%2fprimo_library%2flibweb%2faction%2fsearch.do%3ffrbg%3d%26amp%3b%26amp%3bfn%3dsearch%26amp%3bindx%3d1%26amp%3bvl(1UI0)%3dcontains%26amp%3bdscnt%3d0%26amp%3bscp.scps%3dprimo_central_multiple_fe%26amp%3btb%3dt%26amp%3bvid%3dBC_VIEW%26amp%3bmode%3dBasic%26amp%3bct%3dsearch%26amp%3bsrt%3drank%26amp%3btab%3dpc%26amp%3bdum%3dtrue%26amp%3bvl(freeText0)%3dnuclear%2520N%25C3%2583%25C2%25A4sholm%26amp%3bdstmp%3d1594820109180%26amp%3binitializeIndex%3dtrue%26isMobile%3dfalse" ;
		String res="";
		System.out.println("s1:"+s1);
		try {
			res=URLDecoder.decode(s1, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res=URLDecoder.decode(res, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res= new String(res.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		
	}
	
	
	@Test
	public void testUrlTransformation() {
		String s="http://il-primoqa13.corp.exlibrisgroup.com:1701/primo_library/libweb/action/search.do?frbg=&amp;&amp;fn=search&amp;indx=1&amp;vl(1UI0)=contains&amp;dscnt=0&amp;scp.scps=primo_central_multiple_fe&amp;tb=t&amp;vid=BC_VIEW&amp;mode=Basic&amp;ct=search&amp;srt=rank&amp;tab=pc&amp;dum=true&amp;vl(freeText0)=nuclear Näsholm&amp;dstmp=1594715511960&amp;initializeIndex=true&isMobile=false";
		System.out.println("Transforamtion:"+s);
		String res=null;
		try {
			res=URLEncoder.encode(s, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res=URLDecoder.decode(res, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);

		try {
			res=new String(res.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		try {
			res=URLDecoder.decode(res, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res:"+res);
		System.out.println("End Transforamtion");
		
	}
	
	
//	@Test
//	public void testExconign3() {
//		
//		String ie="any,contains,ø";
//		String ch="any,contains,Ã¸";
//		
//		
//		String res=ie;
//		if(Charset.forName( "ISO-8859-1" ).newEncoder().canEncode(res)) {
//			System.out.println("Is encoded: " + res);
//			Charset utf8charset = Charset.forName("UTF-8");
//			Charset utf16charset = Charset.forName("UTF-16");
//			Charset iso88591charset = Charset.forName("ISO-8859-1");
//			
//			ByteBuffer inputBuffer = ByteBuffer.wrap(res.getBytes(iso88591charset));
//			
//			// decode UTF-8
//			CharBuffer data = utf8charset.decode(inputBuffer);
//			
//			// encode ISO-8559-1
//			ByteBuffer outputBuffer = iso88591charset.encode(data);
//			byte[] outputData = outputBuffer.array();	
//			
//			res=new String(outputData);
//		}
//		System.out.println(res);
//		
//		res=ch;
//		if(Charset.forName( "ISO-8859-1" ).newEncoder().canEncode(res)) {
//			System.out.println("Is encoded: " + res);
//			Charset utf8charset = Charset.forName("UTF-8");
//			Charset utf16charset = Charset.forName("UTF-16");
//			Charset iso88591charset = Charset.forName("ISO-8859-1");
//			
//			ByteBuffer inputBuffer = ByteBuffer.wrap(res.getBytes(iso88591charset));
//			
//			// decode UTF-8
//			CharBuffer data = utf8charset.decode(inputBuffer);
//			
//			// encode ISO-8559-1
//			ByteBuffer outputBuffer = iso88591charset.encode(data);
//			byte[] outputData = outputBuffer.array();	
//			
//			res=new String(outputData);
//		}
//		System.out.println(res);
//		
//		
//	}
//	
	public boolean isEncoded(String text){

	    //Charset charset = Charset.forName("ISO-8859-1");
	    //Charset utf8charset = Charset.forName("UTF-8");
	    String checked;
		try {
			checked = new String(text.getBytes("ISO-8859-1"),"UTF-8");
		    checked=new String(checked.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
	    return checked.equals(text);

	}
	
//	@Test
//	public void testUtf8() {
//		String ie="any,contains,ø";
//		String ch="any,contains,Ã¸";
//		
//		Charset utf8charset = Charset.forName("UTF-8");
//		Charset utf16charset = Charset.forName("UTF-16");
//		Charset iso88591charset = Charset.forName("ISO-8859-1");
//		
//	}


	
}
