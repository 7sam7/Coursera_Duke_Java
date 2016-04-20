/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
 
import edu.duke.*;
import org.apache.commons.csv.*;
import java.text.NumberFormat;
import java.math.*;

public class FirstCSVExample {
	public void readcsv() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		
		for (CSVRecord record : parser){
			URLResource image = new URLResource(record.get("URL"));
			//ImageResource image=new ImageResource(photo);
			//String newName=record.get("Unix.time");
			//System.out.print(image);
			String image_s=image.asString();
			//sr.add(image_s);
			//System.out.print(record.get("Name") + " ");
			//System.out.print(record.get("Favorite Color") + " ");
			//System.out.println(record.get("Favorite Food"));
		}
	}
	public void tester(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		
		System.out.println("countryInfo:");
		System.out.println(countryInfo(parser,"Nauru"));

		parser = fr.getCSVParser();
		System.out.println("listExportersTwoProducts:");
		listExportersTwoProducts(parser,"cotton","flowers");
		parser = fr.getCSVParser();
		System.out.println("numberOfExporters:");
		System.out.println(numberOfExporters(parser,"cocoa"));
		parser = fr.getCSVParser();
		System.out.println("bigExporters:");
		bigExporters(parser,"$999,999,999,999");
	}

	public String countryInfo (CSVParser parser,String country){
		String p="";
		for (CSVRecord record : parser){
			 if (country.equals(record.get("Country"))){
			 	p=record.get("Country")+": "+record.get("Exports")+": "+record.get("Value (dollars)");
			 	System.out.println(p);
		
			 }
			 else
			 	p= "Not Found";
			 	
		}
		return p;

	}

	public void listExportersTwoProducts(CSVParser parser,String exportItem1,
		String exportItem2){
		for(CSVRecord record:parser){	
			if(record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)){
				System.out.println(record.get("Country"));
			}
		}
	}

	public int numberOfExporters(CSVParser parser,String exportItem){
		int number=0;
		for(CSVRecord record:parser){
			if (record.get("Exports").contains(exportItem)){
				number+=1;
			}
		}
		return number;
	}


	public void bigExporters(CSVParser parser,String amount){
		//NumberFormat format = NumberFormat.getCurrencyInstance();
		for (CSVRecord record:parser) {
			String am=amount.replace("$","").replace(",","");
			//Number a = format.parse(amount);
			String bm=record.get("Value (dollars)").replace("$",",").replace(",","");
			//Number b= format.parse(record.get("Value (dollars)"));
			BigInteger a1= new BigInteger(am);
			BigInteger b1 = new BigInteger(bm);
			int res=0;
			res = b1.compareTo(a1);
			if(res==1){
				System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
			}
		}
	}
  
}
