package traktTvProject.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/*
 * Shamelessly stolen/borrowed from
 * http://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
 */
public class NetClientGet {

	/**
	 * Takes a URL from the TraktCall methods, makes a http connection and asks Trakt for a JSON string.
	 * @param APIUrl
	 * @return String - JSON string from Trakt API call.
	 */
	public static String getTraktJson(String APIUrl) {
		StringBuilder strb = new StringBuilder();

		try {

			URL url = new URL(APIUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}


			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;

			while ((output = br.readLine()) != null) {
				strb.append(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return strb.toString();
	}
}


