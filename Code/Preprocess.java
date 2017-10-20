import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;

public class Preprocess{

	private static String address_start = "cleaned_tweets/";
	private static String address_end = ".json";

	public static void main(String args[]){
		Tweet test = new Tweet();
		test.setContent("Basketball LiveOnWLOS RT UNCA WLOS recruiting team tool");
		test.setTimeStamp(1);
		generatekwps(test);
		System.out.println(test.kwps);
	}

	public static void generatekwps(Tweet tweet){
		Arrays.sort(tweet.tokens);
		for(int i = 0; i < tweet.tokens.length; i ++){
			for(int j = i+1; j < tweet.tokens.length; j ++){
				Kwp temp = new Kwp();
				temp.kw1 = tweet.tokens[i];
				temp.kw2 = tweet.tokens[j];
				tweet.kwps.add(temp);
			}
		}
		tweet.content = null;
	}

	public static ArrayList<Tweet> readfile(int window_num){

		String address = address_start + String.valueOf(window_num) + address_end;
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();

		try (BufferedReader br = new BufferedReader(new FileReader(address))){
        	String line;
        	while ((line = br.readLine()) != null){

        		JSONParser parser = new JSONParser();
        		Object object = parser.parse(line);
   			   	JSONObject read = (JSONObject)object;

   			   	Tweet temp = new Tweet();
   			   	temp.setContent(read.get("content").toString());
   			   	temp.setId(Long.parseLong(read.get("id").toString()));
   			   	temp.setTimeStamp(window_num);
   			   	tweets.add(temp);
   		 	}
        }catch(Exception e){
        	e.printStackTrace();
        }

        return tweets;
	}


}