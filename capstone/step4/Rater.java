
/**
 * Write a description of Rater here.
 * interface of rater
 * @author (yz) 
 * @version (step3.0)
 */
import edu.duke.*;
import java.util.*;
public interface Rater {

    public void addRating(String item,double rating);

    public boolean hasRating(String item);

    public String getID();

    //public ArrayList<Rating> getMyRatings();
    
    public HashMap<String,Rating> getMyRatings();
    
    public int numRatings();

    public ArrayList<String> getItemsRated();

    public String toString();

    public double getRating(String item);


}


