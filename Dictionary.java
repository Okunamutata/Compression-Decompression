

import java.util.Collection;



public class Dictionary{
    
    LinkedList[] theArray;
    Node head;
    int arraySize;
    int itemsInArray;

    /*
    * Constructor for Dixtionary object
    * @param int size, the size of the hashTable
    */ 
    public Dictionary(int size){
        arraySize = nextPrime(size);
        head = null;
        theArray = new LinkedList[arraySize];
        
        
        for(int i = 0; i < arraySize; i++)
        {
            theArray[i] = new LinkedList();
        }
        
    }


    public void insert(char str)
    {
        
        Node newNode = new Node(str);

        //calculate hashkey
    	Integer hashKey  = hashFunc(str);
        
        //theArray[hashKey].endInsert(newNode); 
        if(theArray[hashKey] == null){
            theArray[hashKey] = new LinkedList();
            theArray[hashKey].endInsert(newNode);

        }
        else{
            theArray[hashKey].endInsert(newNode);
            
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

    public Dictionary Rehash(int size){
        int oldArrSize = this.arraySize;
        int newArrSize = nextPrime(oldArrSize * 2);
        Dictionary newTable = new Dictionary(newArrSize);
        
       
        //loop through each index in the dictionary array
        for(int i = 0; i < oldArrSize; i++){
            LinkedList tempList = theArray[i];
            int listLength = tempList.listLength();
            Node headTemp = tempList.head;
            Node curr;

            //check if the current list is empty
            //if !empty copy and increment through the list
            if(tempList != null){
                curr = headTemp;
                if(headTemp.getNext() != null){
                    curr = headTemp.getNext();
                }
                else{
                    curr = headTemp;
                }
                //loop through the list in old hash table
                for(int j = 0; j < listLength; j++){
                    
                    Node entryTemp = curr;
                    int newKeyVal = hashFunc2(entryTemp.getChar(), newArrSize );
                    
                    //if the location on the new table is empty create a new linked list and add the copy the node
                    if(newTable.theArray[newKeyVal] == null){
                        //Node temp = newTable.theArray[newKeyVal].head;
                        //temp.endInsert(entryTemp);

                        newTable.theArray[newKeyVal].endInsert(entryTemp);
                       
            
                    }
                    else{
                        newTable.theArray[newKeyVal].endInsert(entryTemp);
                        
                    }
                }    
            }
            
        }
        return newTable;
    }
    


    

    /*
    * Creates key value for hash table insertion
    * @parama String str, the ctring to be encoded
    */
    public int hashFunc(char str)
    {
        int hashKeyVal = 0,asciiVal;
        int tableSize = this.arraySize;
        
            asciiVal = (int) str;
            hashKeyVal = (asciiVal) * 17 % tableSize;
        
       
        //
        
        
        
        return hashKeyVal;
    }

    public int hashFunc2(char str, int newArrSz)
    {
        int hashKeyVal =0 , asciiVal;
        int tableSize = newArrSz;
       
            asciiVal = (int) str;
            hashKeyVal = (asciiVal) * 17 % tableSize;
        
        
        
        
        return hashKeyVal;
    }



    public static void main(String[] args){
       char test = 'b';
       Dictionary hashtable = new Dictionary(50);
       hashtable.insert(test);
       hashtable.displayArray();

       Dictionary newdic =hashtable.Rehash(50);
       newdic.displayArray();

    }
}