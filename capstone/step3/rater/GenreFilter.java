
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class GenreFilter implements Filter{
    private String myGenre;
    
    public GenreFilter(String genre){
        myGenre = genre;
    }

    @Override
    public boolean satisfies(String id){
        boolean isGenre=false;
        if(MovieDatabase.getGenres(id).indexOf(", ")==-1){
            isGenre= MovieDatabase.getGenres(id).equals(myGenre);
            return isGenre;
        }
        else{
            String[] genreArray = MovieDatabase.getGenres(id).split(", ");
            //String gA=Arrays.toString(genreArray);
            int i=genreArray.length-1;
            while(i>=0){
            	isGenre= genreArray[i].equals(myGenre);
            	if (isGenre!=false){
                	break;
                }
                i--;          
          }                              
          return isGenre;      
    	}
	}
}

