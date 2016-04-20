
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class MarkovModel {
	private String myText;
	private Random myRandom;
	private Integer myOrder;
	
	public MarkovModel(int n) {
		myRandom = new Random();
		myOrder = n;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
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
			String next=myText.substring(start+myOrder,start+myOrder+1);
			list.add(next);
			pos=start+key.length();
		}
		return list;
	}
	
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-myOrder);
		String key=myText.substring(index,index+myOrder);		
		sb.append(key);
		//System.out.println("key:"+key);
		for(int k=0; k < numChars-myOrder; k++){
			ArrayList<String> follows= getFollows(key);
			//System.out.println("key "+key+" "+follows);
			if(follows.size()==0){
				break;
			}
			index= myRandom.nextInt(follows.size());
			String next=follows.get(index);
			sb.append(next);
			key= key.substring(1)+next;
		}
		
		return sb.toString();
	}
}
