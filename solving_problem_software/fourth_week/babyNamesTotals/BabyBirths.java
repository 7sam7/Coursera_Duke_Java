
/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public int getRank (Integer year,String name,String gender){
		FileResource fr = new FileResource();
		ArrayList<CSVRecord> female= new ArrayList<CSVRecord>();
		ArrayList<CSVRecord> male = new ArrayList<CSVRecord>();
		int rank=-1;
		for (CSVRecord rec : fr.getCSVParser(false)){
			if (rec.get(1).equals("F")){
				female.add(rec);
				
			}
			else{
				male.add(rec);
				
			}
		}
		if (gender.equals("F")){
			for(CSVRecord frec:female){
				if (frec.get(0).equals(name)){
					rank=female.indexOf(frec)+1;
				}
			}
			
			
		}else{
			for(CSVRecord mrec:male){
				if (mrec.get(0).equals(name)){
					rank=male.indexOf(mrec)+1;
				}
			}
		}
		return rank;
	}

	public String getName(Integer year,Integer rank,String gender){
		String name = "";
		FileResource fr = new FileResource();
		rank=rank-1;
		ArrayList<CSVRecord> female= new ArrayList<CSVRecord>();
		ArrayList<CSVRecord> male = new ArrayList<CSVRecord>();
		for (CSVRecord rec : fr.getCSVParser(false)){
			if (rec.get(1).equals("F")){
				female.add(rec);
				
			}
			else{
				male.add(rec);
				
			}
		}
		if (gender.equals("F")){
			if (female.get(rank)!=null){
				name=female.get(rank).get(0);
			}else{
				name="NO NAME";
				}
			}
		else{
			if (male.get(rank)!=null){
				name=male.get(rank).get(0);
			}
			else{
				name="NO NAME";
				}
			}
		return name;
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalGirlsName=0;
		int totalBoysName=0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalBoysName+=1;
			}
			else {
				totalGirls += numBorn;
				totalGirlsName+=1;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("Unique female girls Name = " + totalGirlsName);
		System.out.println("Unique male boys Name = " + totalBoysName);
	}

	public void whatIsNameInYear(String name,Integer year,Integer newYear,String gender){
		int rank = getRank(year,name,gender);
		String newName= getName(newYear,rank,gender);
		System.out.println(name+" born in "+year+" at the rank "+rank+" would be "+newName+" if she was born in "+newYear);
	}

	public int yearOfHighestRank (String name, String gender){
		int year=-1;
		HashMap<String,Integer> ranklist=new HashMap<String,Integer>();
		ArrayList<Integer> numlist = new ArrayList<Integer>();
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			String fname = f.getName();
			String fyear = fname.substring(3,7);			
			FileResource fr = new FileResource(f);
			for (CSVRecord rec : fr.getCSVParser(false)){
				if((rec.get(0).equals(name))&&(rec.get(1).equals(gender))){	
					ranklist.put(fyear,getRank2(fr,name,gender));
					numlist.add(getRank2(fr,name,gender));
				}
			}
		}

		Collections.sort(numlist);
		System.out.println("ranklist: "+ranklist+"\n"+"numlist: "+numlist);
		int high=numlist.get(0);
		for (String yr: ranklist.keySet()){
			if(ranklist.get(yr)==high){
				year = Integer.parseInt(yr);					
			}
		}
		return year;
	}

	public int getRank2 (FileResource fr,String name,String gender){
		//FileResource fr = new FileResource("data/yob2012short.csv");
		ArrayList<CSVRecord> female= new ArrayList<CSVRecord>();
		ArrayList<CSVRecord> male = new ArrayList<CSVRecord>();
		int rank=-1;
		for (CSVRecord rec : fr.getCSVParser(false)){
			if (rec.get(1).equals("F")){
				female.add(rec);
				
			}
			else{
				male.add(rec);
				
			}
		}
		if (gender.equals("F")){
			for(CSVRecord frec:female){
				if (frec.get(0).equals(name)){
					rank=female.indexOf(frec)+1;
				}
			}
			
			
		}else{
			for(CSVRecord mrec:male){
				if (mrec.get(0).equals(name)){
					rank=male.indexOf(mrec)+1;
				}
			}
		}
		return rank;
	}	

	public double getAverageRank(String name,String gender){
		double ave=0;
		double sum=0;
		ArrayList<Integer> ranklist = new ArrayList<Integer>();
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			ranklist.add(getRank2(fr,name,gender));
		}
		for(Integer r: ranklist){
			sum+=r;
		}
		ave=sum/ranklist.size();
		System.out.println("The average rank of the name"+name+"is: ");
		return ave;
	}

	public int getTotalBirthsRankedHigher(Integer year,String name,String gender){
		int total=0;
		int threshold=0;
		FileResource fr =new FileResource();
		for(CSVRecord rec:fr.getCSVParser(false)){
			if(rec.get(0).equals(name)&&rec.get(1).equals(gender)){
				threshold=Integer.parseInt(rec.get(2));
			}
		}
		for (CSVRecord rec2:fr.getCSVParser(false)){
			if((Integer.parseInt(rec2.get(2))>threshold)&&(rec2.get(1).equals(gender))){
				total+=Integer.parseInt(rec2.get(2));
			}
		}

		return total;
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		//FileResource fr = new FileResource();
		//totalBirths(fr);
		
		
		//System.out.println("Answer to No.3: "+getRank(1960,"Emily","F"));
		
		//System.out.println("Answer to No.4: "+getRank(1971,"Frank","M"));
		
		//System.out.println("Answer to No.5: "+getName(1980,350,"F"));
		
		//System.out.println("Answer to No.6: "+getName(1982,450,"M"));

		//System.out.println(getName(2012,1,"F"));
		//whatIsNameInYear("Susan",1972,2014,"F");
		//whatIsNameInYear("Owen",1974,2014,"M");
		//System.out.println("Highest Ranking is "+yearOfHighestRank("Genevieve","F"));
		System.out.println("Highest Ranking is "+yearOfHighestRank("Mich","M"));
		
		//System.out.printf("%f",getAverageRank("Susan","F"));
		//System.out.printf("%f",getAverageRank("Robert","M"));
		//System.out.println("total number is "+getTotalBirthsRankedHigher(1990,"Emily","F"));
		//System.out.println("total number is "+getTotalBirthsRankedHigher(1990,"Drew","M"));		
	}
}
