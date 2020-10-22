package primo_test.ilsadaptors;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadCollectionBuildRecodsForPipe {
	 static final String outputEncoding = "UTF-8";
	 
	public static void main(String[] args) {
		String filepath=args[0];
		String filename=args[1];
		System.out.println("Filename:"+filename);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(new File(filepath+filename));

			
            XPathFactory xFactory = XPathFactory.newInstance();
            XPath xPath = xFactory.newXPath();

            XPathExpression xExpress = xPath.compile("/collection/record");
            NodeList nodes = (NodeList) xExpress.evaluate(doc, XPathConstants.NODESET);
            
            System.out.println("Found " + nodes.getLength() + " note nodes");

            for (int index = 0; index < nodes.getLength(); index++) {
                Node node = nodes.item(index);
                Document childDoc = db.newDocument();
                childDoc.adoptNode(node);
                childDoc.appendChild(node);
                System.out.println(toString(childDoc,filepath,index));
            }            
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    public static String toString(Document doc, String directoryPath, int index) {

        String sValue = null;

        ByteArrayOutputStream baos = null;
        OutputStreamWriter osw = null;

        try {
            baos = new ByteArrayOutputStream();
            osw = new OutputStreamWriter(baos);
            
            String a1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
            		"<OAI-PMH xmlns=\"http://www.openarchives.org/OAI/2.0/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.openarchives.org/OAI/2.0/ http://www.openarchives.org/OAI/2.0/OAI-PMH.xsd\">\r\n" + 
            		"<ListRecords>\r\n"; 

            String a2="</ListRecords>\r\n" + 
            		"</OAI-PMH>";
            

            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.METHOD, "xml");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource domSource = new DOMSource(doc);
            StreamResult sr = new StreamResult(osw);
            tf.transform(domSource, sr);

            osw.flush();
            baos.flush();
            sValue = new String(baos.toByteArray());
            
            // 
            String val=a1+sValue.substring(55)+a2;
             
           //Get the file reference
             Path path = Paths.get(directoryPath+index+".xml");
              
             //Use try-with-resource to get auto-closeable writer instance
             try (BufferedWriter writer = Files.newBufferedWriter(path))
             {
                 writer.write(val);
             }
             
            
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            try {
                osw.close();
            } catch (Exception exp) {
            }
            try {
                baos.close();
            } catch (Exception exp) {
            }
        }
        return sValue;
    }
    
	private static void printlnCommon(Node n) {
		System.out.print(" nodeName=\"" + n.getNodeName() + "\"");

	    String val = n.getNamespaceURI();
	    if (val != null) {
	    	System.out.print(" uri=\"" + val + "\"");
	    }

	    val = n.getPrefix();

	    if (val != null) {
	    	System.out.print(" pre=\"" + val + "\"");
	    }

	    val = n.getLocalName();
	    if (val != null) {
	    	System.out.print(" local=\"" + val + "\"");
	    }

	    val = n.getNodeValue();
	    if (val != null) {
	    	System.out.print(" nodeValue=");
	        if (val.trim().equals("")) {
	            // Whitespace
	        	System.out.print("[WS]");
	        }
	        else {
	        	System.out.print("\"" + n.getNodeValue() + "\"");
	        }
	    }
	    System.out.println();
	}
	
	
	/**
	  * Return the text that a node contains. This routine:
	  * <ul>
	  * <li>Ignores comments and processing instructions.
	  * <li>Concatenates TEXT nodes, CDATA nodes, and the results of
	  *     recursively processing EntityRef nodes.
	  * <li>Ignores any element nodes in the sublist.
	  *     (Other possible options are to recurse into element 
	  *      sublists or throw an exception.)
	  * </ul>
	  * @param    node  a  DOM node
	  * @return   a String representing its contents
	  */
	public static String getText(Node node) {
	    StringBuffer result = new StringBuffer();
	    if (! node.hasChildNodes()) return "";

	    NodeList list = node.getChildNodes();
	    list=list.item(0).getChildNodes();
	    for (int i=0; i < list.getLength(); i++) {
	        Node subnode = list.item(i);
	        if (subnode.getNodeType() == Node.TEXT_NODE) {
	            result.append(subnode.getNodeValue());
	        }
	        else if (subnode.getNodeType() == Node.CDATA_SECTION_NODE) {
	            result.append(subnode.getNodeValue());
	        }
	        else if (subnode.getNodeType() == Node.ENTITY_REFERENCE_NODE) {
	            // Recurse into the subtree for text
	            // (and ignore comments)
	            result.append(getText(subnode));
	        }
	    }

	    return result.toString();
	}
}
