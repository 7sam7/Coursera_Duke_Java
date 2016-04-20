
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
	
	public int compare(QuakeEntry q1,QuakeEntry q2){
		String[] part1= q1.getInfo().split(",");
		String[] part2=q2.getInfo().split(",");
		String q1l = Arrays.toString(part1);
		String q2l = Arrays.toString(part2); 
        String[] sub1=q1l.split(",  ");
        String[] sub2=q2l.split(",  ");
        String ss1=sub1[sub1.length-1];
        String ss2=sub2[sub2.length-1];
        String[] sss1=ss1.split("]");
        String[] sss2=ss2.split("]");
        String q1last=sss1[0];
        String q2last=sss2[0];
        System.out.println(q1last);
		if (q1last.compareTo(q2last)<0){
	        return -1;
	       }
	    if (q1last.compareTo(q2last)>0){
	        return 1;
	       }
	    if (q1last.compareTo(q2last)==0){
	    	if(q1.getMagnitude()< q2.getMagnitude()){
	    		return -2;
	    	}
	    	else if (q1.getMagnitude() > q2.getMagnitude()){
	    		return 2;
	    	}
	    	else if (q1.getMagnitude() == q2.getMagnitude()){
	    		if(q1.getDepth()< q2.getDepth()){
	    			return -3;
	    		}
	    		else if (q1.getDepth()>q2.getDepth()){
	    			return 3;
	    		}
	    	}
	    }
	    return 0;
	}
}
