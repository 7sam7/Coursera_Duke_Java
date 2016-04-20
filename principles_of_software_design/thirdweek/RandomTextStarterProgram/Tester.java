
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {

	public void testGetFollows(){
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		//String st = "this is a test yes this is a test.";
		markov.setTraining(st);
		ArrayList<String> list = markov.getFollows("he");
		System.out.println("list content: "+list+"\n"+"list size: "+list.size());
		//System.out.println("list size: "+list.size());
	}


}
