import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.*;

public class ReadAndStore{

   public static void main(String[] args){
      int count = 0;
      try (BufferedReader br = new BufferedReader(new FileReader("test.json"))){
         String line;
         while ((line = br.readLine()) != null){
            // process the line.
            JSONParser parser1 = new JSONParser();
            try{
               Object object = parser1.parse(line);
   			   JSONObject read = (JSONObject)object;
               JSONObject write = new JSONObject();

               String temp = (String)read.get("text");
               temp = temp.replaceAll("https?://\\S+\\s?", "");
               temp = temp.replaceAll("[^a-zA-Z\\s]", "").replaceAll("\\s+", " ");
               write.put("content", temp);
               write.put("id", read.get("id"));
               write.put("timestamp", read.get("created_at"));

               int ts = get_ts(read.get("created_at"));
               count ++;

               String path = "processed_tweets/" + String.valueOf(ts) + ".json";
               System.out.println(path);
               FileWriter fw = new FileWriter(path ,true); //the true will append the new data
               fw.write(write.toString());//appends the string to the file
               fw.write("\n");
               fw.close();


               // System.out.println(write);
            }catch(Exception e){
   			   e.printStackTrace();
            }
         }
      }catch(Exception e){
			e.printStackTrace();
      }
      System.out.println(count);
	}

   public static int get_ts(Object timestamp){
      int ret = 0;
      String ts = (String)timestamp;

      if((int)(ts.charAt(11) - 48) == 0 && (int)(ts.charAt(12) - 48) >= 0 && (int)(ts.charAt(12) - 48) <= 5){
         if(ts.charAt(0) == 'S' && ts.charAt(1) == 'a'){
            ret = 1;   
         }else if(ts.charAt(0) == 'S' && ts.charAt(1) == 'u'){
            ret = 5;
         }else if(ts.charAt(0) == 'M'){
            ret = 9;
         }else if(ts.charAt(0) == 'T' && ts.charAt(1) == 'u'){
            ret = 13;
         }else if(ts.charAt(0) == 'W'){
            ret = 17;
         }else if(ts.charAt(0) == 'T' && ts.charAt(1) == 'h'){
            ret = 21;
         }else{
            ret = 25;
         }
      }else if(((int)(ts.charAt(11) - 48) == 0 && (int)(ts.charAt(12) - 48) >= 6) || (((int)(ts.charAt(11) - 48) == 1) && (int)(ts.charAt(12) - 48) <= 1)){
         if(ts.charAt(0) == 'S' && ts.charAt(1) == 'a'){
            ret = 2;   
         }else if(ts.charAt(0) == 'S' && ts.charAt(1) == 'u'){
            ret = 6;
         }else if(ts.charAt(0) == 'M'){
            ret = 10;
         }else if(ts.charAt(0) == 'T' && ts.charAt(1) == 'u'){
            ret = 14;
         }else if(ts.charAt(0) == 'W'){
            ret = 18;
         }else if(ts.charAt(0) == 'T' && ts.charAt(1) == 'h'){
            ret = 22;
         }else{
            ret = 26;
         }
      }else if((int)(ts.charAt(11) - 48) == 1 && (int)(ts.charAt(12) - 48) >= 2 && (int)(ts.charAt(12) - 48) <= 7){
         if(ts.charAt(0) == 'S' && ts.charAt(1) == 'a'){
            ret = 3;
         }else if(ts.charAt(0) == 'S' && ts.charAt(1) == 'u'){
            ret = 7;
         }else if(ts.charAt(0) == 'M'){
            ret = 11;
         }else if(ts.charAt(0) == 'T' && ts.charAt(1) == 'u'){
            ret = 15;
         }else if(ts.charAt(0) == 'W'){
            ret = 19;
         }else if(ts.charAt(0) == 'T' && ts.charAt(1) == 'h'){
            ret = 23;
         }else{
            ret = 27;
         }
      }else if(((int)(ts.charAt(11) - 48) == 1 && (int)(ts.charAt(12) - 48) >= 8) || (((int)(ts.charAt(11) - 48) == 2) && (int)(ts.charAt(12) - 48) <= 3)){
         if(ts.charAt(0) == 'S' && ts.charAt(1) == 'a'){
            ret = 4;   
         }else if(ts.charAt(0) == 'S' && ts.charAt(1) == 'u'){
            ret = 8;
         }else if(ts.charAt(0) == 'M'){
            ret = 12;
         }else if(ts.charAt(0) == 'T' && ts.charAt(1) == 'u'){
            ret = 16;
         }else if(ts.charAt(0) == 'W'){
            ret = 20;
         }else if(ts.charAt(0) == 'T' && ts.charAt(1) == 'h'){
            ret = 24;
         }else{
            ret = 28;
         }
      }

      // System.out.print(ts);
      // System.out.print(" : ");
      // System.out.println(ret);
      return ret;
   }

}
