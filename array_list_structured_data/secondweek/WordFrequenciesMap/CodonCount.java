
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {

	private HashMap<String,Integer> map;

	public CodonCount(){
		map = new HashMap<String,Integer>();
	}

	public void buildCodonMap(String dna,int start){
		String dna_s=dna.substring(start);		
		if (dna_s.length() % 3 == 0){
			for(;start<dna_s.length();start+=3){
				if ((start+3) <= dna_s.length()){
					String dna_c = dna_s.substring(start,start+3);
		    		if (!map.containsKey(dna_c)){
						map.put(dna_c,1);
					}
					else {
						map.put(dna_c,map.get(dna_c)+1);
					}
				}
			}
		}
		else if (dna_s.length() % 3 == 1){
			for(;start<dna_s.length();start+=3){
				if ((start+3) <= dna_s.length()){
					String dna_c = dna_s.substring(start,start+3);
		    		if (!map.containsKey(dna_c)){
						map.put(dna_c,1);
					}
					else {
						map.put(dna_c,map.get(dna_c)+1);
					}
				}
			}
	    }
	    else if (dna_s.length() % 3 == 2){
	    	for(;start<dna_s.length();start+=3){
				if ((start+3)<=dna_s.length()){
					String dna_c = dna_s.substring(start,start+3);
		    		if (!map.containsKey(dna_c)){
						map.put(dna_c,1);
					}
					else {
						map.put(dna_c,map.get(dna_c)+1);
					}
				}
			}
	    }
			
		
	}

	public void getMostCommonCodon(){
		List<Map.Entry<String,Integer>> list =
    		new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
    	public int compare(Map.Entry<String, Integer> o1,
            Map.Entry<String, Integer> o2) {
        return (o2.getValue() - o1.getValue());
		}
	});
		LinkedHashMap<String,Integer> sortedMapByValue = new LinkedHashMap<String,Integer>();
		for(int i=0;i<list.size();i++) {		
			Map.Entry<String, Integer> x = list.get(i);
			sortedMapByValue.put(x.getKey(), x.getValue());
				
	}
	System.out.println(sortedMapByValue);
	//return sortedMapByValue.getKey();	
}

	public void printCodonCounts(int start,int end){
		System.out.println("Counts of codons between"+start+" and "+end+" inclusive are:"+"\n");
		for (String w : map.keySet()){
			int value = map.get(w);
			if((value>=start)&&(value<=end)){
				System.out.println(w+"\t"+value);
			}
		}
	}

	public void tester(){
		FileResource fr = new FileResource();
		String w = fr.asString();
		for (int start=0;start<3;start++){
			map.clear();
			buildCodonMap(w,start);
			System.out.println("Reading frame starting with "+start+" results in "+map.size()+" unique codons");
			getMostCommonCodon();
			//String s=getMostCommonCodon();
			//System.out.println("most common codon is "+s+"with count "+ map.get(s));
			printCodonCounts(6,6);
		}
	}


}
