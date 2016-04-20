/**
 * Prints out all links within the HTML source of a web page.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;

public class URLFinder {
	public StorageResource findURLs(String url) {
		URLResource page = new URLResource(url);
		String source = page.asString();
		StorageResource store = new StorageResource();
		int start = 0;
		while (true) {			
			int index = source.indexOf("href=", start);
			if (index == -1) {
				break;
			}
			int firstQuote = index+6; // after href="
			int endQuote = source.indexOf("\"", firstQuote);
			String sub = source.substring(firstQuote, endQuote);
			store.add(sub);	
			start = endQuote + 1;
		}
	
		return store;
	}

	public int countPeriods(String url) {
    	URLResource page = new URLResource(url);
    	String source = page.asString();
    	int count = 0;
    	for (char c : source.toCharArray()) {
    	    if (c == '.') {
    	        count++;
    	    }
    	}
    	return count;
	}

	public void testURL() {
		int httpcount=0;
		int comcount=0;
		int dots=0;
		int count2=0;
		//StorageResource s1 = findURLs("https://www.whitehouse.gov");
		//StorageResource s2 = findURLs("http://www.doctorswithoutborders.org");
		StorageResource s2 = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");		
		int dot = countPeriods("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
		for (String link : s2.data()) {
			System.out.println(link);
		}		
		for (String item : s2.data()){
			if(item.startsWith("https")){
				httpcount+=1;
			}
			if (item.endsWith(".com")||item.endsWith(".com/")){
				comcount+=1;
			}
			if (item.contains(".com")){
				count2+=1;
			}
			/*int loc = item.indexOf(".");
			while (loc != -1) {
			    dots+=1;
			    loc = item.indexOf(".", loc + 1);
			}*/
		}
		System.out.println("number of http is "+httpcount+"\n"+"number of .com is "+comcount+"\n"+"number of contain .com is "+ count2+"\n"+"number of dots are "+ dot);
		//System.out.println("size = " + s1.size());
		System.out.println("size = " + s2.size());

	}
	
}



