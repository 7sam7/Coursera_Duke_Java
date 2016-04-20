
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sR=new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        int mn=sR.getMovieSize();
		int rn=sR.getRaterSize();
		//sR.print();
		System.out.println("number of movies "+mn+"\n"+"number of raters "+rn);
		ArrayList<Rating> aveRating=sR.getAverageRatings(20);
		//double ave=sR.getAverageByID("1798709",3);
		//String title=sR.getTitle("1798709");
		//System.out.printf("%.2f",ave);
		//System.out.printf(title);
		System.out.println(aveRating.get(0));
		System.out.println("\n");
		ArrayList<Rating> aveRating2=sR.getAverageRatings(12);
		System.out.println(aveRating2.get(0));
		ArrayList<Rating> aveRating3=sR.getAverageRatings(50);
		System.out.println(aveRating3.size());
	}

	public void getAverageRatingOneMovie(){
		SecondRatings sR=new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
		String id=sR.getID("The Maze Runner");
		double ave=sR.averageRatingOneMovie(id);
		System.out.printf("%.4f",ave);
		System.out.println("\n");
		String id2=sR.getID("Moneyball");
		double ave2=sR.averageRatingOneMovie(id2);
		System.out.printf("%.4f",ave2);
		System.out.println("\n");
		String id3=sR.getID("Vacation");
		double ave3=sR.averageRatingOneMovie(id3);
		System.out.printf("%.4f",ave3);
		//sR.uniqueRatings();
	}

}
