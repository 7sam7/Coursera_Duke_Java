import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MinMagFilter(3.5,4.5); 
        ArrayList<QuakeEntry> m7  = filter(list, f);
        Filter f2= new DepthFilter(-55000.0,-20000.0); 
        ArrayList<QuakeEntry> m8 = filter(m7,f2);
        /*Location city = new Location(39.7392, -104.9903);
        Filter f = new DistanceFilter(city,1000000);
        ArrayList<QuakeEntry> m7 = filter(list,f);
        Filter f2 = new PhraseFilter("end","a");
        ArrayList<QuakeEntry> m8 = filter(m7,f2);*/
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        }
        System.out.println(m8.size()); 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

    public void testMatchALLFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MinMagFilter(1.0,4.0));
        maf.addFilter(new DepthFilter(-180000.0,-30000.0));
        maf.addFilter(new PhraseFilter("any","o"));
        ArrayList<QuakeEntry> m8 = filter(list,maf);
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        }
        System.out.println(m8.size()); 
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Location city = new Location(55.7308, 9.1153);
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MinMagFilter(0.0,5.0));
        maf.addFilter(new DistanceFilter(city,3000000));
        maf.addFilter(new PhraseFilter("any","e"));
        ArrayList<QuakeEntry> m8 = filter(list,maf);
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        }
        System.out.println(m8.size()); 
        //String s=maf.getName();
        //System.out.println("Filters used are: "+s);
    }

}
