
/**
 * Write a description of SecondRatings here.
 * 
 * @author zhe yuan 
 * @version (2016.03.28)
 */

import java.util.*;
import edu.duke.*;
public class SecondRatings{
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmovies_short.csv", "ratings_short.csv");
    }

    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings fR=new FirstRatings();
        myMovies=fR.loadMovies(moviefile);
        myRaters=fR.loadRaters(ratingsfile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public void print(){
        System.out.println(myRaters);
    }

    private double getAverageByID(String movie_id,Integer minimalRaters){
        //set double to avoid round off;
        double numOfRaters=0.0;
        double sum=0.0;
        for (Rater rater:myRaters){
            if(rater.hasRating(movie_id)){
                numOfRaters+=1;
            }
        }
        if(numOfRaters>=minimalRaters){
            for(Rater rater:myRaters){
                if(rater.hasRating(movie_id)){
                    sum+=rater.getRating(movie_id);
                }
            }
            double ave=sum/numOfRaters;
            return ave;
        }else{
            return 0.0;
        }
    }

    public ArrayList<Rater> uniqueRatings(){
        ArrayList<Rater> ratings_short= new ArrayList<Rater>();
        for(Rater ra:myRaters){
            HashMap<String,Rating> myRatings= ra.getMyRatings();
            for(String rat:myRatings.keySet()){
                if(!ratings_short.contains(rat)){
                    ratings_short.add(ra);
                }
            }
        }
        System.out.println(ratings_short);
        return ratings_short;
    }
    public ArrayList<Rating> getAverageRatings(Integer minimalRaters){
        ArrayList<Rating> rating= new ArrayList<Rating>();
        Rating rat= new Rating("",0.0);
        for (Movie movie:myMovies){
            String movie_id=movie.getID();
            if (getAverageByID(movie_id,minimalRaters)!=0){
                rat=new Rating(getTitle(movie_id),getAverageByID(movie_id,minimalRaters));                    
                rating.add(rat); 
            } 
        }
        
        Collections.sort(rating);
        return rating;
    }

    public double averageRatingOneMovie(String movie_id){
        //set double to avoid round off;
        double numOfRaters=0.0;
        double sum=0.0;
        for (Rater rater:myRaters){
            if(rater.hasRating(movie_id)){
                numOfRaters+=1;
            }
        }

        for(Rater rater:myRaters){
            if(rater.hasRating(movie_id)){
                sum+=rater.getRating(movie_id);
            }
        }
        double ave=sum/numOfRaters;
        return ave;
    }


    public String getTitle(String movie_id){
        String title="OOPS! movie not found";
        for(Movie movie:myMovies){
            if(movie.getID().equals(movie_id)){
                title= movie.getTitle();
            }   
        }
        return title;
    }

    public String getID(String title){
        String id="NO SUCH TITLE";
        for(Movie movie: myMovies){
            if(movie.getTitle().equals(title)){
                id=movie.getID();
            }
        }
        return id;
    }

}
