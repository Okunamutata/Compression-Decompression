/*
* Project 1, Comp 285
* @author Tobenna Okunna
* 2/26/19
* This Project constructs 4 algorithms, 
* each made to find the max within a sequence of numbers. 
* The program also gives the user the runtime of each operation in milliseconds. 
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import List.ListReferenceBased;

import java.util.*;

public class Compressor{

    string inputFileName;
    int stSize;
    int endSize; 
    int fullness; 
    double avLinkLeng;
    int longList; 
    int totEntries; 
    int numReHash;






    /*
    * Default constructor for Compressor
    * it will initilize the global valuse for ease of use to wrtie the log file
    */
    public Compressor(){
        int stSize = 0;
        int endSize = 0; 
        int fullness = 0; 
        double avLinkLeng = 0.0;
        int longList = 0; 
        int totEntries = 0; 
        int numReHash = 0;
    }

    public static boolean checkSize(Dictionary table){
        int numItems = table.Size();
        if((numItems/(table.Length())) >= 0.9){
            return true;
        }
        else{
            return false;
        }
    }



    public static ArrayList<Integer> reader(String filename, Dictionary table){
        ArrayList<Integer> compressed = new ArrayList<Integer>();
        File file = new File(filename);
        BufferedReader br = null;
        String st, str;
        int code;
        

        try {
            br = new BufferedReader(new FileReader(file));
            while((st = br.readLine()) != null){
                for(int i = 0; i < st.length(); i++){
                    str = st.charAt(i);
                    table.insert(str, table.length);
                }
            }

            if(chechSize(table)){
                table = table.Rehash(nextPrime(table.length));
            }

            for(int i = 0; i < table.length; i++){
                compressed.add(i);
            }

            br.close();
        } catch (FileNotFoundException e) {
           
            System.out.println("Please enter a valid file name");
        }

        return compressed;
    }


    public static void writer(String filename, ArrayList<integer> data){
        try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(filename+".zzz"));
            for(int i: data){
                out.writeInt(IntegertoBinary(i));
            }
            out.close();
        }
        catch(IOException e){
            
        }
    }
    



    /*
    * @parama values needed to construct the log file, values set by dictionary function
    * @return  string to be written to the log file
    */
    public String logFile(string inputFileName, int stSize, int endSize, int fullness, double avLinkLeng,
                            int longList, int totEntries, int numReHash){
       
        String log = "Compression of " + inputFileName + "\n" +
                        "Compressed from " +  stSize + endSize + "\n" +
                        "Hash table is " + fullness + " % full" + "\n" +
                        "The average linked list is " + avLinkLeng + " elements long" + "\n" +
                        "The longest linked list contains " + longList + " total elements" + "\n" + 
                        "The dictionary contains " + totEntries + " total entries" + "\n" + 
                        "The table was rehased " + numReHash + " times";
                    
                    
        return log;
        }
                            
        




    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String file;
        boolean reRun = true;
        ArrayList<Integer> compressed = new ArrayList<Integer>();
       


        do {
            
        
       

        /*
        *Take in txt file from command line
        * Prompt user to enter txt file, if args[0] is empty
        * Try to take in the the 
        */
        try{
            if(args[0] != null){ 
                file = args[0]; 
            }else{
                System.out.println("Please enter the file you wish to compress");
                file = input.next();
            }

            Dictionary table = new Dictionaty(1373);
            char[] carr = new char[95]; // 95 = 126-32+1
            for(char ch = 32; ch <= 126; ++ch){
                carr[ch-32] = ch;
                table.insert((String) carr, 1373);
            }
            reader(file, table);
            compressed = reader(file, table);
            
            System.out.println("Please enter the name of your new compressed file: ");
            String fileout = input.next();
            
            writer(fileout, compressed);
            
        

            
        /* 
        * create the log file
        * call log  
        * call toFile
        * tofile.write(comp.log(with args from values of))
        */   


        
    
        }
         catch (FileNotFoundException e) {
            //if the provided file is not found or is invalid
            System.out.println("Please enter a valid file name");
            break;
        }
        
    } while (valid == true);

        



    }


}