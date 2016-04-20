import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry q : quakeData){
            if (q.getMagnitude() > magMin){
                answer.add(q);
            }
        } 
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry q: quakeData ){
            if (from.distanceTo(q.getLocation()) < distMax){
                answer.add(q);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth,
    double maxDepth){
        System.out.println("Find quakes with depth between "+minDepth+" and "+maxDepth);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry q: quakeData){
            if (q.getDepth() > minDepth && q.getDepth() < maxDepth){
                answer.add(q);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where,
    String phrase){
        ArrayList<QuakeEntry> answer= new ArrayList<QuakeEntry>();
        for (QuakeEntry q : quakeData){
            if(where == "start"){
                if(q.getInfo().startsWith(phrase)){
                    answer.add(q);
                }
            }
            else if (where == "any" ){
                if (q.getInfo().indexOf(phrase)!=-1){
                    answer.add(q);
                }
            }
            else if (where == "end"){
                if (q.getInfo().endsWith(phrase)){
                    answer.add(q);
                }
            }                    
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> answer= filterByMagnitude(list,5.0);
        System.out.println("read data for "+list.size()+" quakes"); 
        for (QuakeEntry q : answer){
            System.out.println(q);
        }
        //System.out.println(answer);
        System.out.println("Found "+ answer.size()+" quakes that match the criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> answer=filterByDistanceFrom(list,1000000,city);
        for (QuakeEntry q : answer){
            System.out.println(city.distanceTo(q.getLocation())+" "+q.getInfo());
        }
        System.out.println("Found "+ answer.size()+" quakes that match the criteria");
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> answer = filterByDepth(list,-4000.0,-2000.0);
        for (QuakeEntry q : answer){
            System.out.println(q);
        }
         System.out.println("Found "+ answer.size()+" quakes that match the criteria");
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String phrase= "Can";
        String where = "any";
        ArrayList<QuakeEntry> answer = filterByPhrase(list,where,phrase);
        for (QuakeEntry q : answer){
            System.out.println(q);
        }
         System.out.println("Found "+ answer.size()+" quakes that match");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
