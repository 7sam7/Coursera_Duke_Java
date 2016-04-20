
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class MarkovFour extends AbstractMarkovModel{
	
	public MarkovFour(int n) {
		myRandom = new Random();
		myOrder =n;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}

	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-4);
		String key=myText.substring(index,index+4);		
		sb.append(key);
		//System.out.println("key:"+key);
		for(int k=0; k < numChars-4; k++){
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
