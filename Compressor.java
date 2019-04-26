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
import java.io.BufferedWriter;
import java.io.FileWriter;
/*
*	The Class creates a LinkedList structure
*   @author Tobenna Okunna, Faisal Binateeq
*	04/15/19
*/

import java.nio.file.*;
public class Compressor{
    
    //fields needed to construct the hashTable
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
         //initalize the dictionary
    	 intzDic();
        
    }

    //initalize the hash table  with ascii value in our predicted range 
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
        
        //include character that format the text as well
        insert("\b");
        insert("\t");
        insert("\n");
        insert("\f");
        insert("\r");

    }

    /*
    * method to insert into the hash table
    * @param String str, the string to be compressed
    */
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

    /*
    * method to insert to the new array 
    * @parama tring str, int c, the string to be inserted and its corresponding code 
    */
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

    /*
    * returns the average list length
    *  calulated by dividing the number of items in the array by the length of the hash table 
    */
    public int averageLinkedList()
    {
    	int average;
    	average = itemsInArray / dictionary.length;
    	return average;
    }

    /* 
    * method to rehash the dictinoary
    * it will create a new larger table and copy the last table's entries using a new hash function
    */
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
    /*
    * method to obtain the index needed on the table
    * @param String str, the string to be compressed
    */
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

   /*
   * method to compress and write to compressed c=output to a new file
   * @param String str, the string to be encoded
   * @param String outputFile, the name of the txt file 1st inputed
   */
   public void compress(String str , String outputFile) throws IOException

   {
    
	   String q =""
	   , p="";
	   
	   String temp ="";
	   String output = "";
	   DataOutputStream out  = new DataOutputStream((new FileOutputStream(outputFile+".zzz")));
	   
	   
	   for(int i = 0; i< str.length();i++){
		   
		   
		   q += str.charAt(i);
		   
		   if(inDictionary(q)){
			   if(i == str.length()-1){
				   out.writeInt(dictionary[hashFunc(q)].listGetCode(dictionary[hashFunc(q)].listStringPosi(q)));
			   }
			   continue;
		   }else{
			   insert(q);
			   for(int j = 0;j<q.length()-1;j++){
				   temp += q.charAt(j);
			   }
			   q = temp;
			   temp = "";
			   out.writeInt(dictionary[hashFunc(q)].listGetCode(dictionary[hashFunc(q)].listStringPosi(q)));
			   q ="";
			   i--;
		   }  
	   }
	  out.close();
	   
   }
    public int tableSize(){
        int count  = 0;
       
        String out;
        for(int i = 0; i < dictionary.length; i++){
            if(dictionary[i].listLength() > 0){
                count++;
            }
        }
        int table_Size = (int)((double)100.0/(dictionary.length)*count);
       return table_Size;
    }
    public int getNumEntry()
    {
    	return itemsInArray;
    }
    public int rehashCount()
    {
    	return rehashCount;
    }
    public int longest()
    {
    	return LinkedList.longest;
    }
    public static void main(String[] args) throws IOException
	{
        Compressor cp = new Compressor();
        Boolean runAgian = true;
        Scanner input = new Scanner(System.in);
        String fileName;
        String command = "";
        long startTime=0, endTime=0;
        double time=0;
        BufferedReader br;
        String append = ".txt";
      do{
          try {
              if(args.length >= 1){ 
                fileName = args[0]; 
                
              }else{
                  System.out.println("Please enter the name of the file you wish to compress");
                  fileName = input.nextLine();
              }
              if(!fileName.contains(append)){ fileName += ".txt";}
				
              File f = new File(fileName);
              if(f.exists())
              {
              br = new BufferedReader(new FileReader(fileName));
                
              while ((fileInput = br.readLine()) != null) {  
                fileInput += br.readLine();
                // process the line
             }
              
              startTime = System.nanoTime();
              cp.compress(fileInput, fileName);
              endTime = System.nanoTime();
              time = (endTime - startTime)/1000000000.0;
              System.out.println("New compressed file created! \n");
              br.close();
              }

              else
              {
            	  System.out.println("Enter a valid file name!");
                  runAgian = true;
              }
              
              
               /* 
              * create the log file
              * call log  
              * call toFile
              * tofile.write(comp.log(with args from values of))
              */   
              File comp = new File(fileName + ".zzz");
              File unComp = new File(fileName);
              BufferedWriter log = new BufferedWriter(new FileWriter(fileName + ".zzz.log"));
              log.write("Compression of " + fileName+"\n");
              log.write("Compressed from " + unComp.length()/1000.0 + " Kilobytes to "+ comp.length()/1000.0 + " Kilobytes\n");
              log.write("Compression took " + time  + " seconds\n");
              log.write("Hash Table is " + cp.tableSize()+"% full\n");
              log.write("The longest linked list contains " + cp.longest()+"\n");
              log.write("The Dictionary contains " +  cp.getNumEntry()+"\n");
              log.write("The table was rehashed "+ cp.rehashCount() + " times\n");
              
              log.close();
              
              //Ask user to run again
              System.out.println("Do you wish to run again? \n" + "Enter 'Y' for yes or 'N' for no ");
            
              command = input.nextLine();
              

          }
          catch (IOException e) {
              //if the provided file is not found or is invalid
              System.out.println("FILE NOT FOUND!");
              
          }
          
          
          if(command.equalsIgnoreCase("Y"))
          { 
        	  runAgian = true;
          } 
          else
          {
        	  runAgian = false; 
        	  System.exit(0);
           } // terminate the program if anything but yes is pressed 
        


      } while (runAgian);

	  
	}
  
}

    

    
   



   
     
  
