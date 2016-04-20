
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
public class ThirdRatings{
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings_short.csv");
    }

    public ThirdRatings(String ratingsfile){
        FirstRatings fR=new FirstRatings();
        myRaters=fR.loadRaters(ratingsfile);
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
        //System.out.println("numOfRaters "+numOfRaters);
        if(numOfRaters>=minimalRaters){
            for(Rater rater:myRaters){
                if(rater.hasRating(movie_id)){
                    sum+=rater.getRating(movie_id);
                }
            }
            //System.out.println("sum "+ sum);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        Rating rat= new Rating("",0.0);
        for (String movie_id:movies){
            if (getAverageByID(movie_id,minimalRaters)!=0){
                rat=new Rating(movie_id,getAverageByID(movie_id,minimalRaters));                    
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

    public ArrayList<Rating> getAverageRatingsByFilter(Integer minimalRaters,
        Filter filterCriteria){
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        //System.out.println(movies);  
        Rating rat= new Rating("",0.0);
        for (String movie_id:movies){
            if (getAverageByID(movie_id,minimalRaters)!=0){
                rat=new Rating(movie_id,getAverageByID(movie_id,minimalRaters));                    
                rating.add(rat); 
                //System.out.println(rating);
            }
          //  else{
          //     System.out.println(movie_id);
          //  }
        }
        
        Collections.sort(rating);
        //System.out.println(rating); 
        return rating;
    }

}

