package com.dev.goeuro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.dev.goeuro.utils.CSVWriterUtil;
import com.dev.goeuro.utils.ResultsWrapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

public class GoEuroTest {

	GoEuroWS web_service;
	ResultsWrapper results_wrapper;
	
	@Before
	public void setUp() throws Exception 
	{
		web_service = new GoEuroWS();
		results_wrapper=new ResultsWrapper();
	}

	@After
	public void tearDown() throws Exception 
	{
	}
/* Test case to verify whether the webservices returns the appropriate result in JSON*/
	@Test
	public void testWebService() throws Exception 
	{
		
		System.out.println("Testing Web Service - 2 Results");
		 String jsonResponse;
		jsonResponse = web_service.callGoEuroWS("Potsdam");
		//ObjectMapper mapper = new ObjectMapper();
		
		ResultsWrapper results_wrapper=new ResultsWrapper();
		//results_wrapper = mapper.readValue(jsonResponse, ResultsWrapper.class);
		System.out.println(jsonResponse);
		assertNotNull(results_wrapper.getResults(jsonResponse));
		assertEquals(2,results_wrapper.getResults(jsonResponse).size());		
	}
	
	@Test
	public void testWebServiceNoResult() throws Exception 
	{
		System.out.println("Testing Web Service - No Result");
		String jsonResponse;
		jsonResponse = web_service.callGoEuroWS("no result");
		

		assertNotNull(results_wrapper.getResults(jsonResponse));
		assertEquals(0,results_wrapper.getResults(jsonResponse).size());	
	}
	
	@Test (expected = NullPointerException.class)
	public void testWebServiceNull() throws Exception 
	{
		  web_service.callGoEuroWS(null);
	}
	
	@Test
	public void testJSONMap() throws JsonParseException, JsonMappingException, IOException 
	{

		System.out.println("Testing JSON Results");
		ResultsWrapper results_wrapper =new ResultsWrapper(); ;
		
        
		assertNotNull(results_wrapper.getResults(SAMPLE_JSON));
		assertEquals(2,results_wrapper.getResults(SAMPLE_JSON).size());

		
		assertNotNull(results_wrapper.getResults(SAMPLE_JSON_2));
		assertEquals(2,results_wrapper.getResults(SAMPLE_JSON_2).size());

	}
	/**
	 * Test case to check if JsonParseException is thrown when Malformed JSON is mapped to Location Class
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test (expected = JsonParseException.class)
	public void testJSONMapMalformed() throws JsonParseException, JsonMappingException, IOException 
	{
		System.out.println("Testing Malformed JSON Map");
	//	ObjectMapper mapper = new ObjectMapper();

		results_wrapper.getResults(SAMPLE_JSON_MALFORMED);
	}
	/**
	 * Test to check if UnrecognizedProperty Exception is thrown when one of the JSON array property is changed
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Test (expected = UnrecognizedPropertyException.class)
	public void testJSONMapIncorrectFields() throws JsonParseException, JsonMappingException, IOException 
	{
		System.out.println("Testing Incorrect Fields JSON Map");
		//ObjectMapper mapper = new ObjectMapper();

		//mapper.readValue(SAMPLE_JSON_INCORRECT_FIELDS, ResultsWrapper.class);
		results_wrapper.getResults(SAMPLE_JSON_INCORRECT_FIELDS);
	}
	
	@Test
	public void testCSVWriter() throws JsonParseException, JsonMappingException, IOException 
	{
		try
		{
		System.out.println("Testing CSV Writer");
		 
       	 
				CSVWriterUtil.csvWrite(results_wrapper.getResults(SAMPLE_JSON),"testcsv1.csv");	
		
		
		CSVWriterUtil.csvWrite(results_wrapper.getResults(SAMPLE_JSON_2),"testcsv2.csv");	
		
		CSVWriterUtil.csvWrite(null,"testcsvblank.csv");	
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) 
	{
		Result result = JUnitCore.runClasses(GoEuroTest.class);
		System.out.println(result.getFailures().size()+" Failures");
		for (Failure failure : result.getFailures()) 
		{
			
			System.out.println(failure.toString());
		}
	}
		

	
	private String SAMPLE_JSON = "[{"+
			"\"_id\" : 410978,"+
			"\"name\" : \"Potsdam, USA\","+
			"\"type\" : \"location\","+
				"\"geo_position\" : {"+
				"\"latitude\" : 44.66978,"+
				"\"longitude\" : -74.98131"+
				"}"+
			"}, {"+
			"\"_id\" : 377078,"+
			"\"name\" : \"Potsdam, Germany\","+
			"\"type\" : \"location\","+
			"\"geo_position\" : {"+
			"\"latitude\" : 52.39886,"+
			"\"longitude\" : 13.06566"+
			"}"+
			"}]"; 
	
	
	private String SAMPLE_JSON_2 = "[{"+
			"\"_id\" : 410978,"+
			"\"name\" : \"Potsdam, USA\","+
			"\"type\" : \"location\","+
				"\"geo_position\" : {"+
				"\"latitude\" : 44.66978,"+
				"\"longitude\" : -74.98131"+
				"}"+
			"}, {"+
			"\"_id\" : 377078,"+
			"\"name\" : \"Potsdam, Deutschland\","+
			"\"type\" : \"location\""+
			"}]";
	
	private String SAMPLE_JSON_INCORRECT_FIELDS = "[{"+
			"\"_id\" : 410978,"+
			"\"name\" : \"Potsdam, USA\","+
			"\"error\" : \"location\","+
			"}, {"+
			"\"_id\" : 377078,"+
			"\"error2\" : \"Potsdam, Deutschland\","+
			"\"type\" : \"location\","+
			"\"geo_position\" : {"+
			"\"latitude\" : 52.39886,"+
			"\"error3\" : 13.06566"+
			"}"+
			"}]";
	
	private String SAMPLE_JSON_MALFORMED = "[{"+
			"\"_id\" : 410978,"+
			"\"name\" : \"Potsdam, USA\","+
			"\"type\" : \"location\","+
			"}, {"+
			"\"_id\" : 377078,"+
			"\"name\" : \"Potsdam, Deutschland\","+
			"\"type\" : \"location\","+
			"\"geo_position\" : {";
}