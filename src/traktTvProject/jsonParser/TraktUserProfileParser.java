package traktTvProject.jsonParser;

import java.io.IOException;


import traktTvProject.dao.TraktUserProfile;
import traktTvProject.dao.TraktUserProfileStats;
import traktTvProject.dao.TraktUserProfileStatsMovies;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TraktUserProfileParser {




	/**
	 * Will parse a userProfile JSON string with the help of the Jackson library.
	 * Will also call parseUserStats() to parse the sub-JSON object containing this data.
	 * 
	 * @param jsonStringToParse
	 * @return finshed TraktUserProfile object including two sub-objects, TraktUserProfileStats 
	 *		   and TraktUserProfileStatsMovies, for one user profile.
	 * @throws IOException
	 */
	public static TraktUserProfile parse(String jsonStringToParse) throws IOException{

		/*Sends a JSON string to the JsonParseFactory to get a JsonParser object that we can work with.*/
		JsonParser jp = JsonParseFactory.sendToParseFactory(jsonStringToParse);

		
		/*ObjectMapper needed to map the node Tree in the JsonParser object.*/
		ObjectMapper mapper = new ObjectMapper();

		/*Datafields needed to generate the new TraktUserProfile object.*/
		String username = null;
		String gender = null;
		int age = 0;
		TraktUserProfileStats stats = null;


		try {
			/*Reads the JsonParse object as a tree and sets the top node as the Rootnode so we can use this to navigate from.*/
			JsonNode rootNode = mapper.readTree(jp);

			/*Gets the relevant data for the variables as their respective datatype*/
			username = rootNode.path("username").asText();
			gender = rootNode.path("gender").asText();
			age = rootNode.path("age").asInt();

			/*Creates a new node that can be used as rootNode for navigating the sub-Json Stats Object
			 * Calls the parseUserStats and sends the new node as a parameter.
			 * And gets a TraktUserProfileStats object in return for the stats field.*/
			JsonNode statsNode = rootNode.path("stats");
			stats = parseUserStats(statsNode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Generates and returns a new TraktUserProfile object.*/
		return new TraktUserProfile(username, gender, age, stats);
	}


	/**
	 *  Will parse a userProfileStats JSON string with the help of the Jackson library.
	 * Will also call parseUserMovieStats() to parse the sub-JSON object containing this data.
	 * Takes statsNode as a paramter so it can be used as a rootnode for navigating in
	 * this part of the tree.
	 * 
	 * @param statsNode
	 * @return TraktUserProfileStats object with friends and moviesstats object for one user
	 */
	private static TraktUserProfileStats parseUserStats(JsonNode statsNode) {

		int friends = 0;
		TraktUserProfileStatsMovies movies = null;

		try{
			friends = statsNode.path("friends").asInt();

			/*Generates a new node for the MovieStats tree.*/
			JsonNode movieStatsNode = statsNode.path("movies");
			movies = parseUserMoviesStats(movieStatsNode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Generates and returns a new TraktUserProfileStats object.*/
		return new TraktUserProfileStats(friends, movies);
	}


	/**
	 * Will parse a userProfileMovieStats JSON string with the help of the Jackson library.
	 * Takes movieStatsNode as a parameter so it can be used as a rootnode for navigating in
	 * this part of the tree.
	 * Works the same as parseUserStats() essentially.
	 * 
	 * @param movieStatsNode
	 * @return TraktUserProfileStatsMovies object with watched and unique watched for one user
	 */
	private static TraktUserProfileStatsMovies parseUserMoviesStats(JsonNode movieStatsNode) {

		int watched = 0;
		int watched_unique = 0;

		try {

			watched = movieStatsNode.path("watched").asInt();
			watched_unique = movieStatsNode.path("watched_unique").asInt();

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*Generates and returns a new TraktUserProfileStatsMovies object.*/
		return new TraktUserProfileStatsMovies(watched, watched_unique);
	}


}
