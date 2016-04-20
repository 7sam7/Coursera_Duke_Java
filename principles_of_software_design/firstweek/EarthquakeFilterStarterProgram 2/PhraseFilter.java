
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class PhraseFilter implements Filter{
    private String where;
	private String name;

	public PhraseFilter(String w,String n){
		where = w;
		name = n;
	} 

	public boolean satisfies(QuakeEntry qe){
		boolean flag=false;
		if (where == "start"){
			if(qe.getInfo().startsWith(name)){
				flag= true;
			}
		}
		else if (where == "any"){
			if(qe.getInfo().indexOf(name)!=-1){
				flag=true;
			}
		}
		else if (where == "end"){
			if(qe.getInfo().endsWith(name)){
				flag = true;
			}
		}
		return flag;
	}
}
