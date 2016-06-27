package com.shitot.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    public static String getJson(Object object) throws IOException {
	ObjectMapper mapper = new ObjectMapper();

	return mapper.writeValueAsString(object);
    }

    public static Object getObgect(String jsonString, String className)
	    throws JsonParseException, JsonMappingException, ClassNotFoundException, IOException {
	 return new ObjectMapper().readValue(jsonString,
	 Class.forName(className));
    }

    public static String getISO8601StringForDate(Date date) {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	return dateFormat.format(date);
    }

}
