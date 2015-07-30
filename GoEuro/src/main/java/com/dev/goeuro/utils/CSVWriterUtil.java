package com.dev.goeuro.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.dev.goeuro.beans.Location;

public class CSVWriterUtil {
	
	private static final Logger logger =LogManager.getLogger(CSVWriterUtil.class.getName());
	
	/**
	 * This method generates a CSV file with List of location objects from JSON response
	 * 
	 * @param Location a list of objects mapping the JSON  returned
	 * 
	 * @param file_name the file name (including the path)
	 */
	
	public static void csvWrite(List<Location> results,String file_name)
	{
		BufferedWriter writer = null;
		try 
		{
			//Even if there are no results we want an empty file to be created.
			File file = new File(file_name);  
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));

			if(results != null)
			{
				for(Location result: results)
				{
					String[] entries = result.toCSV();
					for(int i=0; i < entries.length; i++)
					{
						String value = entries[i];
						
						if(value != null)
						{
							value.replace("\"","\"\"");
							
							//If the value contains a ',' then we need to enclose it with "
							if(value.contains(","))
							{
								writer.write("\"");
							}
							writer.write(value);
							
							if(value.contains(","))
							{
								writer.write("\"");
							}
						}
						else
						{
							writer.write(" ");
						}
						
						if( i!= entries.length-1)
						{
							writer.write(", ");
						}
						
					}
					writer.newLine();
				}  

			} 
			logger.info("***** CSV file " + file_name + " successfully generated into the jar execution directory *****");
			
			
			
	    } catch (UnsupportedEncodingException unsupportedEncodingException) {
		  logger.error("UnsupportedEncodingException: it is not possible to close the file");
	    } catch (FileNotFoundException fileNotFoundException) {
		  logger.error("FileNotFoundException: it is not possible to close the file");
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		   e.printStackTrace();
	    } finally {
		  try {
			writer.close();
		  } catch (IOException ioexception) {
			logger.error("IOException: it is not possible to close the file");
		}
	}

	
}
}
