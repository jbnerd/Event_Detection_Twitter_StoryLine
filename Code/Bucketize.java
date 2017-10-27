import java.util.*;

public class Bucketize{

	public static Set<Bucket> bucketize_new(Set<Kwp> kwps){
		Set<Bucket> ret = new HashSet<Bucket>();
		for(Kwp kwp : kwps){
			Bucket temp = new Bucket();
			temp.keywordpairs.add(kwp);
			for(int j = 0; j < kwp.curr_tweets.size(); j ++){
				for(int k = 0; k < kwp.curr_tweets.get(j).tokens.length; k ++){
					String temp1 = kwp.curr_tweets.get(j).tokens[k];
					temp.mixed_content.add(temp1);
				}
			}
			ret.add(temp);
		}
		return ret;
	}

	public static Set<Bucket> bucketize(Window k_minus_1, Set<Kwp> kwps){
		Set<Bucket> ret = bucketize_new(kwps);
		for(Bucket b : k_minus_1.buckets){
			for(Kwp kwp : b.keywordpairs){
				if(kwp.curr_tweets.size() >= kwp.prev_tweets.size()){
					ret.add(b);
					break;
				}
			}
		}
		return ret;
	}
}