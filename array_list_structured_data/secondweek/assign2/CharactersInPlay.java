
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CharactersInPlay {
	private ArrayList<String> myChar;
	private ArrayList<Integer> myFreqs;

	public CharactersInPlay(){
		myChar = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}

	public void update(String person){
		int index = myChar.indexOf(person);
		if (index == -1){
			myChar.add(person);
			myFreqs.add(1);
		}
		else{
			int value = myFreqs.get(index);
			myFreqs.set(index,value+1);
		}
	}

	public void findAllCharacters(){
		myFreqs.clear();
		myChar.clear();
		int start = 0;
		FileResource fr = new FileResource();
		for (String line : fr.lines()){
			if (line.contains(".")){
				int dot = line.indexOf(".",start);
				String person = line.substring(0,dot);
				update(person);
			}
		}
	}

	public int findIndexOfMax(){
		
		int [] tmp = new int[myFreqs.size()];
		for (int i=0;i<myFreqs.size();i++){
			tmp[i]=i;
		} 
		for (int k=0; k<myFreqs.size(); k++){
			for (int j=k+1;j<myFreqs.size();j++){
				if (myFreqs.get(k)>=myFreqs.get(j)){					
					int tempk =tmp[k];
					tmp[k]=tmp[j];
					tmp[j]=tempk;				
				}	
			}
		}
		//return values[30];
		return tmp[myFreqs.size()-1];
	}

	public void tester(){
		findAllCharacters();
		for(int i=0;i<myChar.size();i++){
			if (myFreqs.get(i)>50){
				System.out.println("main Character is "+myChar.get(i)+"\t"+"number of speaking is "+myFreqs.get(i));
			}
		}
		charactersWithNumParts(10,15);
		//int m = findIndexOfMax();
		//System.out.println("main Character is "+myChar.get(m)+"\t"+"number of speaking is "+myFreqs.get(m));

	}

	public void charactersWithNumParts(int num1, int num2){
		for (int i=0;i<myFreqs.size();i++){
			if((myFreqs.get(i)>=num1)&&(myFreqs.get(i)<=num2)){
				System.out.println(myChar.get(i)+"\t"+myFreqs.get(i));
			}
		}
	}
}
