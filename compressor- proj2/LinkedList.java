public class LinkedList{
    
    //member variables
     public Node head;
     public Node tail;
     private int numItem;
     
     //constructor
     public LinkedList()
     {
         head = null;
         numItem = 0;
     }
     //returns true if list is empty

     //total number of elements in a list
     public int listLength()
     {
         return numItem;
     }
     public void listInsert(String newString, int newCode)
     {
 		
			if(numItem == 0)
			{
				Node newNode = new Node(newString, newCode , head); 
				head = newNode; 
				tail = newNode;
			}
			else 
			{
				Node newNode = new Node(newString, newCode ,null);
				tail.setNext(newNode);
				tail = newNode;	
			}
			numItem++;
	}
     //returns the Node object at that particular position 
     public Node locate(int posi)
     {
         Node curr = head;
         while(posi>0)
         {
             curr = curr.getNext();
             posi--;
         }
         return curr;
     }
     //Retrieves all the items in a list 
     public boolean listContains(String s) 
     {
 		
 		Node curr = head;
 		for(int i = 0;i<numItem;i++)
 		{
 				if(s.equals(curr.getString())) 
 				{
 					return true;
 				}
 				else 
 				{
 					curr = curr.getNext();	
 				}	
 		
 			
 		}
 		return false;
     }
     public String listGetString(int posi) throws IndexOutOfBoundsException {
 		
 		if(posi >= 0 && posi < numItem)
 		{
 			Node curr = locate(posi);
 			return curr.getString();
 		}
 		else
 		{
 			throw new IndexOutOfBoundsException("Error.");
 			
 		}
 	}
     public int listGetCode(int posi) throws IndexOutOfBoundsException {
 		
 		if(posi >= 0 && posi < numItem) 
 		{
 			Node curr = locate(posi);
 			return curr.getCode();
 		}
 		else 
 		{
 			throw new IndexOutOfBoundsException("Error.");
 			
 		}
 	}
     public int listStringPosi(String str)
     {
    	 Node curr = head;
    	 for(int i= 0; i < numItem; i++)
    	 {
    		 if(str.equals(curr.getString()))
    				 return i;
    		 else
    		 {
    			 if(curr.getNext() != null)
    				 curr = curr.getNext();
    		 }
    			 
    	 }
    	 return -1;
     }

}