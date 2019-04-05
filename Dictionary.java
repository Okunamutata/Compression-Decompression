

import java.util.Collection;
import java.util.LinkedList;

import List.Node;
public class Dictionary{
    public int ts; //table size
    
    public Dictionary(int size){ // does Int works?
       Node head = new Node();
        LinkedList<Node> []table = new LinkedList<Node>[nextPrime(size)];
        
        for(int i = 0; i < table.length; i++)
        {
            table[i] = ( new LinkedList());
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

    public Dictionary Rehash(int size){
        //Dictionary newDic = new Dictionary(nextPrime(size));
        Dictonary temp = new Dictionary[nextPrime(size)];
        int ts = temp.length;
        int index = 0;
        for(int i = 0; i < table.length; i++){
            int listLength = table[i].size();
            if(table[i].peek() != null){

                for(int j = 0; j < listLength; j++){
                    Node entryTemp = table[j].peek();
                    int hash = hashCode(entryTemp.getString(), ts );
                    for(int x = 0; x < temp.length; x++){
                        if(temp[x].peek() == null){
                            temp[x] = new LinkedList<Node>();
                            temp[x].add(entryTemp);
                        }
                        else{
                            temp[x].add(entryTemp);
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