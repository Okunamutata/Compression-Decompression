

import java.util.Collection;


import List.Node;
public class Dictionary{
    public int ts; //table size
    LinkedList[]table;


    public Dictionary(int size){ // does Int works?
        Node head = new Node();
        table = new LinkedList[nextPrime(size)];
        
        for(int i = 0; i < table.length; i++)
        {
            table[i].add(head);
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

    public Dictionary Rehash(int size, Dictionary oldTable){
        
        Dictonary temp = new Dictionary[nextPrime(size)];
        int ts = temp.length;
        int oldLength = oldTable.length;
        for(int i = 0; i < oldTable.length; i++){
            int listLength = table[i].listLength();
            if(table[i].head.getNext() != null){

                for(int j = 0; j < listLength; j++){
                    Node entryTemp = table[j].head.getNext();
                    int hash = hashCode(entryTemp.getString(), nextPrime(size) );
                    for(int x = 0; x < temp.length; x++){
                        if(temp[x].head.getNext() == null){
                            temp[x].head.setNext(entryTemp);
                        }
                        else{
                            if(temp[x].getNext != null){ temp[x].setNext(entryTemp);}
                            
                        }
                    }
                    
                }
            }
        }
        return temp;
    }
    


    public void makeEmpty(){
        for(int i = 0; i < table.length; i++){
            table[i].remove();

             //why save table size?
             ts = 0;
        }
    }
    public int hashCode(String str, int ts)
    {
        int index;
        int asciiVal = (int) str;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            index += (int) c * 17;
        }
        
        index =  (index * 31) % ts;
        return index;
    }

    public void insert(String str, int ts)
    {
    	int tablePosition = hashCode(str, ts);
    	
        int tableHead;
        Node newNode = new Node((ascii) index);
        tableHead = dictionary[index];
        if(table.head.getNext() == null){
            head.setNext(newNode());
        }
        else{
            Node curr = head;
                if(curr.getNext() == null){
                    curr.setNext(newNode()); 
                }
            
        }
    }



    public static void main(String[] args){
       
    }
}