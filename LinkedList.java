public class LinkedList{
    
    //member variables
     public Node head;
     private int numItem;
     
     //constructor
     public LinkedList()
     {
         head = new Node('c');
         numItem = 0;
     }
     //returns true if list is empty
     public boolean listIsEmpty()
     {
         return (numItem == 0);
     }

    
     
     //total number of elements in a list
     public int listLength()
     {
         return numItem;
     }

     public void endInsert(Node newEntry){
         Node curr;
         Node nextNode;
         
        curr = head;
         for(int i = 0; i < this.listLength()-1; i ++){
             if(curr.getNext() != null){
                 curr = curr.getNext();
                 while(curr.getNext() == null){
                     curr.setNext(newEntry);
                     ++numItem;
                 }
             }
             else{
                head.setNext(newEntry);
                ++numItem;
             }
         }
     }
     
     //returns the Node object at that particular position 
     public Node locate(int posi)
     {
         Node curr = head;
         while(posi>1)
         {
             curr = curr.getNext();
             posi--;
         }
         return curr;
     }
     
     //Retrieves all the items in a list 
     public void listRetrieve() throws IndexOutOfBoundsException
     {
         Node curr;
         char c;
         int posi = 1;
         while(posi>=1 && posi <=numItem)
         {
             curr = locate(posi);
             c = curr.getChar();
             System.out.println(c);
             System.out.println(curr.getAsVal());
             posi++;
         }
     }

     public void add(Node newNode ){
            head.setNext(newNode);
     }

     //inserts the object in the list ordered by their due date
     // check how order them! this is a function from previous  HW
     public void listInsert(int posi, char newItem) throws IndexOutOfBoundsException
     {
         if(posi >= 1 && posi <=numItem + 1)
         {
             if(posi == 1)
             {
                 Node newNode = new Node(newItem);
                 head = newNode;
             }
             else
             {
                 Node prev = locate(posi-1);
                 Node newNode = new Node(newItem);
                 prev.setNext(newNode);
             }
             numItem++;
         }
         else
             throw new IndexOutOfBoundsException();
     }
     
     
     
     //Deleted the assignment from the list 
     public void listDelete(int posi) throws IndexOutOfBoundsException
     {
         if(posi >= 1 && posi <=numItem)
         {
             Node curr;
             if(posi ==1)
             {
                 curr = head;
                 head = head.getNext();
             }
             else
             {
                 Node prev = locate(posi-1);
                 curr = prev.getNext();
                 prev.setNext(curr.getNext());
             }
             numItem--;
             curr.setNext(null);
             curr = null;
         }
         else
             throw new IndexOutOfBoundsException();
     }
     
     public void listDeleteAll()
     {
         head = null;
         numItem =0;
     }

     public static void main(String[] args){}
     

}