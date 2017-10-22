import java.util.*;

public class Consolidation{

	public static ArrayList<Bucket> consolidate(Window k, Window k_minus_1){
		ArrayList<Bucket> temp = intrawindow_consolidate(k);
		temp = interwindow_consolidate(temp, k_minus_1);
		return temp;
	}

	public static ArrayList<Bucket> intrawindow_consolidate(Window k){
		ArrayList<Bucket> ret = new ArrayList<Bucket>();
		for(int i = 0; i < k.buckets.size(); i ++){
			Bucket temp = new Bucket();
			for(int j = i + 1; j < k.buckets.size(); j++){
				Set<String> intersection = new HashSet<String>(k.buckets.get(i).mixed_content);
				intersection.retainAll(k.buckets.get(j).mixed_content);
				Set<String> union = new HashSet<String>(k.buckets.get(i).mixed_content);
				union.addAll(k.buckets.get(j).mixed_content);
				float jaccard = (float)intersection.size()/union.size();

				if(jaccard >= 0.75){
					temp.keywordpairs.addAll(k.buckets.get(i).keywordpairs);
					temp.keywordpairs.addAll(k.buckets.get(j).keywordpairs);
					temp.mixed_content.addAll(k.buckets.get(i).mixed_content);
					temp.mixed_content.addAll(k.buckets.get(j).mixed_content);
					System.out.println(jaccard);
				}else{
					continue;
				}
			}
			System.out.println(temp);
			ret.add(temp);
		}
		return ret;
	}

	public static ArrayList<Bucket> interwindow_consolidate(ArrayList<Bucket> k, Window k_minus_1){
		ArrayList<Bucket> ret = new ArrayList<Bucket>();
		for(int i = 0; i < k.size(); i ++){
			Bucket temp = new Bucket();
			for(int j = 0; j < k_minus_1.buckets.size(); j++){
				Set<String> intersection = new HashSet<String>(k.get(i).mixed_content);
				intersection.retainAll(k_minus_1.buckets.get(j).mixed_content);
				Set<String> union = new HashSet<String>(k.get(i).mixed_content);
				union.addAll(k_minus_1.buckets.get(j).mixed_content);
				float jaccard = (float)intersection.size()/union.size();
				
				if(jaccard >= 0.75){
					temp.keywordpairs.addAll(k.get(i).keywordpairs);
					temp.keywordpairs.addAll(k_minus_1.buckets.get(j).keywordpairs);
					temp.mixed_content.addAll(k.get(i).mixed_content);
					temp.mixed_content.addAll(k_minus_1.buckets.get(j).mixed_content);
					System.out.println(jaccard);
				}else{
					continue;
				}
			}
			System.out.println(temp);
			ret.add(temp);
		}
		return ret;
	}
}