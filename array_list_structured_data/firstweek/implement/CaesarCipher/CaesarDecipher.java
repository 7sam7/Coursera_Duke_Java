
/**
 * Write a description of CaesarDecipher here.
 * 
 * @author (zhe yuan) 
 * @version (2016.1.1)
 */
import edu.duke.*;

public class CaesarDecipher {    
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
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        String encrypted1 = halfOfStrings(encrypted,0);
        String encrypted2 = halfOfStrings(encrypted,1);
        int dkey1 = getKey(encrypted1);
        int dkey2 = getKey(encrypted2);
        return cc.encrypt(encrypted,26-dkey2,26-dkey1);               
    }
    public void testdecrypt(){
       // FileResource fr = new FileResource();
       // String  message = fr.asString();
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypted = decrypt(message.toUpperCase());
        System.out.println(decrypted);
        
    }
    
}
