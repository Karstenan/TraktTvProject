package traktTvProject.extraVocab;
/* CVS $Id: $ */

import com.hp.hpl.jena.rdf.model.*;


/**
 * 
 * The custom vocab constructed having the neccassary predicates and such to express reasonable triples
 * for a Trakt user so he/she can be made into semantic data as triples in TDB.
 * This vocab expresses the most essential things needed for what the program does right now, but if more
 * content is added then the vocab needs to be expanded to handle things such as television shows etc.
 * 
 * Things such as label, and title is best handled by RDFS and DC terms as they already cover these concepts in a good way.
 *
 */
public class TraktVocab {
	
	  private static Model tt_model = ModelFactory.createDefaultModel();
	    
	    /** <p>The namespace of the vocabulary as a string</p> */
	    public static final String TT = "http://www.example.org";
	    
	    /** <p>The namespace of the vocabulary as a string</p>
	     *  @see #TT */
	    public static String getURI() {return TT;}
	    
	    /** <p>The namespace of the vocabulary as a resource</p> */
	    public static final Resource NAMESPACE = tt_model.createResource( TT );
	    
	    /*
	     * Extra properties.
	     */
	    public static final Property hasUserNameProp = tt_model.createProperty("http://example.org/TraktTvVocab#hasUserName" );
	      
	    public static final Property hasWatchedMovieProp = tt_model.createProperty("http://example.org/TraktTvVocab#hasWatchedMovie" );
	
	    public static final Property hasWatchedThisMovieProp = tt_model.createProperty("http://example.org/TraktTvVocab#hasWatchedThisMovie" );
		
	    public static final Property hasNumberOfMoviePlaysProp = tt_model.createProperty("http://example.org/TraktTvVocab#hasNumberOfMoviePlays" );
	    
	    public static final Property hasNumberOfFriendsProp = tt_model.createProperty("http://example.org/TraktTvVocab#hasNumberOfFriends" );
		
	    public static final Property hasNumberOfWatchedMoviesProp = tt_model.createProperty("http://example.org/TraktTvVocab#hasNumberOfWatchedMovies" );
		
	    public static final Property hasNumberOfWatchedUniqueMoviesProp = tt_model.createProperty("http://example.org/TraktTvVocab#hasNumberOfWatchedUniqueMovies" );
	    
	    public static final Property hasImdb_idProp = tt_model.createProperty("http://example.org/TraktTvVocab#hasImdb_id");
	    
		
	    /*
	     * Extra resources
	     */
	    public static final Resource TraktTvUserRes = tt_model.createResource( "http://example.org/TraktTvVocab#TraktTvUser" );
	    public static final Resource Imdb_id = tt_model.createResource("http://example.org/TraktTvVocab#Imdb_id" );
	    
}
