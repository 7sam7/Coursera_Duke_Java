
/**
 * Write a description of TestCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCipher {

	public int[] countLetters(String encrypted){
        //StringBuilder encrypted = new StringBuilder(s);
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < encrypted.length(); k++){
            char ch = Character.toLowerCase(encrypted.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] vals){
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
        } }
            return maxDex;
    }
	public String breakCaesarCipher(String input){		
		int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);      
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        } 
        CaesarCipher cc = new CaesarCipher(dkey); 
        System.out.println(dkey);
        return cc.decrypt(input); 

	}
	public void simpleTests(){
		int key = 15;
		FileResource fr = new FileResource();
        String  message = fr.asString();
        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(message.toUpperCase());
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        String decrypted1 = breakCaesarCipher(encrypted);
        System.out.println(decrypted1);
	}

}
