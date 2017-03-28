package traktTvProject.jsonParser;

import java.io.IOException;

import traktTvProject.dao.TraktMovieSummary;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class MovieSummaryParser {

	/**
	 * Not currently used as the rest of the Movie Summary codes, because of direct querying to LinkedMDG
	 * but left for as an example of easy expansion of program.
	 * 
	 * As a sidenote, this uses the old parsing code with switch case to navigate through the JsonParser object.
	 * Not a very efficient or elegant way of doing it, nor can it handle JSON Arrays as far as I can tell.
	 * 
	 * @param jsonStringToParse
	 * @return TraktMovieSummary object with all summary info for one movie
	 * @throws IOException
	 */
	public static TraktMovieSummary parse(String jsonStringToParse) throws IOException{

		JsonParser jp = JsonParseFactory.sendToParseFactory(jsonStringToParse);
		

		String title = null;
		String imdb_id = null;
		int year = 0;

		if(jp.nextToken() != JsonToken.START_OBJECT){
			throw new IOException("ugyldig json-streng oppdaget");
		}

		while(jp.nextToken() != JsonToken.END_OBJECT){
			jp.nextToken();

			String index =jp.getCurrentName();
			if(index == null) continue;

			switch(index){

			case "title":
				title = jp.getText();
				break;

			case "imdb_id":
				imdb_id = jp.getText();
				break;

			case "year":
				year = jp.getIntValue();
				break;
			}
		}
		return new TraktMovieSummary(title, year, imdb_id);
	}

}
