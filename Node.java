

public class Node {
	
	private String str;
	private Node next;
	private int code;
	
	//Default constructor or a node
	public Node(String newStr, int newCode)
	{
		String strVal = str;
		code = newCode;
		next = null;
	}
	//constructor for item and node
	public Node(String str, int newCode, Node newNode)
	{
		String strVal = str;
		code = newCode;
		next = newNode;
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
}
