
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public Integer getLargestDepth(ArrayList<QuakeEntry> quakes,int from){
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for (int i=0; i< 70; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }   
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakes,int numSorted){
        //System.out.println("Printing Quakes after pass"+numSorted+": ");
        for(int i=0;i<quakes.size()-numSorted-1;i++){
            //for(int j=i;j<quakes.size();j++){
            if(quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude()){
                    //QuakeEntry qi = quakes.get(i);
                    //QuakeEntry qmax = quakes.get(j);
                QuakeEntry temp = quakes.get(i);
                quakes.set(i,quakes.get(i+1));
                quakes.set(i+1,temp);
                }
            }
         
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        int time=0;
        while (time<in.size()-1){
            onePassBubbleSort(in,time);
            time=time + 1;
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
            }
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        boolean flag=false;
        int j=0;
        for (int i=0;i<quakes.size()-1;i++){
            if(quakes.get(i).getMagnitude()<=quakes.get(i+1).getMagnitude()){
                j+=1;
            }
        }        
        if (j==quakes.size()-1){
            flag=true;
        }
        return flag;
    }

    public int sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int time=0;
        while (time<in.size()-1){
            onePassBubbleSort(in,time); 
            time=time + 1;      
            if (checkInSortedOrder(in)==true){
                /*for (QuakeEntry qe: in) { 
                    System.out.println(qe);
                }*/
                break;
            }
            
        }
        return time;
    }

    public int sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int i=0;
        while (i< in.size()-1) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            i=i+1;
            if (checkInSortedOrder(in)==true){
                /*for (QuakeEntry qe: in) { 
                    System.out.println(qe);
                }*/
                break;
            }
        }     
        return i;
    }
    


    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //FileResource fr = new FileResource();
        //String source = fr.asString();
        //String source = "data/earthquakeDataSampleSix1.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source ="data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");
        /*for (QuakeEntry q: list) { 
            System.out.println(q);
        }*/     
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        int time =sortByMagnitudeWithBubbleSortWithCheck(list);
        //int time = sortByMagnitudeWithCheck(list);
        System.out.println("EarthQuakes in sorted order: ");
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } */
        System.out.println(time);
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
