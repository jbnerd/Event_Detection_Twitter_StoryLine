import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;

public class Tweet{

	private String content;
	private int id;
	private int timestamp;

	public void setContent(String content){
		this.content = content;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setTimeStamp(int timestamp){
		this.timestamp = timestamp;
	}
}

