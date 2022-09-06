package service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelperFunctions {
	public static String arrayListToJsonArray(ArrayList arrayList)
			throws JsonGenerationException, JsonMappingException, IOException {
		final StringWriter stringWriter = new StringWriter();
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(stringWriter, arrayList);
		String jsonArray = stringWriter.toString();
		stringWriter.close();
		return jsonArray;
	}
}
