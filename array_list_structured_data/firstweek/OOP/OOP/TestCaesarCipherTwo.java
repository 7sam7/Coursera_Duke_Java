
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
	public String halfOfStrings(String message,int start){
      String answer = "";
      for (int k = start;k<message.length();k+=2){
          answer = answer + message.charAt(k);
        }
      return answer;       
    }
    
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
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);      
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }  
        System.out.println(dkey);
        return dkey;
    }
    public String breakCaesarCipher(String input){		       
        String encrypted1 = halfOfStrings(input,0);
        String encrypted2 = halfOfStrings(input,1);
        int dkey1 = getKey(encrypted1);
        int dkey2 = getKey(encrypted2);
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1, dkey2);
        return cc.decrypt(input); 
	}
    public void simpleTests(){
    	int key1 = 21;
    	int key2 = 8;
    	FileResource fr = new FileResource();
        String  message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String encrypted = cc.encrypt(message.toUpperCase());
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        String decrypted1 = breakCaesarCipher(encrypted);
        System.out.println(decrypted1);
    }

}
