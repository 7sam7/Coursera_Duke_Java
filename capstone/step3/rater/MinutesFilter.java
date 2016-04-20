
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
	private int myMin;
	private int myMax;

	public MinutesFilter(int min,int max){
		myMin = min;
		myMax = max;
	}

	@Override 
	public boolean satisfies(String id) {
		return ((MovieDatabase.getMinutes(id)>=myMin) && (MovieDatabase.getMinutes(id)<=myMax));
	}
}
