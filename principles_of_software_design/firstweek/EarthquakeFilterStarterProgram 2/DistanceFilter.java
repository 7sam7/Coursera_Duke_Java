
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class DistanceFilter implements Filter{
	private Location loc;
	private double distMax;

	public DistanceFilter(Location location,double distance){
		loc = location;
		distMax = distance;
	}

	public boolean satisfies(QuakeEntry qe){
		return loc.distanceTo(qe.getLocation())<distMax;
	}
}
