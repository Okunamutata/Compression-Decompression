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

    String inputFileName;
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



    public void dictionary( int tsNum){
        
       

    }

    /*
    * @parama values needed to construct the log file, values set by dictionary function
    * @return  string to be written to the log file
    */
    public String logFile(String inputFileName, int stSize, int endSize, int fullness, double avLinkLeng,
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
        Compressor comp = new Compressor();
        Scanner input = new Scanner(System.in);
        String file;
        boolean valid = true;
        FileWriter toFile;

        do {
            
        
       

        /*
        *Take in txt file from command line
        *
        * Prompt user to enter txt file, if args[0] is empty
        *
        * Try to take in the the 
        */
        try{
            if(args[0] != null){ 
                file = args[0]; 
            }else{
                System.out.println("Please enter the file you wish to compress");
                file = input.next();
            }


            
        /* 
        * create the log file
        * call log  
        * call toFile
        * tofile.write(comp.log(with args from values of))
        */   


        
    
        }
         catch (FileNotFoundException e)
        {
            //if the provided file is not found or is invalid
            System.out.println("Please enter a valid file name");
            break;
        }
        
    } while (valid == true);

        



    }


}