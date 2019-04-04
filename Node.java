

public class Node {
	private Object item;
	private Node next;
	
	
	//Default constructor or a node
	public Node(Object newItem)
	{
		item = newItem;
		next = null;
	}
	
	//constructor for item and node
	public Node(Object newItem, Node newNode)
	{
		item = newItem; 
		next = newNode;
	}
	
	public Object getItem()
	{
		return item;
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
