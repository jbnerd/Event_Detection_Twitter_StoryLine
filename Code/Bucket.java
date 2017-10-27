import java.util.*;

public class Bucket{

	public Set<Kwp> keywordpairs = new HashSet<Kwp>();
	public Set<String> mixed_content = new HashSet<String>();

	public String toString(){
		return keywordpairs.toString() + ":\n" + mixed_content.toString() + "\n";
	}

	public int hashCode(){
		int ret = 0;
		
		for(Kwp kwp: keywordpairs){
			ret += kwp.hashCode();
		}

		return ret;
	}

	public boolean equals(Object other){
		Bucket new_other = (Bucket)other;
		if(this.keywordpairs.hashCode() == new_other.keywordpairs.hashCode()){
			if(this.mixed_content.hashCode() == new_other.mixed_content.hashCode()){
				return true;
			}
		}
		return false;
	}

}