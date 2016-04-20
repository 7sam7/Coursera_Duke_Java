
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class MarkovWord implements IMarkovModel {
	private String[] myText;
    private Random myRandom;
    private int myOrder;

	public MarkovWord(int order) {
		this.myOrder= order;
		myRandom = new Random();
		
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String text){
		myText = text.split("\\s+");
	
	}

	public int indexOf(String[] words,WordGram target,int start){
		for(int k=start;k<words.length;k++){
			if(words[k].equals(target.toString())){
				return k;
			}
		}
		return -1 ;	
	}

	public ArrayList<String> getFollows(WordGram kGram){
		ArrayList<String> list = new ArrayList<String>();
		int index = indexOf(myText, kGram, 0);

        while (index!=-1) {
            list.add(myText[index+1]);
            index = indexOf(myText, kGram,index+1);
        }
		return list;
	}	

	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		int index = myRandom.nextInt(myText.length-myOrder);
		System.out.println(myText.toString());
		WordGram wg= new WordGram(myText,index,myText.length-myOrder);
		String key=myText[index];
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		for(int k=0; k < numChars-myOrder; k++){
			
			ArrayList<String> follows= getFollows(wg);
			if(follows.size()==0){
				break;
			}
			index= myRandom.nextInt(follows.size());
			String next=follows.get(index);
			sb.append(next);
			wg.shiftAdd(next);
			//key=next;
		}
		
		return sb.toString().trim();
	}
}
