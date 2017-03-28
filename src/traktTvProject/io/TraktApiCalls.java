/**
 * 
 */
package traktTvProject.io;

import java.io.IOException;
import java.util.List;

import traktTvProject.dao.TraktMovieSummary;
import traktTvProject.dao.TraktUserProfile;
import traktTvProject.dao.UserMoviesWatched;
import traktTvProject.jsonParser.MovieSummaryParser;
import traktTvProject.jsonParser.TraktUserProfileParser;
import traktTvProject.jsonParser.UserMoviesWatchedParser;

/**
 * Class for all methods that constructs a String for a complete adress used for an API call to Trakt.tv
 *
 */
public class TraktApiCalls {
	

	/**
	 * Runs both NetclientGet.get() and the relevant Parser method that gets the json string from trakt.
	 * 
	 * Example API call for JSON String of a UserProfile if needed for comparison.
	 * http://api.trakt.tv/user/profile.json/06cd4f3b257367b4ec6331a5b8972704/justin
	 * 
	 * @param APIKey
	 * @param format
	 * @param user
	 * @return TraktUserProfile helper object with profile information about one requested user.
	 * @throws IOException
	 */
	public static TraktUserProfile callTraktUserProfile(String APIKey, String format, String user) throws IOException {
		
		String traktCallUrl = "http://api.trakt.tv/user/profile." + format + "/" + APIKey + "/" + user;
		
		return TraktUserProfileParser.parse(NetClientGet.getTraktJson(traktCallUrl));
	}
	
	/**
	 * Runs both NetclientGet.get() and the relevant Parser method that gets the json string from trakt.
	 * 
	 * Example API call for JSON String of a Users watched movie list if needed for comparison.
	 * http://api.trakt.tv/user/library/movies/watched.json/06cd4f3b257367b4ec6331a5b8972704/justin
	 * 
	 * @param APIKey
	 * @param format
	 * @param user
	 * @return A list of Movies watched by a single user.
	 * @throws IOException
	 */
	public static List<UserMoviesWatched> callTraktUserMoviesWatched(String APIKey, String format, String user) throws IOException{
		
		String traktCallUrl = "http://api.trakt.tv/user/library/movies/watched." + format + "/" + APIKey + "/" + user;
	
		return UserMoviesWatchedParser.parse(NetClientGet.getTraktJson(traktCallUrl));
	}

	/**
	 * Runs both NetclientGet.get() and the relevant Parser method that gets the json string from trakt based on imdb_id.
	 * 
	 * Not used for now after deciding to use LinkedMDB, but left as another example.
	 * 
	 * Example API call for JSON String of a summary of the movie  Hollow Man, if needed for comparison.
	 * Hollow Man - http://api.trakt.tv/movie/summary.json/06cd4f3b257367b4ec6331a5b8972704/tt0164052
	 * 
	 * @param APIKey
	 * @param format
	 * @param imdb_id
	 * @return MovieSummary object with summary information about one movie.
	 * @throws IOException 
	 */
	public static TraktMovieSummary callTraktMovieSummary(String APIKey, String format, String imdb_id) throws IOException{
		
		String traktCallUrl = "http://api.trakt.tv/movie/summary." + format + "/" + APIKey +"/" + imdb_id;
		
		return MovieSummaryParser.parse(NetClientGet.getTraktJson(traktCallUrl));
		
	}
}
