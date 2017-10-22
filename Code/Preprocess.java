import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class Preprocess{

	private static String address_start = "cleaned_tweets/";
	private static String address_end = ".json";

	public static void main(String args[]){
		Tweet test = new Tweet();
		test.content = "Basketball LiveOnWLOS RT UNCA WLOS recruiting team tool";
		test.timestamp = 1;
		test.populateTokens();
		ArrayList<Kwp> temp = generatekwps_new(test);
		System.out.println(temp);
		System.out.println(test);
	}

	public static ArrayList<Kwp> generatekwps_new(Tweet tweet){// 0 : previous window; 1 : current window;
		Arrays.sort(tweet.tokens);
		ArrayList<Kwp> ret = new ArrayList<Kwp>();
		for(int i = 0; i < tweet.tokens.length; i ++){
			for(int j = i+1; j < tweet.tokens.length; j ++){
				Kwp temp = new Kwp();
				temp.kw1 = tweet.tokens[i];
				temp.kw2 = tweet.tokens[j];
				temp.curr_tweets.add(tweet);
				ret.add(temp);
			}
		}
		tweet.content = null;
		return ret;
	}

	public static ArrayList<Kwp> generatekwps(Window k, Window k_minus_1, Tweet tweet){
		Arrays.sort(tweet.tokens);
		ArrayList<Kwp> ret = new ArrayList<Kwp>();
		for(int i = 0; i < tweet.tokens.length; i++){
			for(int j = i+1; j < tweet.tokens.length; j++){
				Kwp temp = new Kwp();
				temp.kw1 = tweet.tokens[i];
				temp.kw2 = tweet.tokens[j];
				temp.curr_tweets.add(tweet);

				if(k_minus_1.keywordpairs.contains(temp)){
					for(Kwp kwp : k_minus_1.keywordpairs){
						if(kwp.hashCode() == temp.hashCode()){
							for(int l = 0; l < kwp.curr_tweets.size(); l++){
								temp.prev_tweets.add(kwp.curr_tweets.get(l));
							}
						}
					}
				}

				if (info_gain(k, k_minus_1, temp) > 0.003){
					ret.add(temp);
				}else{
					continue;
				}
			}
		}
		tweet.content = null;
		return ret;
	}

	public static ArrayList<Tweet> readfile(int window_num){

		String address = address_start + String.valueOf(window_num) + address_end;
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		Stopwords.stopwordlist = Stopwords.read();
		Stopwords.populateHashSet();

		try (BufferedReader br = new BufferedReader(new FileReader(address))){
        	String line;
        	while ((line = br.readLine()) != null){

        		JSONParser parser = new JSONParser();
        		Object object = parser.parse(line);
   			   	JSONObject read = (JSONObject)object;

   			   	Tweet temp = new Tweet();
   			   	String unclean = read.get("content").toString();
   			   	temp.content = Stopwords.removeStopWords(unclean);
   			   	temp.populateTokens();
   			   	temp.id = Long.parseLong(read.get("id").toString());
   			   	temp.timestamp = window_num;
   			   	tweets.add(temp);
   		 	}
        }catch(Exception e){
        	e.printStackTrace();
        }

        return tweets;
	}

	public static double log_base_2(double data){
		double ret = (double)Math.log((double)data)/Math.log((double)2);
		return ret;
	}

	public static double info_gain(Window k, Window k_minus_1, Kwp kwp){
		double prob_k = (double)k.tweets.size()/(k.tweets.size() + k_minus_1.tweets.size());
		double prob_k_minus_1 = (double)k_minus_1.tweets.size()/(k.tweets.size() + k_minus_1.tweets.size());
		double entropy_y = -1 * prob_k * log_base_2(prob_k) - prob_k_minus_1 * log_base_2(prob_k_minus_1);

		double prob_kwp_k = (double)kwp.curr_tweets.size()/(kwp.curr_tweets.size() + kwp.prev_tweets.size());
		double prob_kwp_k_minus_1 = (double)kwp.prev_tweets.size()/(kwp.prev_tweets.size() + kwp.curr_tweets.size());
		double entropy_y_given_s = -1 * prob_kwp_k * log_base_2(prob_kwp_k) - prob_kwp_k_minus_1 * log_base_2(prob_kwp_k_minus_1);
		
		if(entropy_y_given_s >= 0){
			return entropy_y - entropy_y_given_s;	
		}else{
			return entropy_y;
		}
		
	}
}