
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipherTwo {
	private String alphabet; 
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1,int key2){
    	alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        shiftedAlphabet1 = alphabet.substring(key1) +
        alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    public String encrypt(String input) {
    	StringBuilder sb = new StringBuilder(input.toUpperCase()); 
    	for (int i = 0; i < sb.length(); i++) {
    		if (i % 2 == 0){
        	char c = sb.charAt(i);
        	int idx = alphabet.indexOf(c); 
        		if (idx != -1) {
        	    	c = shiftedAlphabet2.charAt(idx);
        	    	sb.setCharAt(i, c); 
        		}
    		}else{
    		char c = sb.charAt(i);
        	int idx = alphabet.indexOf(c); 
        		if (idx != -1) {
        	    	c = shiftedAlphabet1.charAt(idx);
        	    	sb.setCharAt(i, c); 
        		}
    		}
    }
    	return sb.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey2,26 - mainKey1);
        return cc.encrypt(input);
    }
}

