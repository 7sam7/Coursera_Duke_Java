import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    CaesarCracker crackers;

    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder slice=new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices){
            slice.append(message.substring(i,i+1));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
       
        //WRITE YOUR CODE HERE
        crackers=new CaesarCracker();
        int[] s= new int[klength];
    
        for(int i=0;i<klength;i++){
           s[i]=crackers.getKey(sliceString(encrypted,i,klength));
        }       
        return s;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr= new FileResource();
        String s= fr.asString();
        HashMap<String,HashSet<String>> dictmap = new HashMap<String,HashSet<String>>();
        DirectoryResource dr= new DirectoryResource();
        for(File f:dr.selectedFiles()){
            String fname = f.getName();
            FileResource fd= new FileResource(f);    
            HashSet<String> dict= readDictionary(fd);
            dictmap.put(fname,dict);
            System.out.println(fname);
        }
        //System.out.println(breakForLanguage(s,dict));
        breakForAllLanguages(s,dictmap);
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict=new HashSet<String>();
        for(String line : fr.lines()){
            dict.add(line.toLowerCase());
        }
        return dict;
    }

    public int countWords(String message,
        HashSet<String> dictionary){
        int count=0;
        for(String word:message.split("\\W")){
            if(dictionary.contains(word)){
                count+=1;
            }
        }
        return count;
    }
    //modified for assignment3
    public String breakForLanguages(String encrypted,HashSet<String> dictionary){
        String s="";
        ArrayList<Integer> countList = new ArrayList<Integer>();
        HashMap<Integer,Integer> pair= new HashMap<Integer,Integer>();
        for(int i=1;i<101;i++){
            VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted,i,mostCommonCharIn(dictionary)));
            countList.add(countWords(vc.decrypt(encrypted),dictionary));
            //String si= Integer.toString(i);
            pair.put(i,countWords(vc.decrypt(encrypted),dictionary));
        }
        Collections.sort(countList);
        System.out.println("keylength and its words"+countList+"\n"+"pair: "+pair);
        int max=countList.get(countList.size()-1);
        for(Integer j:pair.keySet()){
            if (pair.get(j)==max){
                //int j= Integer.parseInt(st);
                VigenereCipher vc3 = new VigenereCipher(tryKeyLength(encrypted,j,mostCommonCharIn(dictionary)));
                s = vc3.decrypt(encrypted);
                //System.out.println("key is: "+Arrays.toString(tryKeyLength(encrypted,57,'e')));
                //System.out.println("countList(): "+countList.get(countList.size()-1)+"\n"+st);
        }
        }
        return s;
    }

    public void breakVigenereSingle(){
        FileResource fr= new FileResource();
        String s= fr.asString();
        FileResource fd= new FileResource();    
        HashSet<String> dict= readDictionary(fd);
        System.out.println(breakForLanguage(s,dict));

    }
    //assignment 2
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        String s="";
        ArrayList<Integer> countList = new ArrayList<Integer>();
        HashMap<Integer,Integer> pair= new HashMap<Integer,Integer>();
        for(int i=1;i<101;i++){
            VigenereCipher vc = new VigenereCipher(tryKeyLength(encrypted,i,'e'));
            String message=vc.decrypt(encrypted);
            countList.add(countWords(message,dictionary));
            //String si= Integer.toString(i);
            pair.put(i,countWords(vc.decrypt(encrypted),dictionary));
        }
        Collections.sort(countList);
        System.out.println("keylength and its words"+countList+"\n"+"pair: "+pair);
       // VigenereCipher vc2 = new VigenereCipher(tryKeyLength(encrypted,57,'e'));
       // s = vc2.decrypt(encrypted);
        int max=countList.get(countList.size()-1);
        System.out.println("max: "+max);
        for(Integer j:pair.keySet()){
            if (pair.get(j)==max){
                //int j= Integer.parseInt(st);
                VigenereCipher vc3 = new VigenereCipher(tryKeyLength(encrypted,j,'e'));
                s = vc3.decrypt(encrypted);
                //System.out.println("key is: "+Arrays.toString(tryKeyLength(encrypted,57,'e')));
                //System.out.println("countList(): "+countList.get(countList.size()-1)+"\n"+st);
        }
        }
        return s;
    }    

    public char mostCommonCharIn(HashSet<String> dictionary){
        char mostCommonChar= new Character('e');
        String alph = "abcdefghijklmnopqrstuvwxyz";

        for(String w:dictionary){

        }
        return mostCommonChar;
    }

    public int breakForAllLanguages(String encrypted,
        HashMap<String,HashSet<String>> languages){
        ArrayList<Integer> counts=new ArrayList<Integer>();
        for(String name:languages.keySet()){
            HashSet<String> l = languages.get(name);
            String message=breakForLanguages(encrypted,l);
            int count=countWords(message,l);
            counts.add(count);
        }
        Collections.sort(counts);
        return counts.get(counts.size()-1);
    }
    
}
