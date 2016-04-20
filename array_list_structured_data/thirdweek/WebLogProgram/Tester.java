
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer lg = new LogAnalyzer();     
        lg.readFile("short-test_log");
        lg.printAll();
    }

    public void testUniqueIP(){
    	LogAnalyzer lg = new LogAnalyzer();     
        lg.readFile("weblog2_log");
        System.out.println(lg.countUniqueIPs());
        //lg.printAllHigherThanNum(400);
    }

    public void testUniqueIPOnDay(){
    	LogAnalyzer lg = new LogAnalyzer();     
        lg.readFile("weblog2_log");
        System.out.println(lg.uniqueIPVisitsOnDay("Sep 27").size());
     	System.out.println(lg.countUniqueIPsInRange(200,299));   
    }

    public void testCounts(){
        LogAnalyzer lg = new LogAnalyzer();     
        lg.readFile("weblog2_log");
        //System.out.println(lg.countVisitsPerIP());
        HashMap<String,Integer> counts= lg.countVisitsPerIP();
        System.out.println(lg.mostNumberVisitsByIP(counts));
        Integer most=lg.mostNumberVisitsByIP(counts);
        System.out.println(lg.iPsMostVisits(counts,most));
    }

    public void testDate(){
        LogAnalyzer lg = new LogAnalyzer();     
        lg.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> counts=lg.iPsForDays();
        //System.out.println(counts);
        String day = lg.dayWithMostIPVisits(counts);
        System.out.println(day);
    }

    public void testMostIPInDay(){
        LogAnalyzer lg = new LogAnalyzer();     
        lg.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> counts=lg.iPsForDays();
        //String day = lg.dayWithMostIPVisits(counts);
        ArrayList<String> ips=lg.iPsWithMostVisitsOnDay(counts,"Sep 29");
        System.out.println(ips);
    }

}
