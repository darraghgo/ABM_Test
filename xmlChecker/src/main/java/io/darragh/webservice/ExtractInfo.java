package io.darragh.webservice;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class ExtractInfo {
	



	
	//this method converts our string to a document for our xml parser
	 public static Document convertStringToXMLDocument(String xmlString)
	    {
	        //Parser that produces DOM object trees from XML content
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	        //API to obtain DOM Document instance
	        DocumentBuilder builder = null;
	        try
	        {
	            //Create DocumentBuilder with default configuration
	            builder = factory.newDocumentBuilder();

	            //Parse the content to Document object
	            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

	            //if the xml is not structured correctly the doc will return a null object
	            if(doc == null) {
	            	return null;
	            }
	            return doc;
	        }
	        catch (Exception e)
	        {
	            return null;
	        }

	    }
	                 
	 	//here we take the xml string and extract the data from the elements
	    public static String processXml(String xml) {


	        try {

	        		//variables used to keep track of the different requirements
	        		int commandNum = 0; 
	        		int siteNum = 0;
	        		int validXml = 0;
	        	  
	        		
	        		//create a document object which is our xml string after the parser
	            Document doc = convertStringToXMLDocument( xml );
	          
	            //here the Attribute from the declaration element is extracted and assigned to the command variable
	            String command = doc.getElementsByTagName("Declaration").item(0).getAttributes().getNamedItem("Command").getNodeValue();
	           
	            //check if the command variable matches as is required
	            System.out.println("command = " + command);
	            if(command.matches("DEFAULT")) {
	            	commandNum = 0;
	            	System.out.println("commandNum = " + commandNum);
	            }else {
	             	commandNum = -1;
	            	System.out.println("commandNum = " + commandNum);
	            }
	            
	            
	            //here the data from SiteID element is extracted
	            String  siteID = doc.getElementsByTagName("SiteID").item(0).getTextContent();
	            
	            //here we check if siteID matches to DUB
	            System.out.println("siteID = " + siteID);
	            if(siteID.matches("DUB")) {
	            	siteNum = 0;
	            	System.out.println("siteNum = " + siteNum);
	            }else {
	            	siteNum = -2;
	            	System.out.println("siteNum = " + siteNum);
	            }
	            
	            
	            //if doc is not null ie the xml schema is structured correctly then valid XML is printed to the console, this is for internal checking only
	            if(doc != null) {
	            	validXml = 0;
	            	System.out.println("validXml = " + validXml);
	            }
	            
	            
	            //this is where the actual status code responses are made

	            //if the declaration command is not set to default then -1 is returned if it is correct then the siteID is checked
	            //if the siteID is incorrect -2 is returned
	            //if those conditions are met and the doc is not null then 0 is returned which indicates that everything is correct
	        if(commandNum != 0) {
	        	return "-1";
	        }else if(siteNum != 0) {
	        	return "-2";
	        }else {
	        	return "0";
	        }
	         
	        	//if the doc fails then an exception is thrown which will explain the syntax error in the console
	        } catch (Exception e) {
	            // instance document is invalid!
	        	System.out.println("Error" + e);
	            return "Invalid XML";
	        }


	    }
	    


}
