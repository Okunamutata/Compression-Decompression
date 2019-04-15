import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.PrintWriter;
//import sun.rmi.log.ReliableLog.LogFile;

import java.nio.file.*;
public class Compressor{
    
    LinkedList[] dictionary;
    int itemsInArray = 0;
    int code = 32; // current code of the dictionary    example:  aa --> 128 
    public static final int  ARRAYSIZE =1000;
    public  int rehashCount = 0;
    /*
    * Constructor for Dictionary object
    * @param int size, the size of the hashTable
    */ 
    public Compressor()
    {
    	 dictionary = new LinkedList[nextPrime(ARRAYSIZE)]; //nextPrime(ARRAYSIZE)];
    	 for(int i = 0; i < dictionary.length; i++)
         {
             dictionary[i] = new LinkedList();
         }
    	 intzDic();
        
    }
    public void intzDic()
    {
    	char codeC;
    	String str;
        for( codeC = 32 ;codeC < 127; codeC++)
        {
            //complete initializing dictionary with ASCII 
        	 
        	str= Character.toString(codeC);
        	insert(str);
        }
        
        
        insert("\b");
        insert("\t");
        insert("\n");
        insert("\f");
        insert("\r");

    }
    public void insert(String str)
    {
    
        LinkedList whichList = dictionary[hashFunc(str)];
   
	        if(!whichList.listContains(str))
	        {
	        	whichList.listInsert(str, code);
	        	itemsInArray++;
	        	code++;
	        	if(averageLinkedList() > 10 )
	        	{
	        	 rehash();
	        	}
	        }
    
        
    }
    public void insert(String str,int c) // inserts to the new array 
    {
         
        LinkedList whichList = dictionary[hashFunc(str)];
        
	        if(!whichList.listContains(str))
	        {
	        	whichList.listInsert(str, c);

	        	if(averageLinkedList() > 10 )
	        	{
	        	 rehash();
	        	}
	        }
        
        
    }
    public float averageLinkedList()
    {
    	float average;
    	average = itemsInArray / dictionary.length;
    	return average;
    }
    public void rehash()
    {
    	String str;
        int c; // code
        LinkedList [] oldDic;
        oldDic =  dictionary;  // holding the old dictionary 
        
        // creating a new dictionary with a bigger size
        // 
        int newDicSize = nextPrime(dictionary.length*2);
        
    	dictionary = new LinkedList[newDicSize];
    	for(int i = 0; i < dictionary.length; i++)
        {
            dictionary[i] = new LinkedList();
        }
    	
        // copying the dictionary to the new dictionary
        for( int i = 0; i < oldDic.length ; i++  )
        {
        
        	for(int k =0; k < oldDic[i].listLength(); k++)
        	{
        	str = oldDic[i].listGetString(k) ;
    		c = oldDic[i].listGetCode(k) ;

    		insert(str,c);

        	}
        	
        }
        ++rehashCount;
        
    }
    public int hashFunc(String str)
    {
        int hashVal = 0;
        
        for( int i = 0; i < str.length(); i++)
        	hashVal = 37 * hashVal + str.charAt(i) ;
        
        hashVal %= dictionary.length;
        if(hashVal < 0 )
        	hashVal += dictionary.length;
        
        return hashVal;
    }
    public static int nextPrime(int n)
    {
        if (n % 2 == 0)
            n++;
        for ( ; !isPrime( n ); n += 2);
 
        return n;

    }
    public static boolean isPrime(int n )
    {
        if (n == 2 || n == 3){ 
            return true;
        }
        if (n == 1 || n % 2 == 0){
            return false;
        }
        for (int i = 3; i * i <= n; i += 2)
        {
            if (n % i == 0){
                return false;
            }
        }
        return true;
       
    }
    public boolean inDictionary(String str)
   {
	   return dictionary[hashFunc(str)].listContains(str);
	
   }
    public String compress(String str, String outputFile) throws IOException

   {
       
    	//intzDic();
	   String q =""
	   , p="";
	   
	   String temp ="";
	   String output = "";
       String coded = "";
       outputFile = outputFile + ".zzz";
       DataOutputStream out;
       out = new DataOutputStream((new FileOutputStream(outputFile)));
      
      /* byte [] data = coded.getBytes();
           outputFile = outputFile + ".zzz";
           Path file = Paths.get(outputFile);
           Files.write(file, data);
           */
	   for(int i = 0; i< str.length();i++){
		   
		   
		   q += str.charAt(i);
		   
		   if(inDictionary(q)){
			   if(i == str.length()-1){
                   //coded += Integer.toBinaryString(dictionary[hashFunc(q)].listGetCode(dictionary[hashFunc(q)].listStringPosi(q)));
                  
				   out.write(dictionary[hashFunc(q)].listGetCode(dictionary[hashFunc(q)].listStringPosi(q)));
			   }
			   continue;
		   }else{
			   insert(q);
			   for(int j = 0;j<q.length()-1;j++){
				   temp += q.charAt(j);
			   }
			   q = temp;
               temp = "";
               //coded += Integer.toBinaryString(dictionary[hashFunc(q)].listGetCode(dictionary[hashFunc(q)].listStringPosi(q)));
			   out.write(dictionary[hashFunc(q)].listGetCode(dictionary[hashFunc(q)].listStringPosi(q)));
			   q ="";
			   i--;
           } 
           
       }
       
           
            BufferedReader br = new BufferedReader(new FileReader(outputFile));
            String line, outf = null;
            while((line = br.readLine()) != null){ outf = line;}
          return outf;
           
   }

   /*
   * Methods to create the log file
   */

    public static String tableSize(Compressor ht){
        int count  = 0;
        LinkedList[] temp = ht.dictionary;
        String out;
        for(int i = 0; i < temp.length; i++){
            if(temp[i].head != null){
                ++count;
            }
        }
        int table_Size = (int)temp.length/ count;
        out = " Hash Table is " + table_Size + "% full ";

        return out;
    }

    public static String fileSize(String inputFile, String outputFile){
        long inSize, outSize;
       
        long size1 = inputFile.length(), size2 = outputFile.length();
        //divide by 1024 to get size in kilobytes
        //file.lenght() returns in bytes

        inSize = (size1/ 1024);
        outSize = (size2/ 1024);

        String out = " Compressed from " + inSize + " Kilobytes " + outSize + " Kilobytes ";
        return out;
    }

    public static String avLength(Compressor cp){
        float avListLength = cp.averageLinkedList();
        String out = "The average linked list is " + avListLength + " elements long ";
        return out;
    }

    public static String longList(Compressor cp){
        int length = 0, length2 = 0;
        int longestList = 0;
        for(int i = 0; i < cp.dictionary.length-1; i++ ){
                length = cp.dictionary[i].numItem;
                if(cp.dictionary[i +1] != null){length2 = cp.dictionary[i +1].numItem;}
                
            
            if(length > length2){
                longestList = length;
            }
            else{
                longestList = length2;
            }
        }   
        String out = " The Longest linked list contains " + longestList + " elements";
        return out;
    }


    public static String numEntries(Compressor cp){
        int count = 0;
        for(int i = 0 ; i < cp.dictionary.length; i++){
            if(cp.dictionary[i].numItem != 0){ ++count;}

        }
        String out = " The dictionary contains " + count + " total entries ";
        return out;
    }

    public static String numRehashed(Compressor cp){
        String out = " The table was rehashed " + cp.rehashCount + " times";
        return out;
    }


    
   



    public static void main(String[] args) throws IOException
	{
	  /* Compressor test = new Compressor();
	   String str = "a";
	   	test.compress(str);
      */
      Compressor cp = new Compressor();
      Boolean runAgian = true;
      Scanner input = new Scanner(System.in);
      String file;
      String command = "";
      String data = "";
      File f;
    do{
        try {
            if(args[0] != null){ 
                file = args[0]; 
            }else{
                System.out.println("Please enter the file you wish to compress");
                file = input.next();
            }
            
            
            data = new String(Files.readAllBytes(Paths.get(file)));
            long startTime = System.nanoTime();
            cp.compress(data, file);
            long endTime = System.nanoTime();
            long time = (endTime - startTime)/1000000;
            System.out.println(" New compressed file created! ");
            

             /* 
            * create the log file
            * call log  
            * call toFile
            * tofile.write(comp.log(with args from values of))
            */   
            
            PrintWriter writer = new PrintWriter(file +".zzz.log", "UTF-8");
            writer.println("Comression of " + file );
            writer.println("Compression took " + time + " seconds \n");
            writer.println(fileSize(data, cp.compress(data, file)));
            writer.println(tableSize(cp));
            writer.println("The average linked list is" + cp.averageLinkedList() + "elements long ");
            writer.println(longList(cp));
            writer.println(numEntries(cp));
            writer.println(numRehashed(cp));
            writer.close();

            

            //Ask user to run again
            System.out.println("Do you wish to run again? \n" + "Enter 'Y' for yes or 'N' for no ");
            command = input.nextLine();
            

        }
        catch (FileNotFoundException e) {
            //if the provided file is not found or is invalid
            System.out.println("When next prompted enter a valid file name");
            runAgian = true;
            
        }
         catch (InputMismatchException e) {
            //if the command given by the user is invalid 
            System.out.println("Please enter a valid command");
            System.out.println("Do you wish to run again? \n" + "Enter 'Y' for yes or 'N' for no ");
            command = input.nextLine();
            
        }
        
        if(command.equalsIgnoreCase("Y")){ runAgian = true;}
        else{ runAgian = false; break;}
      


    } while (runAgian == true);


}


     
  
}