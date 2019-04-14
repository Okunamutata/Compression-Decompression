

public class Key {
	
	private String str;
	private int code;
	
	//Default constructor or a node
	public Key(String newStr, int newCode)
	{
		str = newStr;
		code = newCode;
		
	}
	public Key()
	{
		str = null;
		code = 0;
		
	}
	public Key getNode()
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
