package com.plethora.product.catalogue.custom.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author rajeevsingh
 *
 */
public class JSONUtils {

	//Generic Type Safe Method
	static public <T> T covertFromJsonToObject(String json, Class<T> var) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, var);//Convert Json into object of Specific Type
	}

	public static String covertFromObjectToJson(Object obj) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

}
