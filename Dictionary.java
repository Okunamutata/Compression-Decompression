
public class Dictionary{
    
    LinkedList[] theArray;
    //Node head;
    int arraySize;
    int itemsInArray;
    int code; // current code of the dictionary    example:  aa --> 128 
    public static final int  ARRAYSIZE = nextPrime(1000);
    /*
    * Constructor for Dictionary object
    * @param int size, the size of the hashTable
    */ 
    public Dictionary()
    {
    	 arraySize = nextPrime(ARRAYSIZE);
    	 theArray = new LinkedList[arraySize];
    	 for(int i = 0; i < arraySize; i++)
         {
             theArray[i] = new LinkedList();
         }
    	 intzDic();
        
    }
    public Dictionary(int newArraySize)
    {
    	arraySize = nextPrime(ARRAYSIZE);
    	theArray = new LinkedList[arraySize];
    	for(int i = 0; i < arraySize; i++)
        {
            theArray[i] = new LinkedList();
        }
   	 	intzDic();
        
    }
    public void intzDic()
    {
    	char c;
    	String str;
        for(code = 32 ; code <= 126; code++)
        {
            //complete initializing dictionary with ASCII 
        	c=(char)code;
        	str= Character.toString(c);
        	insert(str);
        }
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
    public int averageLinkedList()
    {
    	int average;
    	average = itemsInArray / arraySize;
    	return average;
    }
    //Methos to test hashtable and rehash
    private void displayArray()
    {
        Node curr;
        int listLength;
        LinkedList list;
        for(int i  = 0; i < this.arraySize ; i++)
        {
            System.out.println("the Array index " + i + "String: " 
            		+  this.theArray[i].ListRetrieveString(i) 
            		+  "code: "+ this.theArray[i].ListRetrieveCode(i));
         
        }    
    }
    public Dictionary rehash()
    {
        int oldArrSize = theArray.length;
        LinkedList [] oldArr = theArray;
        
        // change the array size
        int newArrSize = nextPrime(oldArrSize * 2);
        // create a new table and initialize it
        Dictionary newTable = new Dictionary(newArrSize);
        intzDic();
        
        // copy the table to the new table
        for( int i = 0; i < oldArrSize ; i++  )
        {
        	String str;
        	for(int j = 0; j < oldArr[i].listLength() ; i++)
        	{
        		str = oldArr[i].ListRetrieveString(j) ;
        		insert(str);
        	}
        	
        }
        
		return newTable;
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
    public static void main(String[] args){
        String test = "aa";
        Dictionary hashtable = new Dictionary();
        hashtable.insert(test);
        
        hashtable.displayArray();// this doesn't work because not all nodes have strings or codes
        // if head node is empty (null) you can't use getString or getCode


     }
  
}