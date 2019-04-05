

public class Node {
	private Object item;
	private Node next;
	
	
	//Default constructor or a node
	public Node(String str)
	{
		String strVal = str;
		int asciiVal = (int) str;
		next = null;
	}
	
	//constructor for item and node
	public Node(Object newItem, Node newNode)
	{
		item = newItem; 
		next = newNode;
	}


	
	public int getAsVal()
	{
		return asciiVal;
	}
	
	public String getString(){
		return strVal;
	}
	
	public Node getNext()
	{
		return next;
	}
	
	
	public void setNext(Node newNode)
	{
		next = newNode;
	}
	
}
