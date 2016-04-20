
/**
 * Write a description of WordInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordInFiles {
	private HashMap<String,ArrayList<String>> wordmap;

	public WordInFiles(){
		wordmap = new HashMap<String,ArrayList<String>>();
	}


	private void addWordsFromFile(File f){
		FileResource fr = new FileResource(f);						
		for(String word :fr.words()){		
			if(!wordmap.containsKey(word)){
				ArrayList<String> wordlist = new ArrayList<String>();
				wordlist.add((f.getName()));
				wordmap.put(word,wordlist);
			}
			else{
				if(! wordmap.get(word).contains(f.getName())){
					wordmap.get(word).add(f.getName());
				}
			}
		
		}	
	}

	public void buildWordFileMap(){
		wordmap.clear();
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()){
			addWordsFromFile(f);
		}
	}


	public int maxNumber(){
		/*int tmp=0;
		for(int i=0;i<wordmap.size();i++){       
           int j=i+1;  
           tmp=i;  
           int temp=wordmap.get(i).size(); 
           ArrayList<String> tempw = wordmap.get(i); 
           for(;j<wordmap.size();j++){  
              if(wordmap.get(j).size()<temp){  
                 temp=wordmap.get(j).size();
                 tempw=wordmap.get(j);  
                 tmp=j;  
              }  
           }  
           wordmap.put(tmp,wordmap.get(i));  
           wordmap.put(i,tempw);    
       }
		return tmp;*/
		ArrayList<Integer> size = new ArrayList<Integer>();
		for(ArrayList list : wordmap.values()){
			size.add(list.size());
		}
		Collections.sort(size);
		return size.get(size.size()-1);
	}

	public ArrayList wordsInNumFiles(int number){
		/*Object o = null;
		ArrayList all = new ArrayList();
		Set set=wordmap.entrySet();
  		Iterator it=set.iterator();
  		while(it.hasNext()) {
  		 	Map.Entry entry=(Map.Entry)it.next();
  		 	if(entry.getValue().equals(number)) {
  		  		 o=entry.getKey();
  		  		all.add(o);         
  		 	}
  		}
  		return all;*/
  		ArrayList<String> w = new ArrayList<String>();
  		for(String word : wordmap.keySet()){
  			if (wordmap.get(word).size() == number){
  				w.add(word);
  			}
  		}
  		return w;
	}

	public void printFilesIn(String word){

		ArrayList<String> f = wordmap.get(word);
		for (String s : f){
			System.out.println(s);
		}
	}

	public void tester(){
		buildWordFileMap();
		System.out.println(wordmap.get("tree"));
		ArrayList<String> al = wordsInNumFiles(4);
		System.out.println(al.size());
		//System.out.println(wordmap);
		 
	}

}
