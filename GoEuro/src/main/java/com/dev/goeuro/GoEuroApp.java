package com.dev.goeuro;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev.goeuro.beans.Location;
import com.dev.goeuro.exception.ServiceException;
import com.dev.goeuro.utils.CSVWriterUtil;
import com.dev.goeuro.utils.ResultsWrapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class GoEuroApp {
	private final static String CSVFILENAME = "results.csv";
	private static final Logger logger =LogManager.getLogger(GoEuroApp.class.getName());
	
	public static void main(String[] args) {
	
		if(args.length==0)
		{
			logger.error("Missing Parameter. Requires CITYNAME");
			System.out.println(" Missing Paremeter : City Name Required. Usage :Java -jar GoEuro.jar <CITY NAME>");
		}
		else if(args.length>1)
		{
			logger.error("The program accepts exactly one input argument");
			
			return;
		}
		else
		{
			
		String name=args[0];
		logger.info("***** Starting Go Euro Test *****");
		String jsonResponse;
		try
		{
			GoEuroWS web_service= new GoEuroWS();
			jsonResponse= web_service.callGoEuroWS(name);
			ResultsWrapper results_wrapper= new ResultsWrapper();
			List<Location> results=results_wrapper.getResults(jsonResponse);
			
			System.out.println(results.size()+" "+"results");
			CSVWriterUtil.csvWrite(results, CSVFILENAME);
		}
		catch (ServiceException serviceException) {
			logger.error("CallGoEuroWS: Error calling webservice. " + serviceException.getMessage());
		} catch (JsonParseException jsonParseException) {
			logger.error("getResults: exception parsing the json");
		} catch (JsonMappingException jsonMappingException) {
			logger.error("getResults: exception mapping the json into bean");
		} catch (IOException ioexception) {
			logger.error("getResults: json mapper I/O exception");
		}
		}
	}

}
