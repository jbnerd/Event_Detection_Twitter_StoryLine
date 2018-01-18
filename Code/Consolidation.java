import java.util.*;

public class Consolidation{

	public static Set<Bucket> consolidate(Window k, Window k_minus_1){
		Set<Bucket> temp = intrawindow_consolidate(k);
		// System.out.println("IN - intrawindow_consolidation done");
		temp = interwindow_consolidate(temp, k_minus_1);
		// System.out.println("IN - interwindow_consolidation done");

		return temp;
	}

	public static Set<Bucket> intrawindow_consolidate(Window k){
		Set<Bucket> ret = new HashSet<Bucket>();
		int i = 0, j = 0;
		for(Bucket bucket: k.buckets){
			Bucket temp = new Bucket();
			temp.keywordpairs.addAll(bucket.keywordpairs);
			temp.mixed_content.addAll(bucket.mixed_content);
			for(Bucket bucket1: k.buckets){
				Set<String> intersection = new HashSet<String>(bucket.mixed_content);
				intersection.retainAll(bucket1.mixed_content);
				Set<String> union = new HashSet<String>(bucket.mixed_content);
				union.addAll(bucket1.mixed_content);
				float jaccard = (float)intersection.size()/union.size();
				if (i%10000000 == 0){
					// System.out.println(j + "th iteration of intrawindow_consolidation");
					i = 0; j += 1;
				}

				i++;
				if(jaccard >= 0.5){
					temp.keywordpairs.addAll(bucket1.keywordpairs);
					temp.mixed_content.addAll(bucket1.mixed_content);
					// System.out.println(jaccard);
				}else{
					continue;
				}
			}
			// System.out.println(temp);
			ret.add(temp);
		}
		return ret;
	}

	public static Set<Bucket> interwindow_consolidate(Set<Bucket> k, Window k_minus_1){
		Set<Bucket> ret = new HashSet<Bucket>();
		int i = 0, j = 0;
		for(Bucket bucket: k){
			Bucket temp = new Bucket();
			temp.keywordpairs.addAll(bucket.keywordpairs);
			temp.mixed_content.addAll(bucket.mixed_content);
			for(Bucket bucket1: k_minus_1.buckets){
				Set<String> intersection = new HashSet<String>(bucket.mixed_content);
				intersection.retainAll(bucket1.mixed_content);
				Set<String> union = new HashSet<String>(bucket.mixed_content);
				union.addAll(bucket1.mixed_content);
				float jaccard = (float)intersection.size()/union.size();
				if (i%10000000 == 0){
					// System.out.println(j + "th iteration of intrawindow_consolidation");
					i = 0; j += 1;
				}

				i++;
				if(jaccard >= 0.5){
					temp.keywordpairs.addAll(bucket1.keywordpairs);
					temp.mixed_content.addAll(bucket1.mixed_content);
					// System.out.println(jaccard);
				}else{
					continue;
				}
			}
			// System.out.println(temp);
			ret.add(temp);
		}
		return ret;
	}
}