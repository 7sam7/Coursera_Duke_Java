
/**
 * Write a description of MarkovTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class MarkovOne extends AbstractMarkovModel {
	
	
	public MarkovOne(int myorder) {
		myRandom = new Random();
		super(myorder);
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
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
