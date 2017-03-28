package traktTvProject.jsonParser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

public class JsonParseFactory {
	

	/**
	 * Takes a JSON string as parameter, makes a JsonParser object and returns it so the Jackson library can be used
	 * to gather data from the JSON string.
	 * @param input
	 * @return parser - A JsonParser object containing a JSON String.
	 * @throws Exception
	 */
	private static JsonParser parseFactory(String input) throws Exception{

		String jsonUnparsed = input;

		JsonFactory jsonFactory = new JsonFactory();
		JsonParser parser;
		parser = jsonFactory.createParser(jsonUnparsed);

		return parser;
	}
	
	
	public static JsonParser sendToParseFactory(String jsonStringToParse) {
		
		JsonParser jp = null;
		try {
			jp = parseFactory(jsonStringToParse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jp;	
	}


}
