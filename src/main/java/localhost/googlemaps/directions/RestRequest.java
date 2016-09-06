package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/*Simple class to make requests to google maps
 * @author Forrest Corry
 * @since 2016-06-09
 */


public class RestRequest{
	
	/*
	 * the URL of the API we want to connect to
	 */
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	
	/*The character set to use when encoding IRL parameters
	 * 
	 */
	protected static String charset = "UTF-8";
	
	/*
	 * API key used for making requests to API
	 */
	
	protected static String key = "AIzaSyDGP5PNMHqUms__GLT_Org_lRAPxe-qIx8";
	
	
	public static void main(String[] args) {
		
		try{
			
			//The origin or starting point for directions
			String origin = "Munich";
			
			//The destination or end point for directions
			String destination = "London";
			
			//The return type of the response xml json
			String returnType = "json";
			
			String language = "de";
			
			String avoid = "tolls|highways";
			
			String region = "eu";
			
			//creats the url parameters as a string encodeing them with the defined charset
			String queryString = String.format("origin=%s&destination=%s&language=%s&avoid=%s&region=%s&key=%s",
					URLEncoder.encode(origin, charset),
					URLEncoder.encode(destination, charset),
					URLEncoder.encode(language, charset),
					URLEncoder.encode(avoid, charset),
					URLEncoder.encode(region, charset),
					URLEncoder.encode(key, charset));
			
			//creats a new URL out of the endpoint, returnType and queryString
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");
			
			//if we did not get a 200 (success) throw an exception
			if(connection.getResponseCode() !=200){
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}
			
			//read response into buffer
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			//loop of buffer line by line until it return nul meaning there are no more lines
			while(br.readLine() !=null){
				System.out.println(br.readLine());
			}
			
			//close connection to API
			connection.disconnect();
			
			
		}catch (MalformedURLException e){
			e.printStackTrace();
			
		}catch (IOException e){
			e.printStackTrace();
		}
		
		
	}//main
	
}//class
