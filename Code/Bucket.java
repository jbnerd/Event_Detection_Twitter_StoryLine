import java.util.*;

public class Bucket{

	public Set<Kwp> keywordpairs = new HashSet<Kwp>();
	public Set<String> mixed_content = new HashSet<String>();

	public String toString(){
		return keywordpairs.toString();
	}

}