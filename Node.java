

public class Node {
	private char str, strVal;
	private Node next;
	int asciiVal;
	int hashKey;
	
	
	//Default constructor or a node
	public Node(char str)
	{
		 char strVal = str;
		asciiVal = (int) str;
		next = null;
	}
	
	//constructor for item and node
	public Node(char str, int hk)
	{
		hashKey = hk;
		char strVal = str;
		asciiVal = (int) str;
		next = null;
	}

	public Node getNode(){ return this;}

	
	public int getAsVal()
	{
		return asciiVal;
	}
	
	public char getChar(){
		return this.strVal;
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
