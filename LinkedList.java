/*
*	The Class creates a LinkedList structure
*   @author Tobenna Okunna, Faisal Binateeq
*	04/15/19
*/
public class LinkedList{
    
	//member variables
	public static int longest;
     public Node head;
     public Node tail;
     public int numItem;
     
     // Default constructor
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
	 
	 /*
	 *	method to insert string and the code for the string
	 *  @param String newString, the string being added
	 *  @parama int newCode, the coded string
	 */
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
			if(numItem > longest){
				longest = numItems;
			}
	}


     /*
	 * method to locate a node at a given position
	 * @param int posi, the position on the list 
	 * @return Node , the Node object at that particular position 
	 */
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
	 

	 /* 
	 * method to check if the list contains a given string 
	 * @param String s, the string to be searched for
	 * @return boolean, true if s is found, false otherwise
	 * 
	 */
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
	 
	 /*
	 * method to get the string item at a given position
	 * @param int posi, the position on the list
	 * @return String, the string of the node at the given position
	 */
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
	 
	 /*
	 * method to get the code item from a given position on the list 
	 * @param int posi, the position on the list
	 * @return int the code of the node at the position
	 */
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