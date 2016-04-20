
/**
 * Write a description of MarkovTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class MarkovOne {
	private String myText;
	private Random myRandom;
	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}

	public ArrayList<String> getFollows2(String key){
		ArrayList<String> list = new ArrayList<String>();
		int pos=0;
		String[] myTextparts = myText.split(key);
		for(int i=1;i<myTextparts.length;i++){
			
			if (myTextparts[i].length()==1){
				String a= myTextparts[i].substring(myTextparts[i].length()-1);
				list.add(a);
			}
			else if (myTextparts[i].length()==0){
				if (key.length()!=1){
					String a= key.substring(0,1);
					list.add(a);
				}
				else{
					String a= key.substring(key.length()-1);
					list.add(a);
				}	
			}
			else if (myTextparts[i].length()!=0){
				String a= myTextparts[i].substring(0,1);
				list.add(a);
			}

					
		}
		return list;
	}

	public ArrayList<String> getFollows(String key){
		ArrayList<String> list = new ArrayList<String>();
		int pos=0;
		
		while(pos<myText.length()){
			int start=myText.indexOf(key,pos);
			if(start==-1){
				break;
			}
			if(start+key.length()>=myText.length()){
				break;
			}
			String next=myText.substring(start+1,start+2);
			list.add(next);
			pos=start+key.length();
		}
		return list;
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		int index = myRandom.nextInt(myText.length()-1);
		String key=myText.substring(index,index+1);
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		for(int k=0; k < numChars-1; k++){
			ArrayList<String> follows= getFollows(key);
			if(follows.size()==0){
				break;
			}
			index= myRandom.nextInt(follows.size());
			String next=follows.get(index);
			sb.append(next);
			key=next;
		}
		
		return sb.toString();
	}
}
