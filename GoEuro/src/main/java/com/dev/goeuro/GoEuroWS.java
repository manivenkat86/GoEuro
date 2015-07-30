package com.dev.goeuro;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.dev.goeuro.exception.ServiceException;
//import com.dev.goeuro.utils.ResultsWrapper;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class GoEuroWS 
{
	  private static final String URI_NAME =  "https://api.goeuro.com/api/v2/position/suggest/en/";
	  private static final Logger logger =LogManager.getLogger(GoEuroWS.class.getName());
	  Client client=null;
	  public GoEuroWS()
	  {
		 client=ClientBuilder.newBuilder().register(JacksonFeature.class).build();
		
		 // JacksonFeature jackson = new JacksonFeature();
	      //client.register(jackson);
	    
	  }
	
	/**
	 * This method calls the API at the following address
	 * "https://api.goeuro.com/api/v2/position/suggest/en/"
	 * returning a string of JSON response
	 * 
	 * @param name - city name that is passed as a argument to
	 *  (e.g java -jar GoEuroTest.jar "CITY_Name" --> name= STRING)
	 *  
	 * @return A string represents all the elements of the json array 
	 *  returned by the service        
	 *  
	 * @throws ServiceException this exception happens when for some
	 *         reasons the service response is different from 200 (OK)
	 */
public  String callGoEuroWS(String name) throws ServiceException{
			
	logger.info("***** Calling the webservice from this URL : " + URI_NAME + " *****");
	logger.info("***** City Name: '" + name + "' *****");
		final WebTarget documentTarget = client.target(URI_NAME).path(name);
		 Response jsonResponse =documentTarget.request(MediaType.APPLICATION_JSON).get();

		if (jsonResponse.getStatus() == 200) {
			 String jsonString =jsonResponse.readEntity(String.class);
			 logger.info("***** Json results found: " + jsonString + " *****");
			return jsonString;
		} else {
			throw new ServiceException("Response status : " + jsonResponse.getStatus());
		}
	}
	
    
}
