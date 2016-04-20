
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //Collections.sort(list);
        for(int k=0;k<list.size();k++){
            int minIdx=k;
            for(int j=k+1;j<list.size();j++){
                if(list.get(j).compareTo(list.get(minIdx))<0){
                    minIdx=j;
                }
                //list.get(j).compareTo(list.get(minIdx))                  
            }
            Collections.swap(list,k,minIdx);
        }
        /*for(QuakeEntry qe: list) {
            System.out.println(qe);
        }*/
        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
       System.out.println(list.get(quakeNumber)+"\n");
       /* for(int a=0;a<15;a++){
            System.out.println(list.get(a));
    }*/
    }    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }

    public void sortByTitleAndDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list,new TitleAndDepthComparator());
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber)+"\n");
      /* String[] s=list.get(quakeNumber).getInfo().split(",");
        String s1=Arrays.toString(s); 
        String[] s2=s1.split("  ");
        String s5=s2[1];
        String[] s3=s5.split("]");
        String s4=s3[0];
        System.out.println(s4);*/
    }

    public void sortByLastWordInTitleThenByMagnitude(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //Collections.sort(list,new TitleAndDepthComparator());
        Collections.sort(list,new TitleLastAndMagnitudeComparator());
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber)+"\n");
        for(int a=490;a<515;a++){
            System.out.println(list.get(a));
    }
    }











}
