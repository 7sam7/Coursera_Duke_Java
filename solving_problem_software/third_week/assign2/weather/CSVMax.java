
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.text.NumberFormat;
import java.math.*;
import java.io.*;
public class CSVMax {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser){
            smallestSoFar=compareBetweenTwo(currentRow,smallestSoFar);
        }
        return smallestSoFar;
    }

    public void testcoldestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Coldest temperature is "+coldestHourInFile(parser).get("TemperatureF")); 
        //+" at "+coldestHourInFile(parser).get("DateUTC"));
    }

    public CSVRecord compareBetweenTwo(CSVRecord currentRow,CSVRecord smallestSoFar){
        if(smallestSoFar == null){
                smallestSoFar=currentRow;
            }
            else if(!currentRow.get("TemperatureF").equals("-9999")){
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currentTemp < smallestTemp){
                    smallestSoFar=currentRow;
                }
            }
        return smallestSoFar;
    }

    public CSVRecord fileWithColdestTemperature(){
        String name="";
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow= coldestHourInFile(fr.getCSVParser());
            smallestSoFar=compareBetweenTwo(currentRow,smallestSoFar);
            name=f.getName();
            //System.out.println("Coldest temperature is "+coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
            
        }
        //return "weather-"+smallestSoFar.get("DateUTC");
        return smallestSoFar;
    }

    public void testfileWithColdestTemperature(){
        //System.out.println("Coldest day was in file "+fileWithColdestTemperature());
        CSVRecord csv = fileWithColdestTemperature();
        System.out.println("coldest temperature in a year is "+csv.get("TemperatureF")+" at "+csv.get("DateUTC"));
    }

    public CSVRecord hcompareBetweenTwo(CSVRecord currentRow,CSVRecord smallestSoFar){
        if(smallestSoFar == null){
                smallestSoFar=currentRow;
            }
            else if ((!currentRow.get("Humidity").equals("N/A") )&& (!smallestSoFar.get("Humidity").equals("N/A"))){            	
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                if (currentTemp < smallestTemp){
                    smallestSoFar=currentRow;
                }
            }
        return smallestSoFar;
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
    	CSVRecord lowestSoFar = null;
    	for(CSVRecord currentRow:parser){
    		lowestSoFar=hcompareBetweenTwo(currentRow,lowestSoFar);
    	}
    	return lowestSoFar;
    }

    public void testlowesthumidity(){
    	FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInFile(parser);
		System.out.println("lowestHumidityInFile is "+csv.get("Humidity")+" "+csv.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
    	CSVRecord lowestSoFar = null;
    	DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow= lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar=hcompareBetweenTwo(currentRow,lowestSoFar);
            }
    	return lowestSoFar;
    }

    public void testlowesthumidityInManyFiles(){
    	CSVRecord csv = lowestHumidityInManyFiles();
		System.out.println("lowestHumidityInFile is "+csv.get("Humidity")+" "+csv.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser){
    	double t=0;
    	int i=0;
    	double j=0;
    	for (CSVRecord record : parser){
    		i+=1;
    		j+=Double.parseDouble(record.get("TemperatureF"));
    	}
    	return t=j/i;
    }

    public void testAverageTemperatureInFile(){
    	FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double t = averageTemperatureInFile(parser);
		System.out.println("Average temperature in file is "+t);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,Integer value){
    	double t=0;
    	int i=0;
    	double j=0;
    	for (CSVRecord record : parser){
    		if (Double.parseDouble(record.get("Humidity"))>=value){
    			i+=1;
    			j+=Double.parseDouble(record.get("TemperatureF"));	
    		}
    		
    	}
    	if(j==0){
    		System.out.println("No temperature with that Humidity");
    	}
    	return t=j/i;
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
    	FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double t = averageTemperatureWithHighHumidityInFile(parser,80);
		System.out.println("Average temperature in file is "+t);
    }

}
