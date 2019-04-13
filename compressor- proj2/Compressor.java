import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Compressor{
    
    LinkedList[] theArray;
    //Node head;
    int arraySize;
    int itemsInArray = 0;
    int code = 32; // current code of the dictionary    example:  aa --> 128 
    public static final int  ARRAYSIZE =  nextPrime(1000);
    /*
    * Constructor for Dictionary object
    * @param int size, the size of the hashTable
    */ 
    public Compressor()
    {
    	 arraySize = nextPrime(ARRAYSIZE);
    	 theArray = new LinkedList[arraySize];
    	 for(int i = 0; i < arraySize; i++)
         {
             theArray[i] = new LinkedList();
         }
    	 intzDic();
        
    }
    public void newArray(int newArraySize) // for rehashing
    {
    	arraySize = nextPrime(ARRAYSIZE);
    	theArray = new LinkedList[arraySize];
    	for(int i = 0; i < arraySize; i++)
        {
            theArray[i] = new LinkedList();
        }
    }
    public void intzDic()
    {
    	char c, codeC;
    	String str;
        for(codeC = 32 ; codeC < 127; codeC++)
        {
            //complete initializing dictionary with ASCII 
        	c=(char)codeC;
        	str= Character.toString(c);
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
        //calculate hashkey
    	int hashKey  = hashFunc(str);
        LinkedList whichList = theArray[hashKey];
        
	        if(whichList.ListContains(str) == false)
	        {
	        	whichList.ListInsertEnd(str, code);
	        	itemsInArray++;
	        	code++;
	        	if(averageLinkedList() > 10 )
	        	 rehash();
	        }
        
        
    }
    public void insert(String str,int cod)
    {
        //calculate hashkey
    	int hashKey  = hashFunc(str);
        LinkedList whichList = theArray[hashKey];
        
	        if(whichList.ListContains(str) == false)
	        {
	        	whichList.ListInsertEnd(str, cod);
	        	if(averageLinkedList() > 10 )
	        	 rehash();
	        }
        
        
    }
    public int averageLinkedList()
    {
    	int average;
    	average = itemsInArray / arraySize;
    	return average;
    }
    //Methos to test hashtable and rehash
    public void rehash()
    {
        LinkedList [] oldArr = theArray;
        
        // change the array size
 //       int newArrSize = (theArray.length * 2);
        // create a new table and initialize it
        theArray = new LinkedList[nextPrime(theArray.length * 2)];
    	for(int i = 0; i < arraySize; i++)
        {
            theArray[i] = new LinkedList();
        }      
        // copy the table to the new table
        String str;
        int cod;
        for( int i = 0; i < oldArr.length ; i++  )
        {
        	for(int j = 0; j < oldArr[i].listLength() ; i++)
        	{
        		str = oldArr[i].ListRetrieveString(j) ;
        		cod = oldArr[i].ListRetrieveCode(j) ;
        		insert(str,cod);
        	}
        	
        }
        
    }
    /*
    * Creates key value for hash table insertion
    * @parama String str, the ctring to be encoded
    */
    public int hashFunc(String str)
    {
        int hashVal = 0;
        
        for( int i = 0; i < str.length(); i++)
        	hashVal = 37 * hashVal + str.charAt(i) ;
        
        hashVal %= theArray.length;
        if(hashVal < 0 )
        	hashVal += theArray.length;
        
        return hashVal;
    }
    public static int nextPrime(int n){
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
	   return theArray[hashFunc(str)].ListContains(str);
	
   }
    public void compress(String mainString) throws IOException

   {

	   String q =""
	   , p="";
	   int hashKey;
	   String temp ="";
	   String output = "";
	   
	   for(int i = 0; i< mainString.length();i++){
		   
		   
		   q += mainString.charAt(i);
		//   p = Character.toString(mainString.charAt(i));
		   
		   if(inDictionary(q)){
			   if(i == mainString.length()-1){
				   System.out.println(theArray[hashFunc(q)].ListRetrieveCode(theArray[hashFunc(q)].listStringPosi(q)));
			   }
			   continue;
		   }else{
			   insert(q);
			   for(int j = 0;j<q.length()-1;j++){
				   temp += q.charAt(j);
			   }
			   q = temp;
			   temp = "";
			   System.out.println(theArray[hashFunc(q)].ListRetrieveCode(theArray[hashFunc(q)].listStringPosi(q)));
			   q ="";
			   i--;
		   }  
	   }
	  
	   
   }

    public static void main(String[] args)
	{
	   Compressor test = new Compressor();
	   String str = "abbabbabbbbaabab";
	   try
	   {
		test.compress(str);
		//test.rehash();    something wrong with rehash
	   } 
	   catch (IOException e) 
	   {
		System.out.println("Something is wrong!");
	   }
	}
  
}