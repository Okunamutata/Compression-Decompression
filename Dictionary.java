
public class Dictionary{
    
    LinkedList[] theArray;
    Node head;
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
        
        for(int i = 0; i < arraySize; i++)
        {
            theArray[i] = new LinkedList();
        }
        
    }
    public Dictionary(int newArraySize)
    {
    	arraySize = newArraySize;
        
        for(int i = 0; i < arraySize; i++)
        {
            theArray[i] = new LinkedList();
        }
        
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
        Node newNode = new Node(str, code );

        //calculate hashkey
    	int hashKey  = hashFunc(str);
        LinkedList whichList = theArray[hashFunc(str)];
        if(!whichList.ListContains(str))
        {
        	whichList.ListInsertEnd(str, code);
        	itemsInArray++;
        	//if(averageLinkedList() > 10 )
        	// rehash();
        }
        
    }
    public static int nextPrime(int n){
        if (n % 2 == 0)
            n++;
        for ( ; !isPrime( n ); n += 2);
 
        return n;

    }
    public static boolean isPrime(int n ){
        if (n == 2 || n == 3){ 
            return true;
        }
        if (n == 1 || n % 2 == 0){
            return false;
        }
        for (int i = 3; i * i <= n; i += 2){
            if (n % i == 0){
                return false;
            }
        }
        return true;
       
    }
    //Methos to test hashtable and rehash
    private void displayArray(){
        Node curr;
        int listLength;
        LinkedList list;
        for(int i  = 0; i < this.arraySize-1; i++){
            System.out.println("the Array index " + i);
            list = theArray[i];
            //listLength = theArray[i].listLength();
            
        }    
    }
    public Dictionary Rehash()
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
   
   
  
}