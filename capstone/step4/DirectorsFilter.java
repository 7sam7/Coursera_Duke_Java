
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class DirectorsFilter implements Filter {
	private String myDirectors;

	public DirectorsFilter(String directors){
		myDirectors = directors;
	}
	@Override
	public boolean satisfies(String id){
	    String[] directorsArray = myDirectors.split(",");
		if(MovieDatabase.getDirector(id).indexOf(",")!=-1){
			String[] myDirectorsArray = MovieDatabase.getDirector(id).split(", ");
			for(int j=0;j<directorsArray.length;j++){
				for(int k=0;k<myDirectorsArray.length;k++){
					if (myDirectorsArray[k].equals(directorsArray[j])){
						return true;
					}
				}
			}
		}else{
			for(int i=0;i<directorsArray.length;i++){
				if(directorsArray[i].equals(MovieDatabase.getDirector(id))){
					return true;
				}
			}
		}		
		return false;
	}
}
