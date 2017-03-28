/**
 * 
 */
package main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import traktTvProject.dao.TraktMovieSummary;
import traktTvProject.dao.TraktUserProfile;
import traktTvProject.dao.UserMoviesWatched;
import traktTvProject.gui.TraktGui;
import traktTvProject.io.TraktApiCalls;


/**
 * @author karst_000
 *
 */
public class Main {

	static List<UserMoviesWatched> userMoviesWatchedList = new LinkedList<>();



	/*
	 *Temporary hardcoded APIkey and format to make it easier to use the program for several users.
	 *Should be possible to change for a new user in a finished product.
	 *For now there is no reason to not hardcode the format as json is the only format available from trakt, but in this way easily changeable
	 *and adaptable as the program grows, would need gui components to enter API, format etc.
	 */
	public final static String APIKey = "####"; //removed APIkey from the public github repo.
	public final static String format = "json";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*Starts the gui and the program.
		 * Probably not good practice to put helper methods here according to standards
		 * but this is how it is now and it works well enough.
		 * 
		 * See the comments for the two buttons in TraktGui for the start of the program process.
		 */
		TraktGui.main(args);

	}

	/**
	 * Gets a Trakt User Profile for a single user from Trakt.tv through the rest of the program.
	 * @param APIKey
	 * @param format
	 * @param user
	 * @return userProfile - a userprofile helper object with relevant data.
	 */
	public static TraktUserProfile getTraktUserProfile (String APIKey, String format, String user){

		TraktUserProfile userProfile = null;

		try {
			userProfile = TraktApiCalls.callTraktUserProfile(APIKey, format, user);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return userProfile;
	}

	/**
	 *  Gets a list of movies watched by a single user from the rest of the program.
	 * @param APIKey
	 * @param format
	 * @param user
	 * @return userMovieWatchedList - list of of all movies seen by one user.
	 */
	public static List<UserMoviesWatched> getUserMoviesWatchedListFromTrakt (String APIKey, String format, String user){
		try {
			userMoviesWatchedList = TraktApiCalls.callTraktUserMoviesWatched(APIKey, format, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userMoviesWatchedList;		
	}

	/**
	 * Gets the Trakt movie summary for a single movie from Trakt.tv through the rest of the program
	 * 
	 * Not used after adding LinkedMDB directly to the project, but left as an example of easy expansion for 
	 * further functionality if needed.
	 * 
	 * @param APIKey
	 * @param format
	 * @param imdb_id
	 * @return movieSummary object for one movie.
	 */
	public static TraktMovieSummary getTraktMovieSummary(String APIKey, String format, String imdb_id){

		TraktMovieSummary movieSummary = null;

		try {
			movieSummary = TraktApiCalls.callTraktMovieSummary(APIKey, format, imdb_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movieSummary;
	}

}


