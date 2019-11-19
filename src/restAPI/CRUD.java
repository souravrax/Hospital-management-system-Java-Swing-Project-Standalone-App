package restAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CRUD {
	public static JSONObject fetch(String params, String type, String url) throws IOException, ParseException {
		if (type == "POST") {
			final String POST_PARAMS = params;
			URL obj = new URL(url);
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod(type);
			postConnection.setRequestProperty("Content-Type", "application/json");
			postConnection.setDoOutput(true);
			OutputStream os = postConnection.getOutputStream();
			os.write(POST_PARAMS.getBytes());
			os.flush();
			os.close();
			int responseCode = postConnection.getResponseCode();
//			System.out.println(responseCode);
			if (responseCode == 200) {
				System.out.println("POST Request ran successfully");
				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				JSONParser parser = new JSONParser();
//				System.out.println(response.toString());
				JSONObject json = (JSONObject) parser.parse(response.toString());
				
				return json;
			} else {
				System.out.println("POST NOT WORKED");
			}
		} else if (type == "GET") {
			URL urlForGetRequest = new URL(url);
			String readLine = null;
			HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuffer response = new StringBuffer();
				while ((readLine = in.readLine()) != null) {
					response.append(readLine);
				}
				in.close();
				System.out.println("JSON String Result " + response.toString());
			} else {
				System.out.println("GET NOT WORKED");
			}
		} else if (type == "PUT") {
			URL urlForPurRequest = new URL(url);
			final String POST_PARAMS = params;
			HttpURLConnection putConnection = (HttpURLConnection) urlForPurRequest.openConnection();
			putConnection.setRequestMethod(type);
			putConnection.setRequestProperty("Content-Type", "application/json");
			putConnection.setDoOutput(true);
			OutputStream os = putConnection.getOutputStream();
			os.write(POST_PARAMS.getBytes());
			os.flush();
			os.close();
			if(putConnection.getResponseCode() == 200){
				BufferedReader in = new BufferedReader(new InputStreamReader(putConnection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(response.toString());
				return json;
			}
			else{
				System.out.println("PUT NOT WORKED");
			}
		}
		return new JSONObject();
	}
	
	public static JSONArray specialFetch(String params, String type, String url) throws IOException, ParseException {
		
		final String POST_PARAMS = params;
		URL obj = new URL(url);
		HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		postConnection.setRequestMethod(type);
		postConnection.setRequestProperty("Content-Type", "application/json");
		postConnection.setDoOutput(true);
		OutputStream os = postConnection.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		int responseCode = postConnection.getResponseCode();
//			System.out.println(responseCode);
		if (responseCode == 200) {
			System.out.println("POST Request ran successfully");
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JSONParser parser = new JSONParser();
//				System.out.println(response.toString());
			JSONArray jsonArray = (JSONArray) parser.parse(response.toString());
			
			return jsonArray;
		} else {
			System.out.println("POST NOT WORKED");
		}
		
		return new JSONArray();
	}
}
