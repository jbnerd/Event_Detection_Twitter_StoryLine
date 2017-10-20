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
	public ArrayList<Kwp> kwps = new ArrayList<Kwp>();

	public void setContent(String content){
		this.content = content;
		this.populateTokens(content);
	}

	public String getContent(){
		return this.content;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setTimeStamp(int timestamp){
		this.timestamp = timestamp;
	}

	public int getTimeStamp(){
		return this.timestamp;
	}

	public String toString(){
		String ret = "";
		for(int i = 0; i < tokens.length; i++){
			ret += tokens[i];
			ret += " ";
		}
		return ret + " : " + String.valueOf(this.timestamp);
	}

	public void populateTokens(String content){
		StringTokenizer token = new StringTokenizer(content);
		Set<String> temp = new HashSet<String>();
		tokens = new String[token.countTokens()];
		int i = 0;
        while (token.hasMoreTokens()){
            temp.add(token.nextToken());
        }

        for(String s: temp){
        	tokens[i] = s;
        	i++;
        }
	}

	public void sortTokens(){
		Arrays.sort(tokens);
	}

}

