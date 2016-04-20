/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    /*public String findProtein(String dna) {
        String dna_l = dna.toLowerCase();
        int start = 0;
        while (true){
            int loc = dna_l.indexOf("atg",start);
            if (loc == -1) {
                break;
            }
        }
        start = loc +3;
        int stop = dna_l.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna_l.substring(start, stop+3);
        }
        else{
            int stop1 = dna_l.indexOf("tga",start+3);
            if ((stop1-start) % 3 == 0){
                return dna_l.substring(start,stop1+3);
            }       
            else{
                int stop2 = dna_l.indexOf("taa",start+3);
                if ((stop2-start) % 3 == 0){
                    return dna_l.substring(start,stop2+3);
                }
                else {
                    return "";
                }
            }           
        }
    }*/
    /*public void findStart(String dna){
        int start = 0;
        while (true){
            int loc = dna.indexOf("atg",start);
            if (loc == -1){
                break;
            }       
        String dna_s = dna.substring(loc,(dna.length()-1));
        int loc_n = printAll(dna_s);
        if (loc_n == -1){
            start = loc + 3;
            }
        else{
            start=loc + loc_n;
            }
        }   
    }*/

    public int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("tga",index);
        if (stop1 == -1 || (stop1-index) % 3 !=0){
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("taa",index);
        if (stop2 == -1 || (stop2-index) % 3 !=0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("tag",index);
        if (stop3 == -1 || (stop3-index) % 3 !=0){
            stop3 = dna.length();
        }
        
        return Math.min(stop1,Math.min(stop2,stop3));
        
        //return Math.min(stop1,Math.min(stop2,stop3));
    }
            


    public void printAll(String dna){
        int start = 0;//find atg
        int index = 0;
        int start1 = 0;  //very important,read substring from 0                
        while (true){
            int loc = dna.indexOf("atg",start);
            if (loc == -1){
                break;
            }       
            String dna_s = dna.substring(loc,(dna.length()-1));
            //loc_n is the size of stop
            int loc_n = findStopIndex(dna_s,index);
            if (loc_n != dna_s.length()){
                System.out.println(dna_s.substring(start1,loc_n+3));
                start=loc+loc_n;
            }
            else{
                start = loc + 3;
            }
        }
    }

    public StorageResource storeAll(String dna){
        int start = 0;
        int start1 = 0;
        StorageResource sr = new StorageResource();                  
        while (true){
            int loc = dna.indexOf("atg",start);
            if (loc == -1){
                break;
            }      
            //String dna_s = dna.substring(loc,(dna.length()-1));
            int loc_n = findStopIndex(dna,loc+3);
            if (loc_n != dna.length()){
                //System.out.println(dna_s.substring(start1,loc_n+3));
                sr.add(dna.substring(loc,loc_n+3));
                start=loc_n+3;
            }
            else{
                start = loc + 3;
            }
        }
        return sr;
    }

    public float cgRatio(String dna){
    	int cgcount = 0;
		for( int i=0; i<dna.length(); i++ ) {
   			 if( dna.charAt(i) == 'c' || dna.charAt(i) == 'g' ) {
       				cgcount++;
  			} 
		}
    	return ((float)cgcount)/dna.length();
    }

    public void printGenes(StorageResource sr){
    	int lcount = 0;
    	int hcount = 0;
    	for(String gene : sr.data()){
       		if (gene.length() > 200) {
           		System.out.println(gene.length()+"\t"+gene);
           		lcount+=1;
			} 
			if (cgRatio(gene) > 0.35) {
           		//System.out.println(gene.length()+"\t"+gene);
           		hcount+=1;
			}
		}
		System.out.println("the number of Strings longer than 60 char is "+lcount);
		//System.out.println("the number of Strings cgRatio higher than 0.35 is "+hcount);
    }

    public void ctgcount(String dna){
    	int ctgcount=0;
    	int start = 0;
    	while(true){
        	int loc=dna.indexOf("ctg",start);
        	if(loc==-1){
        		break;
        	}
        	ctgcount+=1;
        	start=loc+3;       	
        }
        System.out.println("number of CTG is "+ctgcount);
    }
    public void testStorageFinder(){   	
    	//DirectoryResource dr = new DirectoryResource();
    	StorageResource sr = new StorageResource();
        FileResource fr = new FileResource();
        //String dna = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        String dna = fr.asString();
        //System.out.println(dna.length());
        String dna_l = dna.toLowerCase();
        //ctgcount(dna_l);
        sr = storeAll(dna_l);        
        System.out.println(sr.data());        
        printGenes(sr);
        System.out.println(sr.size());
            //System.out.println("found " + result);
        
    }
    public void testFinder(){
        //String a = "ccatgccctaataaatgtctgtaatgtaga";
        String a = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        String dna_l = a.toLowerCase();     
        System.out.println("DNA string is " + "\n" + a);
        System.out.println("Gene found is ");
        //printAll(dna_l);
        StorageResource sr = new StorageResource();
        sr=storeAll(dna_l);
        System.out.println(sr.size());

    }

    /*public String stopCodon(String dna){
        String gene = findProtein(dna);
        if (gene.endsWith("tag")){
            return "tag";
        }
        else if (gene.endsWith("tga")){
            return "tga";
        }
        else if (gene.endsWith("taa")){
            return "taa";
        }
        else{
            return "";
        }
    }
    
    

    public void testing() {
        //String a = "cccatggggtttaaataataataggagagagagagagagttt";
        //String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ataaactatgttttaaatgt";
        //String ap = "ataaactatgttttaaatgt";
        //String a = "acatgataacctaag";
        //String ap = "acatgataacctaag";
        String a = "AATGCTAGTTTAAATCTGA";
        String au = "AATGCTAGTTTAAATCTGA";
        String ap = au.toLowerCase();
        String result = findProtein(a);
        String stopsign = stopCodon(result);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length() + " stopCodon is " + stopsign);
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
            String stopsign = stopCodon(result);
            System.out.println("stopsign is " + stopsign);
        }
    }*/
}
