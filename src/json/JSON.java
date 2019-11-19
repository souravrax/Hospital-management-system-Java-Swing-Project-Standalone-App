package json;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON {
	
	public static String stringify(String ...elements) {
		String jsonString = "";
		for(String elem : elements) {
			System.out.println(elem);
		}
				
		return jsonString;
	}
	
	public static JSONObject parse(String json) throws ParseException {
		JSONObject jsonObject = new JSONObject();
		JSONParser parser = new JSONParser();
		
		jsonObject = (JSONObject) parser.parse(json.toString());
		
		
		return jsonObject;
	}
}