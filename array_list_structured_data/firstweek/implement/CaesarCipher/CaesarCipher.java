import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key1,int key2) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            if (i%2 ==0){
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
                if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet2.charAt(idx);                
                //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }//If currChar is in the alphabet
            } else{
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet1.charAt(idx);                
                //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
           }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public void testCaesar() {
        int key1 = 21;
        int key2 = 8;
        FileResource fr = new FileResource();
        String  message = fr.asString();
        String encrypted = encrypt(message.toUpperCase(), key1, key2);
        System.out.println("key is "+key1+"\n"+key2+"\n"+encrypted); 
        String encrypted1 = "Top ncmy qkff vi vguv vbg ycpx";        
        String decrypted = encrypt(encrypted1.toUpperCase(), 26-key1, 26-key2);
        System.out.println(decrypted);
        /*int key = 15;
        FileResource fr = new FileResource();
        String  message = fr.asString();
        String encrypted = encrypt(message.toUpperCase(), key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);*/
    }
    public void decipher(){
        int key1=14;
        int key2=24;
        String encrypted1 = "Hfs cpwewloj loks cd Hoto kyg Cyy";        
        String decrypted = encrypt(encrypted1.toUpperCase(), 26-key2, 26-key1);
        System.out.println(decrypted);
    }
}
 
