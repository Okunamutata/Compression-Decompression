
import java.util.ArrayList;
 
import List.Node;

public class Dictionary{
    ArrayList<Node> table;
    public int ts; //table size
    
    public Dictionary(int size){ // does Int works?
       
        table = new ArrayList<Node>(nextPrime(size));
        
        for(int i = 0; i < table.length; i++){
            table.add(new LinkedList<Node>());

           
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

    public void rehash(){

    }
    
    public void intzDic(){
        for(int i = 32; i < 126; i++){
            int hash = hashCode(i, 191);
            //complete initializeing dicitionary wth ascii 

        }
    }


    public void makeEmpty(){
        for(int i = 0; i < table.length; i++){
            table.remove(i);

             //why save table size?
             ts = 0;
        }
    }
    public int hashCode(int asciiVal, int ts){
        int index;
        index = (int) (asciiVal * 31) % ts;
        return index;
    }

    public void insert(int index ){
        short tableHead;
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