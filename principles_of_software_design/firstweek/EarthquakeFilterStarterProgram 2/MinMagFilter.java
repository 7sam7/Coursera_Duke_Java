
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class MinMagFilter implements Filter
{
    private double magMin;
    private double magMax; 
    
    public MinMagFilter(double min,double max) { 
        magMin = min;
        magMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getMagnitude() >= magMin ) && (qe.getMagnitude()<= magMax); 
    } 

}
