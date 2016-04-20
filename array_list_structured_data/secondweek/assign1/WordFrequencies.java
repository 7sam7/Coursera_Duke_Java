
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (zhe yuan) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordFrequencies {

	private  ArrayList<String> myWords; 
	private ArrayList<Integer> myFreqs;
	//private int [] tmp;
	public WordFrequencies(){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
		//tmp = new int[myWords.size()];
	}

	public void findUnique(){
		myWords.clear();
		myFreqs.clear();
		FileResource fr = new FileResource();
		for (String w : fr.words()){
			w = w.toLowerCase();
			int index = myWords.indexOf(w);
			if (index == -1){
				myWords.add(w);
				myFreqs.add(1);
			}
			else{
				int value = myFreqs.get(index);
				myFreqs.set(index,value+1);
			}
		}
		System.out.println("Number of unique words: "+ myWords.size());
	}

	public void tester(){
		findUnique();		
		//for (int i=0;i<myWords.size();i++){
		//	System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
		//}
		int m = findIndexOfMax();
		//findIndexOfMax();
 		//Collections.sort(myFreqs);
		//System.out.println(myWords.get(myWords.size()-1)+"\t"+myFreqs.get(myWords.size()-1));
		System.out.println("The word that occurs most often and its count are: "+myWords.get(m)+"\t"+myFreqs.get(m));
		//System.out.println("number of unique words is "+myWords.size());
		//System.out.println(myWords.get(myWords.indexOf("the"))+"\t"+myFreqs.get(myWords.indexOf("the")));
		//System.out.println(myWords.indexOf("the")+"\n"+myWords.indexOf("thou"));
	}

	public int findIndexOfMax(){
		
		int tmp=0;
	
		for(int i=0;i<myFreqs.size();i++){       
           int j=i+1;  
           tmp=i;  
           int temp=myFreqs.get(i); 
           String tempw = myWords.get(i); 
           for(;j<myFreqs.size();j++){  
              if(myFreqs.get(j)<temp){  
                 temp=myFreqs.get(j);
                 tempw=myWords.get(j);  
                 tmp=j;  
              }  
           }  
           myFreqs.set(tmp,myFreqs.get(i));  
           myFreqs.set(i,temp); 
           myWords.set(tmp,myWords.get(i));  
           myWords.set(i,tempw); 
       }
		//return values[30];
		return tmp;
	}

}
