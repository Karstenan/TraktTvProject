package traktTvProject.sparqlaTorium;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;
import com.hp.hpl.jena.tdb.TDBFactory;

public class QueryMaker {

	/**
	 * Makes a query to both TraktStore and LinkedMDB getting a list of watched movies for a user where
	 * Kevin Bacon has featured and from this making an amputed form of Kevin Bacon Score.
	 * 
	 * @param user
	 * @return baconScore - int that is the number of movies this user has seen with Kevin Bacon.
	 */
	public static int userTraktQuery(String user){

		/*
		 * Left intentionally, if more Sparql queries wish to be tested.
		 * Just write sparql queries in the testQuery.txt file in the project folder and use the buffered reader.
		 */

		//		String queryString = "";
		//		
		//		try {
		//			BufferedReader reader = new BufferedReader(new FileReader("testQuery.txt"));
		//			String line2 = "";
		//			while ((line2 = reader.readLine()) != null) {
		//				queryString = queryString + line2;
		//			}
		//		} catch (FileNotFoundException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		//		


		/**
		 * The query itself
		 * It makes a construct, a new model based on dc:title movie titles across 
		 * LinkedMDB and TraktStore. To do this it makes a Service call to the local fuseki
		 * server running LinkedMDB and gets a list of Kevin Bacon movies.
		 */
		String  queryString = ""
				+"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+"PREFIX owl: <http://www.w3.org/2002/07/owl#>"
				+"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+"PREFIX tt: <http://example.org/TraktTvVocab#>"
				+"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>"
				+"PREFIX dc: <http://purl.org/dc/terms/>"
				+"CONSTRUCT {"
				+"?userURI tt:hasWatchedMovie ?i ."
				+"?i tt:hasWatchedThisMovie ?movieURI ."
				+"?movieURI dc:title ?movieTitle ."
				+"?mURI movie:actor ?actorURI ."
				+"} WHERE {"
				+"SERVICE <http://localhost:3030/LinkedMDB/query>{"
				+"?mURI dc:title ?movieTitle ."
				+"?mURI movie:actor ?actorURI ."
				+"?actorURI movie:actor_name \"Kevin Bacon\" . }"
				+"?userURI tt:hasUserName " + "\""+user+"\"" + " ."
				+"?userURI  tt:hasWatchedMovie ?i ."
				+"?i tt:hasWatchedThisMovie ?movieURI ."
				+"?movieURI dc:title ?movieTitle . }";

		/*creates a Query object*/
		Query query = QueryFactory.create(queryString);

		/*opens the traktStore so it is ready for the query*/
		Dataset dataset = TDBFactory.createDataset("tdbfolder/traktstore");		

		/*Executes the query against the dataset*/
		QueryExecution queryExecution = QueryExecutionFactory.create(query, dataset);

		/*Gets a model back based on the construct from the query.*/
		Model queryResult = queryExecution.execConstruct();		

		/*Unsure if this is the right way to handle a query like this but it works for what we need
		 *Makes a new query meant for the new model that only contains the movies seen by the user
		 *that also Kevin Bacon acted in. 
		 */
		Query baconQuery = QueryFactory.create(""
				+ "PREFIX dc: <http://purl.org/dc/terms/>"
				+ "PREFIX tt: <http://example.org/TraktTvVocab#>"
				+ "SELECT ?movieTitle WHERE { "
				+ " ?movieURI dc:title ?movieTitle .}");

		/*executes the query on the resulting model from the previous query.*/
		QueryExecution baconQueryExecution = QueryExecutionFactory.create(baconQuery, queryResult);


		/*get a ResultSet back*/
		ResultSet baconResult = baconQueryExecution.execSelect();

		/*there is probably a better way of doing this but again, it gets the job done.¨
		 * while loop that counts the number of entries in the ResultSet which is the number of movies
		 * seen by one user with Kevin Bacon. 
		 */
		int baconScore = 0;
		while (baconResult.hasNext()) {
			baconResult.next();
			baconScore++;
		}

		/*closing the dataset when done querying.*/
		dataset.close();

		/*returns the baconscore to TraktGui to be added to jlabels to show the score.*/
		return baconScore;
	}




	/**
	 * Extra method currentlly not used because of the addition of inline SERVICE CALL, 
	 * but can be used as an alternative way to contact the fuseki server running LinkedMDB.
	 * 
	 * @return baconResult - a ResultSet containing whith this query a set of movies Kevin Bacon as been actor in
	 * from LinkedMDB
	 */
	public static ResultSet getBaconMovies(){

		String query = ""
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX tt: <http://example.org/TraktTvVocab#>"
				+ "PREFIX movie: <http://data.linkedmdb.org/resource/movie/>"
				+ "PREFIX dc: <http://purl.org/dc/terms/>"
				+ "SELECT ?movieTitle "
				+ "WHERE "
				+ "{ ?actorURI movie:actor_name \"Kevin Bacon\" ."
				+ " ?movieURI movie:actor ?actorURI ."
				+ " ?movieURI dc:title ?movieTitle .}";


		String service = "http://localhost:3030/LinkedMDB/query";
		ResultSet baconResult = null;
		QueryExecution qe = 
				QueryExecutionFactory.sparqlService(service, query);
		try {
			if (qe.execSelect() != null) {
				System.out.println(service + " is UP");
				baconResult = qe.execSelect();

				System.out.println(ResultSetFormatter.asText(baconResult));
			}
		} catch (QueryExceptionHTTP e) {
			System.out.println(service + " is DOWN");
		} finally {
			qe.close();
		} 
		return baconResult;
	}

}
