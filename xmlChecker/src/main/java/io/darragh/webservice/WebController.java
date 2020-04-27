package io.darragh.webservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

@Controller
public class WebController {
	
	//using Spring Boot to set up a controller for our webservice
	
    @PostMapping("/sendxml")      //in Postman use POST method to send xml to http://localhost:8080/sendxml              
    @ResponseBody /*saveXml takes the xml POST as a String */
    public String saveXml(@RequestBody String xml) {

   
      try {   

    	  //Extract Info class contains the methods to process our xml data
    	  ExtractInfo exInfo = new ExtractInfo();
    	
    	  	//saveXml method returns our status code as a string which can be posted to our localhost server
    	  return exInfo.processXml(xml);

		//if there is an error it will be caught here
		  } catch (Exception e) {
		      // instance document is invalid!
			  System.out.println("error : " + e);
			return "error, check console";
		  }
    	
      
    }

}
