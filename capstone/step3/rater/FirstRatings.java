
/**
 * Write a description of FirstRatings here.
 * 
 * @author zhe yuan 
 * @version 03/15/2016
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    private int countG;
    private int countM;
    private int numOfRatingPerRaters;
    private int numOfRatingPerMovie;

    public FirstRatings(){
        countG=0;
        countM=0;
        numOfRatingPerRaters=0;
        numOfRatingPerMovie=0;
    }

    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies= new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);

        for(CSVRecord rec:fr.getCSVParser(false)){
            if(!rec.get(6).equals("minutes")){
                int minutes = Integer.parseInt(rec.get(6));
                Movie movie = new Movie(rec.get(0),rec.get(1),rec.get(2),rec.get(4),rec.get(5),rec.get(3),rec.get(7),minutes);
                movies.add(movie);
            }       
        }
        return movies;
    }

    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);

        for(CSVRecord rec:fr.getCSVParser(false)){
            if(!rec.get(0).equals("rater_id")){
                String item=rec.get(1);
                double value=Double.parseDouble(rec.get(2));
                String id =rec.get(0);
                if(raters.size()==0){
                    Rater rater = new EfficientRater(id);
                    rater.addRating(item,value);
                    raters.add(rater); 
                }else{
                    List<Rater> l= new ArrayList<Rater>(raters);
                    Iterator<Rater> it=l.iterator();
                    while(it.hasNext()){
                        Rater r=it.next();
                        if(r.getID().equals(id)){
                            r.addRating(item,value);
                            break;
                        }else if (!it.hasNext()){ 
                            Rater rater = new EfficientRater(id);
                            rater.addRating(item,value);
                            raters.add(rater);
                            break;
                        }
                    }
                }                       
            }                 
        }
       //System.out.println(raters.size());
       return raters;
    }

    public void testLoadRaters2(){
        ArrayList<Rater> raters= loadRaters("ratings_short.csv");
        System.out.println(raters);
    }
    //====================helper methods of Movies===============//
    public void countGenre(ArrayList<Movie> movies,String genre){
         for(Movie movie:movies){
            //test sepcific genre.
            if(movie.getGenres().contains(genre)){
                countG+=1;
            }
        }
    }

    public void countMinutes(ArrayList<Movie> movies,Integer minutes){
        for(Movie movie:movies){
            if(movie.getMinutes()>150){
                countM+=1;
            }
        }
    }
    public void directorWork(ArrayList<Movie> movies,HashMap<String,Integer> directorAndWork){
        for(Movie movie:movies){    
            if(movie.getDirector().contains(",")){
                String[] directors=movie.getDirector().split(",");
                for(String d : directors){
                   if(!directorAndWork.containsKey(d)){
                        directorAndWork.put(d,1);
                    }else{
                        int num =directorAndWork.get(d);
                        num+=1;
                        directorAndWork.put(d,num);
                        }   
                    }
            }else{
                String director=movie.getDirector();
                if(!directorAndWork.containsKey(director)){
                        directorAndWork.put(director,1);
                }else{
                    int num =directorAndWork.get(director);
                    num+=1;
                    directorAndWork.put(director,num);
                    }
                }
            }

    }

    public String getDirectorWithMaxWork(HashMap<String,Integer> directorAndWork,Integer maxValue){
        String maxKey="";       
        for(String s:directorAndWork.keySet()){
            if(directorAndWork.get(s)==maxValue){
                maxKey=s;
            }
        }
        return maxKey;
    }
    //========================helper methods of LoadRaters=============//
    public void getNumOfRaters(ArrayList<Rater> raters,ArrayList<String> numOfRaters){
        for(Rater rater:raters){
            if(!numOfRaters.contains(rater.getID())){
                numOfRaters.add(rater.getID());
            }
        }   
    }
    public void getRatingPerRater(ArrayList<Rater> raters,String id){
        for(Rater rater:raters){
            if(rater.getID().equals(id)){
                numOfRatingPerRaters+=1;
            }
        }
    }
    public void getTotalNumOfDiffMovie(ArrayList<Rater> raters,ArrayList<String> numOfDiffMoviesTotal){
        for(Rater rater:raters){   
            HashMap<String,Rating> rs=rater.getMyRatings();
            for(String r:rs.keySet()){
                if(!numOfDiffMoviesTotal.contains(r)){
                    numOfDiffMoviesTotal.add(r);
                }
            }
                
        }   
    }
    public void getNumOfRatingPerMovie(ArrayList<Rater> raters,String movie_id){
        for (Rater rater:raters){
            if(rater.hasRating(movie_id)){
                numOfRatingPerMovie+=1;
            }
        }

    }
    public void getRaterWithNumOfMovies(ArrayList<Rater> raters,HashMap<String,Integer> raterWithNumOfMovies){
        for(Rater rater:raters){
            if(!raterWithNumOfMovies.containsKey(rater.getID())){
                raterWithNumOfMovies.put(rater.getID(),1);
            }else{
                int num=raterWithNumOfMovies.get(rater.getID());
                num+=1;
                raterWithNumOfMovies.put(rater.getID(),num);
            }
        }
    } 
    public void getRaterWithMaxNumOfMovies(HashMap<String,Integer> raterWithNumOfMovies,Integer maxValue){
        String maxKey="";
        for(String s:raterWithNumOfMovies.keySet()){
            if(raterWithNumOfMovies.get(s)==maxValue){
                maxKey=s;
            }
        }
        System.out.println("maxKey "+maxKey);
    }

    public int getSumOfRatingsPerMovie(ArrayList<Rater> raters,String movie_id){
        int sum=0;
        for(Rater rater:raters){
            if(rater.hasRating(movie_id)){
                sum+=rater.getRating(movie_id);
            }
        }
        return sum;
    }

    //======end of helper methods and begin test with LoadMovies=====//
    //===============TEST LOADMOVIES============================//
    public void testLoadMovies(){
        ArrayList<Movie> movies=loadMovies("ratedmoviesfull.csv");
        HashMap<String,Integer> directorAndWork= new HashMap<String,Integer>();   
        directorWork(movies,directorAndWork);     
        countGenre(movies,"Comedy");
        countMinutes(movies,150);
        int maxValue= Collections.max(directorAndWork.values());
        String maxKey=getDirectorWithMaxWork(directorAndWork,maxValue);
        System.out.println(movies.size());
        System.out.println("countGenre "+countG+"\n"+"countMinutes "+countM);
        System.out.println("director of most num of movies: "+maxKey);
        System.out.println("maximum number of films directed by one director : "+maxValue); 
    }

    //======================TEST LOADRATERS======================//
    public void testLoadRaters(){
        ArrayList<Rater> raters=loadRaters("ratings_short.csv");
        System.out.println(raters.toString());
        ArrayList<String> numOfRaters = new ArrayList<String>();        
        ArrayList<String> numOfDiffMoviesTotal = new ArrayList<String>();
        HashMap<String,Integer> raterWithNumOfMovies= new HashMap<String,Integer>();
        getRaterWithNumOfMovies(raters,raterWithNumOfMovies);
        getNumOfRaters(raters,numOfRaters);
        getRatingPerRater(raters,"193");
        getNumOfRatingPerMovie(raters,"1798709");
        getTotalNumOfDiffMovie(raters,numOfDiffMoviesTotal);               
        int maxValue= Collections.max(raterWithNumOfMovies.values());
        String maxKey="";
        for(String s:raterWithNumOfMovies.keySet()){
            if(raterWithNumOfMovies.get(s)==maxValue){
                maxKey=s;
            }
        }
        System.out.println("num Of Raters: "+numOfRaters.size());
        System.out.println("numOfDiffMoviesTotal "+numOfDiffMoviesTotal.size());
        System.out.println("numOfRatingPerMovie "+numOfRatingPerMovie);
        System.out.println("numOfRatingPerRaters "+numOfRatingPerRaters);
        System.out.println("maxKey "+maxKey+"\n"+"maxValue "+maxValue);
        //getRaterWithMaxNumOfMovies(raterWithNumOfMovies,maxValue);
    }
    //getter method
    public int getnumOfRatingPerMovie(){
        return numOfRatingPerMovie;
    }

}
