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
     public boolean listIsEmpty()
     {
         return (numItem == 0);
     } 
     //total number of elements in a list
     public int listLength()
     {
         return numItem;
     }
     public void ListInsertEnd(String newString, int newCode)
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
         while(posi>1)
         {
             curr = curr.getNext();
             posi--;
         }
         return curr;
     }
     //Retrieves all the items in a list 
     public boolean ListContains(String s) 
     {
 		boolean c = false;
 		for(int i = 0;i<numItem;i++)
 		{
 			
 			//if(head.getString() != null)
 		//	{
 				if(s.equals(head.getString())) 
 				{
 					return c = true;
 				}
 				else 
 				{
 					if(i<numItem-1) 
 					{
 					head = head.getNext();
 					}
 				}	
 		//	}
 			
 		}
 		return c;
     }
     public void listRetrieve() throws IndexOutOfBoundsException
     {
         Node curr;
         String c;
         int posi = 1;
         while(posi>=1 && posi <=numItem)
         {
             curr = locate(posi);
             c = curr.getString();
             System.out.println(c);
             System.out.println(curr.getCode());
             posi++;
         }
     }
     public String ListRetrieveString(int posi) throws IndexOutOfBoundsException {
 		
 		if(posi >= 0 && posi <= numItem)
 		{
 			Node curr = locate(posi);
 			return curr.getString();
 		}
 		else
 		{
 			throw new IndexOutOfBoundsException("Error.");
 			
 		}
 	}
     public int ListRetrieveCode(int posi) throws IndexOutOfBoundsException {
 		
 		if(posi >= 0 && posi <= numItem) 
 		{
 			Node curr = locate(posi);
 			return curr.getCode();
 		}
 		else 
 		{
 			throw new IndexOutOfBoundsException("Error.");
 			
 		}
 	}
     public void add(Node newNode ){
            head.setNext(newNode);
     }
     public void listDeleteAll()
     {
         head = null;
         numItem =0;
     }
}