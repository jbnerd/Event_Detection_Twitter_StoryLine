import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;

public class test{

   	public static void main(String[] args){
      	//JSONObject obj = new JSONObject();

      	//obj.put("name", "foo");
      	//obj.put("num", new Integer(100));
      	//obj.put("balance", new Double(1000.21));
    	//obj.put("is_vip", new Boolean(true));

      	//System.out.println(obj);

		// try (BufferedReader br = new BufferedReader(new FileReader("test.json"))) {
		// 	String line;
		// 	while ((line = br.readLine()) != null) {
		// 		// process the line.
		// 		JSONParser parser1 = new JSONParser();
		//    		try{
  //  					Object object = parser1.parse(line);
  //  					JSONObject jsonobject = (JSONObject)object;

  //  					System.out.println(jsonobject);
  //  				}catch(Exception e){
  //  					e.printStackTrace();
  //  				}
		// 	}
		// }catch(Exception e){
		// 		e.printStackTrace();
		// }

   // 		try{
   // 			FileWriter fw = new FileWriter("temp.txt",true); //the true will append the new data
   //  		fw.write("add a line\n");//appends the string to the file
   //  		fw.close();
   // 		}catch(Exception e){
   // 			e.printStackTrace();
   // 		}
   // 		// char a = '0';
   // 		// System.out.println((int)a);

   // 		try{
			// FileWriter fw = new FileWriter("temp.txt",true); //the true will append the new data
   //  		fw.write("add a line\n");//appends the string to the file
   //  		fw.close();
   // 		}catch(Exception e){
   // 			e.printStackTrace();
   // 		}

		// try (BufferedReader br = new BufferedReader(new FileReader("test.json"))) {
		// 	String line = br.readLine();
		// 	JSONParser parser = new JSONParser();
		//    	try{
  //  				Object object = parser.parse(line);
  //  				JSONObject jsonobject = (JSONObject)object;

  //  				// System.out.println(jsonobject);
  //  				String content = (String)jsonobject.get("text");
  //  				System.out.println(content);
  //  				content = content.replaceAll("https?://\\S+\\s?", "");
  //  				System.out.println(content);



  //  			}catch(Exception e){
  //  				e.printStackTrace();
  //  			}
		// }catch(Exception e){
		// 	e.printStackTrace();
		// }

   		// String st1 = "RT WLOS Basketball camp doubles as recruiting tool for UNCA womens team LiveOnWLOS";
   		// String st2 = "WLOS Basketball camp lol";
   		// String[] starr;
   		// Set<String> s1 = new HashSet<String>();
   		// Set<String> s2 = new HashSet<String>();

   		// StringTokenizer token = new StringTokenizer(st1);         
     //    while (token.hasMoreTokens()){
     //        s1.add(token.nextToken());
     //    }

     //    token = new StringTokenizer(st2);
     //    while(token.hasMoreTokens()){
     //    	s2.add(token.nextToken());
     //    }

     //    System.out.println(s1);
     //    System.out.println(s2);

     //    Set<String> s3 = new HashSet<String>(s1);
     //    Set<String> s4 = new HashSet<String>(s1);
     //    s3.retainAll(s2);
     //    s4.addAll(s2);
     //    System.out.println(s3);
     //    System.out.println(s4);

     //    float jaccard = (float)s3.size()/s4.size();
     //    System.out.println(1-jaccard);

	// Kwp temp1 = new Kwp();
	// temp1.kw1 = "loll";
	// temp1.kw2 = "lull";
	// Set<Kwp> test = new HashSet<Kwp>();
	// test.add(temp1);
	// Kwp temp2 = new Kwp();
	// temp2.kw1 = "loll";
	// temp2.kw2 = "lull";
	// System.out.println(temp1.equals(temp2));
	// System.out.println(test.contains(temp2));
   		
   		/////////////////////////////////////////////////////////////////////////////////////////////////////////
   		int prev_ts = 1, curr_ts = 2;
   		Window prev = new Window();
   		prev.tweets = Preprocess.readfile(prev_ts);
   		// System.out.println(first.tweets);
   		ArrayList<Kwp> temp1 = new ArrayList<Kwp>();
   		for(int i = 0; i < prev.tweets.size(); i ++){
   			ArrayList<Kwp> temp = Preprocess.generatekwps_new(prev.tweets.get(i));
   			temp1.addAll(temp);
   			// System.out.println(first.tweets.get(i));
   		}
   		prev.keywordpairs.addAll(temp1);
   		// System.out.println(first.keywordpairs);
   		prev.buckets = Bucketize.bucketize_new(prev.keywordpairs);
   		// System.out.println(first.buckets);

   		Window curr = new Window();
   		curr.tweets = Preprocess.readfile(curr_ts);
   		temp1 = new ArrayList<Kwp>();
   		for(int i = 0; i < curr.tweets.size(); i++){
   			ArrayList<Kwp> temp = Preprocess.generatekwps(prev, curr, curr.tweets.get(i));
   			temp1.addAll(temp);
   		}
   		curr.keywordpairs.addAll(temp1);
   		curr.buckets = Bucketize.bucketize(prev, curr.keywordpairs);

   		prev.buckets = Consolidation.intrawindow_consolidate(prev);
   		curr.buckets = Consolidation.intrawindow_consolidate(curr);
   		curr.buckets = Consolidation.consolidate(curr, prev);

   		System.out.println(prev.buckets);
   		System.out.println(prev.buckets.size());
   		System.out.println(curr.buckets);
   		System.out.println(curr.buckets.size());
   		////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  		Bucket temp1 = new Bucket();
  //  		Bucket temp2 = new Bucket();
  //  		Kwp kwp1 = new Kwp();
  //  		kwp1.kw1 = "lol";
  //  		kwp1.kw2 = "lull";
  //  		Kwp kwp2 = new Kwp();
  //  		kwp2.kw1 = "lol";
  //  		kwp2.kw2 = "lull";
  //  		System.out.println(kwp1.equals(kwp2));

  //  		temp1.keywordpairs.add(kwp1);
  //  		temp1.mixed_content.add("lol");
  //  		temp1.mixed_content.add("LiveOnWLOS");
  //  		temp1.mixed_content.add("lull");
  //  		temp1.mixed_content.add("thunder");

  //  		temp2.keywordpairs.add(kwp2);
		// temp2.mixed_content.add("lol");
  //  		temp2.mixed_content.add("LiveOnWLOS");
  //  		temp2.mixed_content.add("lull");
  //  		temp2.mixed_content.add("thunder");

  //  		System.out.println(temp1.hashCode());
  //  		System.out.println(temp2.hashCode());

  //  		Set<Bucket> test = new HashSet<Bucket>();
  //  		test.add(temp1);
  //  		System.out.println(test.contains(temp2));
   		///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}
}
