import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;

public class Tweet{

	public String content;
	public Long id;
	public int timestamp;
	public String[] tokens;

	public String toString(){
		String ret = "";
		for(int i = 0; i < tokens.length; i++){
			ret += tokens[i];
			ret += " ";
		}
		return ret + " : " + String.valueOf(this.timestamp);
	}

	public void populateTokens(){
		StringTokenizer token = new StringTokenizer(this.content);
		Set<String> temp = new HashSet<String>();
		int i = 0;
        while (token.hasMoreTokens()){
            temp.add(token.nextToken());
        }

        tokens = new String[temp.size()];

        for(String s: temp){
        	tokens[i] = s;
        	i++;
        }
	}

	public void sortTokens(){
		Arrays.sort(tokens);
	}

}

