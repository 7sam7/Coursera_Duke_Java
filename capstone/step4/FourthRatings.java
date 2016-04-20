
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
public class FourthRatings{

    private double getAverageByID(String movie_id,Integer minimalRaters){
        //set double to avoid round off;
        double numOfRaters=0.0;
        double sum=0.0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    private double dotProduct(Rater me,Rater r){
    	HashMap<String,Rating> movies= me.getMyRatings();
    	double dotP=0.0;
    	for(String id : movies.keySet()){
    		double scale=me.getRating(id)-5;
    		if (r.hasRating(id)){
    			dotP += scale*(r.getRating(id)-5);
    		}
    	}
    	return dotP;
    }
    private ArrayList<Rating> getSimilarities(String id){
    	ArrayList<Rating> similarRating = new ArrayList<Rating>();
    	Rater me = RaterDatabase.getRater(id);
    	for(Rater r : RaterDatabase.getRaters()){
    		if(!me.getID().equals(r.getID())){
    			double dotP=dotProduct(me,r);
    			if(dotP>0){
    				Rating rat = new Rating(r.getID(),dotP);
    				similarRating.add(rat);
    			}
    		}
    	}
    	Collections.sort(similarRating,Collections.reverseOrder());
    	//System.out.println(similarRating);
    	return similarRating;
    }
    private boolean hasMinRaters(String movie_id,Integer minimalRaters,Integer numSimilarRaters,ArrayList<Rating> ratingRater){
    	int numOfRaters=0;
    	int md_id = Integer.parseInt(movie_id);
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        //System.out.println(myRaters);
        for(int i=0;i<numSimilarRaters;i++){
        	Rater rater = RaterDatabase.getRater(ratingRater.get(i).getItem());
            HashMap<String,Rating> movieRated=rater.getMyRatings();
            for(String mo_id : movieRated.keySet()){
                int rm_id =Integer.parseInt(mo_id);
                if(rm_id==md_id){
                    numOfRaters+=1;
                    //System.out.println("training: "+ratingRater.get(i).getValue()+" original: "+rater.getRating(mo_id    ));
                }
            }
        }      
       // System.out.println(movie_id+" "+numOfRaters);
        if(numOfRaters>=minimalRaters){
        	return true;
        }
        else{
        	return false;
        }
    }
    public ArrayList<Rating> getSimilarRatings(String id, Integer numSimilarRaters,
    	Integer minimalRaters){
    	ArrayList<Rating> ratingMoive = new ArrayList<Rating>();
    	ArrayList<Rating> ratingRater = getSimilarities(id);
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //for(int j=0;j<numSimilarRaters;j++){
         //   System.out.println(ratingRater.get(j));
        //}
    	for(int j=0;j<movies.size();j++){
    		String movie_id = movies.get(j);
    		int md_id = Integer.parseInt(movie_id);
    		if (hasMinRaters(movie_id,minimalRaters,numSimilarRaters,ratingRater)){
    			double sum = 0.0;
    			double ave = 0.0;
    			double num = 0.0;
    			for(int i=0;i<numSimilarRaters;i++){
                    Rater rater = RaterDatabase.getRater(ratingRater.get(i).getItem());
    				HashMap<String,Rating> movieRated=rater.getMyRatings();
        			for(String mo_id : movieRated.keySet()){
        				int rm_id =Integer.parseInt(mo_id);
    					if(rm_id==md_id){
    						sum +=ratingRater.get(i).getValue()*rater.getRating(mo_id);
    						num += 1;
    						//System.out.println("training: "+ratingRater.get(i).getValue()+" original: "+rater.getRating(mo_id    ));
    					}
    				}			
    				//System.out.println(sum);
    			}
    			if(num!=0.0){
    				ave = sum/num;
    				Rating rating = new Rating(movie_id,ave);
    				ratingMoive.add(rating);
    			} 			
    		}	
    	}
    	Collections.sort(ratingMoive,Collections.reverseOrder());   	
    	return ratingMoive;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, Integer numSimilarRaters,
    	Integer minimalRaters,Filter filterCriteria){
   		ArrayList<Rating> ratingMoive = new ArrayList<Rating>();
    	ArrayList<Rating> ratingRater = getSimilarities(id);
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    	//System.out.println(movies);
    	for(int j=0;j<movies.size();j++){
    		String movie_id = movies.get(j);
    		int md_id = Integer.parseInt(movie_id);
    		if (hasMinRaters(movie_id,minimalRaters,numSimilarRaters,ratingRater)){
    			double sum = 0.0;
    			double ave = 0.0;
    			double num = 0.0;
    			for(int i=0;i<numSimilarRaters;i++){
    				Rater rater = RaterDatabase.getRater(ratingRater.get(i).getItem());
    				HashMap<String,Rating> movieRated=rater.getMyRatings();
        			for(String mo_id : movieRated.keySet()){
        				int rm_id =Integer.parseInt(mo_id);
    					if(rm_id==md_id){
    						sum +=ratingRater.get(i).getValue()*rater.getRating(mo_id);
    						num += 1.0;
    						//System.out.println("training: "+ratingRater.get(i).getValue()+" original: "+rater.getRating(movie_id));
    					}
    				}			
    				//System.out.println(sum);
    			}
    			if(num!=0.0){
    				ave = sum/num;
    				Rating rating = new Rating(movie_id,ave);
    				ratingMoive.add(rating);
    			} 			
    		}	
    	}
    	Collections.sort(ratingMoive,Collections.reverseOrder());   	
    	return ratingMoive;
    }
}


