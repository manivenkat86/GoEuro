package com.dev.goeuro.utils;

import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;

import com.dev.goeuro.beans.Location;
/* import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
   import com.fasterxml.jackson.annotation.JsonProperty; */
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//@JsonIgnoreProperties(ignoreUnknown=true)
public class ResultsWrapper {
	
	/**
	 * This method will return a list with Location objects that maps JSON to POJO
	 * Location class  contains getters and setter to map the JSON objects to JAVA objects
	 * 
	 * @param jsonString - JSON response as string
	 * @return List<Location> list of type Location which is Java bean mapped to JSON response
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	public  List<Location> getResults(String jsonString)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		List<Location> results = objectMapper.readValue(
				jsonString,
				objectMapper.getTypeFactory().constructCollectionType(
						List.class, Location.class));
		
		return results;
	}
}
