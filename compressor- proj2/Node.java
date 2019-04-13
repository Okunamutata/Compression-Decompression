

public class Node {
	
	private String str;
	private int code;
	private Node next;
	//Default constructor or a node
	public Node(String newStr, int newCode)
	{
		str = newStr;
		code = newCode;
		next = null;
	}
	//constructor for item and node
	public Node(String newStr, int newCode, Node newNode)
	{
		 str = newStr;
		code = newCode;
		next = newNode;
	}
	public Node()
	{
		str = null;
		code = 0;
		next = null;
	}
	
	public Node getNode()
	{ 
		return this;
	}
	public int getCode()
	{
		return this.code;
	}
	public String getString()
	{
		return this.str;
	}
	public Node getNext()
	{
		return next;
	}
	public void setNext(Node newNode)
	{
		next = newNode;
	}
	public void setString(String newStr) 
	{
		str = newStr;
	}
	public void setCode(int newCode) {
		code = newCode;
	}
	public void setBoth(String newStr, int newKey) 
	{
		str = newStr;
		code= newKey;
	}
	public char charAt(int posi)
	{
		return str.charAt(posi);
	}
}
