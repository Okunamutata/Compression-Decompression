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
    
    LinkedList[] dictionary;
    int itemsInArray = 0;
    int code = 32; // current code of the dictionary    example:  aa --> 128 
    public static final int  ARRAYSIZE =1000;
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
    public int averageLinkedList()
    {
    	int average;
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
    public void compress(String str) throws IOException

   {
    	//intzDic();
	   String q =""
	   , p="";
	   
	   String temp ="";
	   String output = "";
	   
	   for(int i = 0; i< str.length();i++){
		   
		   
		   q += str.charAt(i);
		   
		   if(inDictionary(q)){
			   if(i == str.length()-1){
				   System.out.println(dictionary[hashFunc(q)].listGetCode(dictionary[hashFunc(q)].listStringPosi(q)));
			   }
			   continue;
		   }else{
			   insert(q);
			   for(int j = 0;j<q.length()-1;j++){
				   temp += q.charAt(j);
			   }
			   q = temp;
			   temp = "";
			   System.out.println(dictionary[hashFunc(q)].listGetCode(dictionary[hashFunc(q)].listStringPosi(q)));
			   q ="";
			   i--;
		   }  
	   }
	  
	   
   }
    public static void main(String[] args) throws IOException
	{
	   Compressor test = new Compressor();
	   String str = "aaabbbabababa";
	   	test.compress(str);
	  
	}
  
}