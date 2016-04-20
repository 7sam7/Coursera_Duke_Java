
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class DepthFilter implements Filter {
	private double depMin;
	private double depMax;

	public DepthFilter(double min,double max){
		depMin = min;
		depMax = max;
	}
	
	public boolean satisfies(QuakeEntry qe) { 
        return (qe.getDepth() >= depMin) && (qe.getDepth()<= depMax); 
    } 
}
