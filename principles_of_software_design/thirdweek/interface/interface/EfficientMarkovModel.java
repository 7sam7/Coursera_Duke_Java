
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private HashMap<String,ArrayList<String>> map;
    
    public EfficientMarkovModel(int myOrder) {
        super(myOrder);      
        map= new HashMap<String,ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    private String getKey(int index) {
        return myText.substring(index, index + myOrder);
    }

    private String getFollowingLetter(int index) {

        return myText.substring(index+myOrder, index+myOrder+1);
    }

    @Override
    public void setTraining(String s){
        super.setTraining(s);
        buildMap();
        printHashMapInfo();

    }

    public void buildMap(){
        for (int i = 0; i < myText.length() - myOrder; i++) {

            String key = getKey(i);
            String follow = getFollowingLetter(i);

            if (map.containsKey(key)) {
                map.get(key).add(follow);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                map.put(key, list);
            }
        }

    }

    @Override
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }

    public String getRandomText(int numChars){
        if (myText == null) {
            return "";
        }
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
    public void printHashMapInfo() {
       
        System.out.printf("Map size:\t%d\nMax size:\t%d\n", mapSize(), longestFollowsSize());
    }
    
    public int mapSize() {
        return map.size();
    }

    public int longestFollowsSize() {
        int maxSize = 0;
        for (String key : map.keySet()) {
            maxSize = Math.max(maxSize, map.get(key).size());
        }

        return maxSize;
    }

    @Override
    public String toString() {
        return "Efficient Markov Model order " + myOrder;
    }
}
