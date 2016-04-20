
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class LargestQuakes {
	
	public void findLargestQuakes(){
		EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list = parser.read(source);
    	System.out.println("read data for "+list.size()+" quakes");
    	int loc=indexOfLargest(list);
    	ArrayList<QuakeEntry> answer = getLargest(list,50);
    	for (int k=0;k<answer.size();k++){
    		QuakeEntry entry = answer.get(k);
    		System.out.println(entry);
    	}
    	 System.out.println("number found: "+answer.size());
	}

	public int indexOfLargest(ArrayList<QuakeEntry> data){
		int loc=0;
		
		return loc;
	}

	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
		ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
		for (int j=0;j<howMany;j++){
            int minIndex = 0;
            for (int k=1; k<copy.size();k++){
                QuakeEntry quake = copy.get(k);
                double mag = quake.getMagnitude();
                if(mag > copy.get(minIndex).getMagnitude()){
                    minIndex = k;
                }
            }
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
		return ret;
	}
}
