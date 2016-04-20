
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
public class MovieRunnerSimilarRatings {
	public void printAverageRatings(){
        FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		ArrayList<Rating> aveRating=fourthR.getAverageRatings(35);
		System.out.println(aveRating.size());
		
	}
	public void printAverageRatingsByYearAfterAndGenre(){
		FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(new YearAfterFilter(1990));
		allFilters.addFilter(new GenreFilter("Drama"));
		ArrayList<Rating> aveRating=fourthR.getAverageRatingsByFilter(8,allFilters);
		//System.out.println(aveRating);
		System.out.println(aveRating.size());
		/*for(Rating ra:aveRating){
			System.out.println(ra.getValue()+" "+MovieDatabase.getYear(ra.getItem())+" "+MovieDatabase.getTitle(ra.getItem())+"\n"
				+" "+MovieDatabase.getGenres(ra.getItem()));
		}*/
	}
	public void printSimilarRatings(){
		FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		ArrayList<Rating> ratingList = fourthR.getSimilarRatings("71",20,5);
		//System.out.println(ratingList);
		System.out.println(ratingList);
		System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
	}
	public void printSimilarRatingsByGenre(){
		FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter("964",20,5,new GenreFilter("Mystery"));
		System.out.println(ratingList);
		System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
	}
	public void printSimilarRatingsByDirector(){
		FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
		System.out.println(ratingList.size());
		System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
	}
	public void printSimilarRatingsByGenreAndMinutes(){
		FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(new MinutesFilter(80,160));
		allFilters.addFilter(new GenreFilter("Drama"));
		ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter("168",10,3,allFilters);
		System.out.println(ratingList.size());
		System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
	}
	public void printSimilarRatingsByYearAfterAndMinutes(){
		FourthRatings fourthR=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(new MinutesFilter(70,200));
		allFilters.addFilter(new YearAfterFilter(1975));
		ArrayList<Rating> ratingList = fourthR.getSimilarRatingsByFilter("314",10,5,allFilters);
		System.out.println(MovieDatabase.getTitle(ratingList.get(0).getItem()));
	}
}
