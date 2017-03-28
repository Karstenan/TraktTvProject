package traktTvProject.jsonParser;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import traktTvProject.dao.UserMoviesWatched;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserMoviesWatchedParser {


	/**
	 * Parses a JSON array string, making UserMoviesWatched helperobjects and adding them to a list with help of a while loop.
	 *
	 * @param jsonStringToParse
	 * @return List<UserMoviesWatched> watchedList - A list of movies watched by a specific user.
	 * @throws IOException
	 */
	public static List<UserMoviesWatched> parse(String jsonStringToParse) throws IOException{

		/*Sends a JSON string to the JsonParseFactory to get a JsonParser object that we can work with.*/
		JsonParser jp = JsonParseFactory.sendToParseFactory(jsonStringToParse);

		/*A list to keep all the watched movie objects*/
		List<UserMoviesWatched> watchedList = new LinkedList<>();

		ObjectMapper mapper = new ObjectMapper();


		try {
			/*Makes a rootNode as a starting point so we can navigate the tree.
			 * This is a special case, as the entire JSON String is a JSON Array.
			 */
			JsonNode rootNode = mapper.readTree(jp);

			/*Because the top node is(I'm not entirely sure what) but something that is at the top of the
			 * array. And as such we can have the rootnode return an Iterator which makes it possible
			 * to iterate through the each small tree in the array.*/
			Iterator<JsonNode> ite = rootNode.iterator();

			/*Will iterate as long as the iteratator has a next movie to continue on.
			 * generates and adds a new movie for each iteration of the array and adds them to the watchedList.*/
			while (ite.hasNext()) {
				JsonNode jn = ite.next();

				String imdb_id = jn.get("imdb_id").asText();
				String title = jn.get("title").asText();
				int plays = jn.get("plays").asInt();

				watchedList.add(new UserMoviesWatched(imdb_id, title, plays));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return watchedList;
	}
}
