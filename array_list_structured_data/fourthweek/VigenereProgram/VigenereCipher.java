import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    CaesarCracker crackers;
    
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }

    public String sliceString(String message,Integer whichSlice,
        Integer totalSlices){
        StringBuilder slice=new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices){
            slice.append(message.substring(i,i+1));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted,Integer klength,
        Character mostCommon){
        crackers=new CaesarCracker();
        int[] s= new int[klength];
    
        for(int i=0;i<klength;i++){
           s[i]=crackers.getKey(sliceString(encrypted,i,klength));
        }       
        return s;
    }

    public void breakVigenere(){
        //VigenereCipher vigenere= new VigenereCipher();
        //System.out.println(sliceString("abcdefghijklm",0,3));
        FileResource fr= new FileResource();
        String contents= fr.asString();
        VigenereCipher vc = new VigenereCipher(tryKeyLength(contents,4,'e'));
        System.out.println(Arrays.toString((tryKeyLength(contents,4,'e'))));
        
        //System.out.println(vc.decrypt(contents));
    }









    
}
