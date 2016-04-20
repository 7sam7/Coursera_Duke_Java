
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
        records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             //System.out.println(le);
            System.out.println(le.getAccessTime().toString()); 
         }
     }

     public int countUniqueIPs(){
        ArrayList<String> unique= new ArrayList<String>();
        for (LogEntry lg : records){
            if (!unique.contains(lg.getIpAddress())){
                unique.add(lg.getIpAddress());
            }
        }
        return unique.size();
     }
     

     public ArrayList<Integer> printAllHigherThanNum(int num){
        ArrayList<Integer> unique=new ArrayList<Integer>();
        for (LogEntry lg : records){
            if (!unique.contains(lg.getStatusCode())){
                unique.add(lg.getStatusCode());               
            }           
        }
        for (int i:unique){
            if (i>=num){
                    System.out.println(i);
            }
        }
        return unique;
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> unique= new ArrayList<String>();
        for (LogEntry lg : records){
            if (lg.getAccessTime().toString().contains(someday)){
                if (!unique.contains(lg.getIpAddress())){
                 unique.add(lg.getIpAddress());
                }
            }
        }
        return unique;
     }

     public Integer countUniqueIPsInRange(Integer low,Integer high){
        ArrayList<String> stat= new ArrayList<String>();
        for (LogEntry lg:records){
            if((lg.getStatusCode()>=low) && (lg.getStatusCode()<=high)){
                if (!stat.contains(lg.getIpAddress())){
                    stat.add(lg.getIpAddress());                   
                }                 
            }      
        }
        return stat.size();
        //return stat;
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry lg : records){
            String ip =lg.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else{
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
     }

     public HashMap<String,Integer> countVisitsPerIPFromList(ArrayList<String> ips){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (String ip : ips){
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else{
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
     }

     public Integer mostNumberVisitsByIP(HashMap<String,Integer> counts){
        Integer most=0;
        for (Integer v : counts.values()){
            if(most <= v){
                most = v;
            }
        }
        return most;   
     }

     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts,Integer max){
        ArrayList<String> mostVisit=new ArrayList<String>();
        for (String s: counts.keySet()){
            if (counts.get(s)==max){
                mostVisit.add(s);
            }
        }
        return mostVisit;

     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> counts = new HashMap<String,ArrayList<String>>();
        for(LogEntry lg : records){
            String date = lg.getAccessTime().toString().substring(4,10);
            if(!counts.containsKey(date)){
                ArrayList<String> ip = new ArrayList<String>();
                ip.add(lg.getIpAddress());
                counts.put(date,ip);
            }
            else{
                ArrayList<String> a=counts.get(date);
                a.add(lg.getIpAddress());
                counts.put(date,a);
            }
        }
        return counts;
     }

     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> counts){
        String d="";
        int sum=0;
        for (ArrayList<String> ip : counts.values()){
            if (ip.size()>=sum){
                sum=ip.size();
            }
        }
        for(String day: counts.keySet()){
            if(counts.get(day).size()==sum){
                d=day;
            }
        }
        return d;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts,String date){
        ArrayList<String> ipList= new ArrayList<String>();

        for(String day:counts.keySet()){
            if(day.equals(date)){
                ipList=counts.get(day);
            }
        }
        HashMap<String,Integer> count=countVisitsPerIPFromList(ipList);
        Integer most = mostNumberVisitsByIP(count);
        ArrayList<String> mostVisit=iPsMostVisits(count,most);

        return mostVisit;
     }

}
