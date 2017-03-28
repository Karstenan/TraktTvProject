/**
 * 
 */
package traktTvProject.jenaModelatorium;

import java.util.List;

import traktTvProject.dao.TraktUserProfile;
import traktTvProject.dao.UserMoviesWatched;
import traktTvProject.extraVocab.TraktVocab;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;

/**
 * Models and adds all the triples to the TDB triple store.
 * This is just a very small sample of what the rich API set of Trakt has to offer but it should be sufficient to
 * showcase to principle idea of this lifting exercise.
 * Television shows, the specific episodes, even more various user information, or even trending of popular movies/Tvshows
 * amongst users could be added and get a very rich triple store that can be used with other datasets quite easily with
 * a program such as this as a base.
 *
 */ 
public class Triplelatorium {
	
/**
 * Takes TraktUserProfile and a list of his/hers watched movies, and makes use of getters() to get relevant data to the 
 * jena code that makes triples.
 * 
 * @param traktUser
 * @param userWlist
 */
	public static void makeTriples (TraktUserProfile traktUser, List<UserMoviesWatched> userWlist){

		/*Makes/opens the dataset store on the drive*/
		Dataset dataset = TDBFactory.createDataset("tdbfolder/traktstore");		
		/*gets the default model from the dataset so jena has something to work with, adding etc.*/
		Model traktStore = dataset.getDefaultModel();
		
		/*makes the base for the user triples, the Trakt UserNames are unique and therefore works
		 * well as a base, and it will link to the user page on the Trakt.tv site.*/
		String base = "http://trakt.tv/user/";
		
		/*Another base for the movie triples, as Trakt uses the imdb_id as unique identifier and not name
		 * it makes sense to use this as a base for the movies.
		 * It will also link to the movie page on www.imdb.com which is the biggest
		 * moviedatabase on the web that most adhere to and as such is a good unique identifier*/
		String imdbBase = "http://www.imdb.com/title/";
		
	
		/*Generates the UserRes resource with the base + the Trakt Username from the user helper object*/
		Resource userRes = traktStore.createResource(base + traktUser.getUsername()); //sette inn getUser elns!
		
		
		/*A few literals to denote the number of each respective item on the user profile
		 * expressed as a positive integer as that was the closest we came to something that makes sense.
		 * It is a number, and it will always either be 0 or higher number of friends/watched movies.
		 * WatchedMovies is how many has been watched incuding repetitions of single movies.
		 * WatchedMobiesUnique is the number of unique movies watched.
		 */
		Literal userNumberOfFriendsLit = traktStore.createTypedLiteral(traktUser.getStats().getFriends(), XSDDatatype.XSDpositiveInteger); //antall venner av bruker
		Literal userNumberOfWatchedMoviesLit = traktStore.createTypedLiteral(traktUser.getStats().getMovies().getWatched(), XSDDatatype.XSDpositiveInteger); //antall filmer som har blitt sett
		Literal userNumberOfWatchedUniqueMoviesLit = traktStore.createTypedLiteral(traktUser.getStats().getMovies().getWatched_unique(), XSDDatatype.XSDpositiveInteger); // antall unike filmer som har blitt, altso 1 film registrert 1 gong til tross for å sett fleire gonga.
		
		
		
		/*adds some properties to the user to make it idenfiable in the triple store.*/
		userRes.addProperty(TraktVocab.hasUserNameProp, traktUser.getUsername())
							.addProperty(RDFS.label, traktUser.getUsername())
							.addProperty(RDF.type, TraktVocab.TraktTvUserRes);
		
		/*adds the previously mentioned literals*/
		userRes.addLiteral(TraktVocab.hasNumberOfFriendsProp, userNumberOfFriendsLit)
							.addLiteral(TraktVocab.hasNumberOfWatchedMoviesProp, userNumberOfWatchedMoviesLit)
							.addLiteral(TraktVocab.hasNumberOfWatchedUniqueMoviesProp, userNumberOfWatchedUniqueMoviesLit);
		
		/*
		 * Iterera gjennom ei liste av alle sette filmer for en bruker henta frå Trakt og laga tripla som kobla kvar film til brukeren.
		 */
		
		/*
		 * Iterates trough the userWatchedList of movies and makes each movies as resource and
		 * adds each movie as a triple and connecting it the User through a blank node.
		 */
		for (UserMoviesWatched umw : userWlist) {
		
			/*Makes a movie resource with the imdb_id as a base to make it uniquely identifiable in the triplestore*/
			Resource movieRes = traktStore.createResource(imdbBase + umw.getImdb_id());
			
			/*adds relevant properties to the movieRes, importantly DCTerms title as this
			 * will make the movie identifiable to useful in matching movies against LinkedMDB,
			 * as LinkedMDB used this predicate for it's movies.*/
			movieRes.addProperty(TraktVocab.hasImdb_idProp, umw.getImdb_id())
								.addProperty(DCTerms.title,  umw.getTitle());
			
			
			/*A literal to keep track of the number of times one movie has been watched.*/
			Literal userNumberOfMoviePlaysLit = traktStore.createTypedLiteral(umw.getPlays(), XSDDatatype.XSDpositiveInteger);

			
			/*
			 * Adds a blank node to the user with hasWatchedMovie predicate.
			 * Then adds the numberofPlays literal and the movie resource what was just made with
			 * hasWatchedThisMovie, this is important as each predicate needs to unique.
			 * This will make us able to connect a movie to one user, and how many plays this movie has had by one user
			 * without running into the problems of placing this literal on the movie itself or the user, there would be
			 * no way of telling which movie had been watched several times, or which user has watched a movie several times.
			 */
			userRes.addProperty(TraktVocab.hasWatchedMovieProp, 
					traktStore.createResource()
					.addProperty(TraktVocab.hasNumberOfMoviePlaysProp, userNumberOfMoviePlaysLit)
					.addProperty(TraktVocab.hasWatchedThisMovieProp, movieRes));
			
						
			//userRes.addProperty(TraktVocab.hasWatchedMovieProp, movieRes);
							
		}
		
		
		/*writes the triples to the TDB store, and prints it in Turtle format for now to Syso just 
		 * so we can see something is happening
		 */
		traktStore.write(System.out, "TURTLE", base);
		
		/*This can be used when we wish to commit the triples to the TDB store without printing*/
//		traktStore.commit();
		
		/*closes the dataset each time we are finished writing to it.*/
		dataset.close();
		
		/*for now just prints in Syso from which user we have gotten information about.*/
		System.out.println("Fetched " + traktUser.getUsername() + " Trakt User from Trakt.tv");
		
		
	}			
}
