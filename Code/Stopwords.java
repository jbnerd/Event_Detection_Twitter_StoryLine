import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Stopwords {

	public static String[] stopwordlist;
	public static Set<String> stopWordSet = null;
	
	public static boolean isStopWord(String word){
		if(word.length() < 2) return true;
		if(word.charAt(0) >= '0' && word.charAt(0) <= '9') return true;
		if(stopWordSet.contains(word)) return true;
		else return false;
	}

	public static String removeStopWords(String string){
		String result = "";
		String[] words = string.split("\\s+");
		for(String word : words){
			if(word.isEmpty()) continue;
			if(isStopWord(word)) continue;
			result += (word + " ");
		//	System.out.println(word);
		//	System.out.println(result);
		}
		return result;
	}
	
	static void populateHashSet(){
		stopWordSet = new HashSet<String> (Arrays.asList(stopwordlist));
	}

/*
	static void populateStemmedHashSet(){
		stemmedStopWordSet = stemStringSet(stopWordSet);
	}
*/

	public static void main(String[] args){

		ReadFile fileio = new ReadFile();
		stopwordlist = fileio.read();
		
		/*
		for(int i = 0; i < stopwordlist.length; i ++){
			System.out.println(stopwordlist[i]);
		}
		*/

		populateHashSet();
		//System.out.println(stopWordSet);
		//populateStemmedHashSet();

		
		String trial = "this is a trial string, to check if the stop words are getting removed or not. to infinity and beyond";
		String new_trial = removeStopWords(trial);
		System.out.println(new_trial);
		
	}

}

class ReadFile{

	private static final String FILEPATH = "stopwords.txt";

	public String[] read(){
		
		BufferedReader br = null;
		FileReader fr = null;
		String[] ret = null;

		try{
			fr = new FileReader(FILEPATH);
			br = new BufferedReader(fr);

			String sCurrentLine;

			sCurrentLine = br.readLine();
			//System.out.println(sCurrentLine);
			StringTokenizer st = new StringTokenizer(sCurrentLine, ", ");
			int count = st.countTokens();
			//System.out.println(count);

			ret = new String[count];
			int i = 0;
			while(st.hasMoreTokens()){
				ret[i] = st.nextToken();
				i++;
			}

			for(i = 0; i < count; i ++){
				ret[i] = ret[i].replace("\"", "");
				//System.out.println(ret[i]);
			}			

		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(br != null)
					br.close();

				if(fr != null)
					fr.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}

		return ret;
	}

}