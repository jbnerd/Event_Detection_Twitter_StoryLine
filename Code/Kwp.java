import java.util.*;

public class Kwp{
	public String kw1;
	public String kw2;
	public ArrayList<Tweet> prev_tweets = new ArrayList<Tweet>();
	public ArrayList<Tweet> curr_tweets = new ArrayList<Tweet>();

	public String toString(){
		String prev_ret = "";
		for(int i = 0; i < prev_tweets.size(); i ++){
			prev_ret += String.valueOf(i);
			prev_ret += " : ";
			prev_ret += prev_tweets.get(i).toString();
			prev_ret += "\n";
		}

		String curr_ret = "";
		for(int i = 0; i < curr_tweets.size(); i ++){
			curr_ret += String.valueOf(i);
			curr_ret += " : ";
			curr_ret += curr_tweets.get(i).toString();
			curr_ret += "\n";
		}

		//String ret = "keywords : " + kw1 + " + " + kw2 + "\n previous window tweets {\n" + prev_ret + "}\n current window tweets {\n" + curr_ret + "}";
		//return ret;
		return kw1 + " + " + kw2;
	}

	public boolean hasIncreased(){
		return curr_tweets.size() > prev_tweets.size();
	}

	public boolean equals(Object other){
		Kwp temp = (Kwp)other;
		if (temp.kw1 == this.kw1 && temp.kw2 == this.kw2){
			return true;
		}else{
			return false;
		}
	}

	public int hashCode(){
		return 37*kw1.hashCode() + kw2.hashCode();
	}
}