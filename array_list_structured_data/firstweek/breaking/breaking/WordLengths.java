
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {

	public void countWordlengths(FileResource resource, int[] counts){
		for(String word : resource.words()){
			word = word.toLowerCase();			
			int wordlength = word.length(); 
			if (Character.isLetter(word.charAt(word.length()-1))==false){
				wordlength --;
			}
			if (wordlength >= counts.length) {  	    	   
          		wordlength = counts.length - 1;  	  	
     		} 
			if (wordlength > 0 ) {  	   	   
    	      counts[wordlength] ++;  	  	
     		}
			
		}
	}
	public int indexOfMax(int[] values){
		//int [] tmp = int[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
		int [] tmp = new int[31];
		for (int i=0;i<31;i++){
			tmp[i]=i;
		} 
		for (int k=0; k<31; k++){
			for (int j=k+1;j<31;j++){
				if (values[k]>values[j]){
					int temp =values[k];
					int tempk =tmp[k];
					values[k]=values[j];
					tmp[k]=tmp[j];
					values[j]=temp;
					tmp[j]=tempk;				
				}	
			}
		}
		//return values[30];
		return tmp[30];		
		/*for (int k=0;k<31;k++){
			for (int j=k+1;j<31;j++){
				if(values[k]>values[j]){
					int temp = k;
				}
			}
		}
		return temp;*/
	}
	public void testCountWordLengths(){
		FileResource fr = new FileResource();
		int[] counts = new int[31];
		countWordlengths(fr,counts);		
		for(int k=0; k < 31; k++){
			if (counts[k] != 0){
				System.out.println(counts[k]+" word of length "+k);
			}
		}
		System.out.println("the most common word length appears "+indexOfMax(counts)+" times");		
	}
	

}

      	
     


