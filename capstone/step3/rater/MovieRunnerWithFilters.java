
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings tR=new ThirdRatings("ratings.csv");
		int rn=tR.getRaterSize();
		//sR.print();
		System.out.println("number of raters "+rn);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		ArrayList<Rating> aveRating=tR.getAverageRatings(35);
		//double ave=sR.getAverageByID("1798709",3);
		//String title=sR.getTitle("1798709");
		//System.out.printf("%.2f",ave);
		//System.out.printf(title);
		System.out.println(aveRating.size());
		/*System.out.println("\n");
		ArrayList<Rating> aveRating2=tR.getAverageRatings(12);
		System.out.println(aveRating2.get(0));
		ArrayList<Rating> aveRating3=tR.getAverageRatings(50);
		System.out.println(aveRating3.size());*/
	}
	public void printAverageRatingsByYear(){
		ThirdRatings tR=new ThirdRatings("ratings.csv");
		int rn=tR.getRaterSize();
		//sR.print();
		System.out.println("number of raters "+rn);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		ArrayList<Rating> aveRating=tR.getAverageRatingsByFilter(20,new YearAfterFilter(2000));
		//System.out.println(aveRating);
		System.out.println(aveRating.size());
		/*for(Rating ra:aveRating){
			System.out.println(ra.getValue()+" "+MovieDatabase.getYear(ra.getItem())+" "+MovieDatabase.getTitle(ra.getItem()));
		}*/	
	}
	public void printAverageRatingsByGenre(){
		ThirdRatings tR=new ThirdRatings("ratings.csv");
		int rn=tR.getRaterSize();
		System.out.println("number of raters "+rn);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		ArrayList<Rating> aveRating=tR.getAverageRatingsByFilter(20,new GenreFilter("Comedy"));
		//System.out.println(aveRating);
		System.out.println(aveRating.size());
		/*for(Rating ra:aveRating){
			System.out.println(ra.getValue()+" "+MovieDatabase.getTitle(ra.getItem())+"\n"
				+" "+MovieDatabase.getGenres(ra.getItem()));
		}*/	

	}
	public void printAverageRatingsByMinutes(){
		ThirdRatings tR=new ThirdRatings("ratings.csv");
		int rn=tR.getRaterSize();
		System.out.println("number of raters "+rn);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		ArrayList<Rating> aveRating=tR.getAverageRatingsByFilter(5,new MinutesFilter(105,135));
		//System.out.println(aveRating);
		System.out.println(aveRating.size());
		/*for(Rating ra:aveRating){
			System.out.println(ra.getValue()+" "+MovieDatabase.getMinutes(ra.getItem())+" "+MovieDatabase.getTitle(ra.getItem()));
		}*/	
	}
	public void printAverageRatingsByDirectors(){
		ThirdRatings tR=new ThirdRatings("ratings.csv");
		int rn=tR.getRaterSize();
		System.out.println("number of raters "+rn);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		ArrayList<Rating> aveRating=tR.getAverageRatingsByFilter(4,new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
		//System.out.println(aveRating);
		System.out.println(aveRating.size());
		/*for(Rating ra:aveRating){
			System.out.println(ra.getValue()+" "+MovieDatabase.getTitle(ra.getItem())+"\n"
				+" "+MovieDatabase.getDirector(ra.getItem()));
		}*/
	}
	public void printAverageRatingsByYearAfterAndGenre(){
		ThirdRatings tR=new ThirdRatings("ratings.csv");
		int rn=tR.getRaterSize();
		System.out.println("number of raters "+rn);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(new YearAfterFilter(1990));
		allFilters.addFilter(new GenreFilter("Drama"));
		ArrayList<Rating> aveRating=tR.getAverageRatingsByFilter(8,allFilters);
		//System.out.println(aveRating);
		System.out.println(aveRating.size());
		/*for(Rating ra:aveRating){
			System.out.println(ra.getValue()+" "+MovieDatabase.getYear(ra.getItem())+" "+MovieDatabase.getTitle(ra.getItem())+"\n"
				+" "+MovieDatabase.getGenres(ra.getItem()));
		}*/
	}
	public void printAverageRatingsByDirectorsAndMinutes(){
		ThirdRatings tR=new ThirdRatings("ratings.csv");
		int rn=tR.getRaterSize();
		System.out.println("number of raters "+rn);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int movieSize = MovieDatabase.size();
		System.out.println("number of movies "+movieSize);
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(new MinutesFilter(90,180));
		allFilters.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
		ArrayList<Rating> aveRating=tR.getAverageRatingsByFilter(3,allFilters);
		System.out.println(aveRating.size());
		//System.out.println(aveRating);
		//for(Rating ra:aveRating){
		//	System.out.println(ra.getValue()+" Time: "+MovieDatabase.getMinutes(ra.getItem())+" "+MovieDatabase.getTitle(ra.getItem())+"\n"
		//		+" "+MovieDatabase.getDirector(ra.getItem()));
		//}
	}
}
