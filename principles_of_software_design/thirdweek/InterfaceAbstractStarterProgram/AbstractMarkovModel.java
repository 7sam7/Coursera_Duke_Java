
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int myOrder;
    
    public AbstractMarkovModel(int order) {
        super(order);
        myRandom = new Random();
        this.myOrder= order;
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }

    private boolean matchKey(String key, int pos) {

        return key.equals(myText.substring(pos, pos + myOrder));

    }

    abstract public String getRandomText(int numChars);

    private String getFollowingLetter(int pos) {
        // index of letter following key
        int index = pos+myOrder;
        return myText.substring(index, index+1);
    }

	protected ArrayList<String> getFollows(String key){
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

	@Override
    public String toString() {
        return "Markov Model order " + myOrder;
    }


}
