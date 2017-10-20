import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Stopwords {

	public static String[] stopwordlist;
	public static Set<String> stopWordSet = null;
	private static final String FILEPATH = "complete_stopWords.txt";
	
	public static String[] read(){
		
		BufferedReader br = null;
		FileReader fr = null;
		String[] ret = null;

		try{
			fr = new FileReader(FILEPATH);
			br = new BufferedReader(fr);

			String sCurrentLine;
			ret = new String[41856];
			int i = 0;

			while((sCurrentLine = br.readLine()) != null){
				ret[i] = sCurrentLine;
				i++;
			}
			// System.out.println(ret.length);

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
		}
		return result;
	}

	static void populateHashSet(){
		stopWordSet = new HashSet<String> (Arrays.asList(stopwordlist));
	}

	public static void main(String[] args){

		stopwordlist = read();
		
		populateHashSet();
				
		String trial = "RT WLOS Basketball camp doubles as recruiting tool for UNCA womens team LiveOnWLOS";
		trial = trial.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
		trial = removeStopWords(trial);		
		System.out.println(trial);
		
	}

}