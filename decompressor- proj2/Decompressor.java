import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decompressor {

	Node [] dictionary;
	int arraySize;
	int code = 32; // current code of the dictionary    example:  aa --> 128 
	public static final int  ARRAYSIZE =10000;
	
	
	public Decompressor()
	{

    	arraySize=ARRAYSIZE ;
    	dictionary = new Node[arraySize];
        for(int i = 0; i < arraySize; i++)
        {
            dictionary[i] = new Node();
        }
   	 	intzDic();
      
		
	}
	public void intzDic()
	{
		char c, codeC;
		String str;
		for(codeC = 32 ; codeC < 127; codeC++)
        { 
            //complete initializing dictionary with ASCII 
        	c=(char)codeC;
        	str= Character.toString(c);
        	dictionary[codeC].setBoth(str, code); 
        	code++;
        }
		insert("\b");
        insert("\t");
        insert("\n");
        insert("\f");
        insert("\r");
	}
	
	public void insert(String str)
    {
		dictionary[code].setBoth(str, code);
		code++;
    }
	public String process(int q , int p)
	{
		String output= "";
		
		
		// p= q + proceeds 
		if( dictionary[p].getString() != null ) // if p is in the dic meaning not Null
		{
			output += dictionary[p].getString();
			insert(dictionary[q].getString()+dictionary[p].charAt(0) );
		}
		else // if it is not in the dictionary
		{
			output += dictionary[q].getString() + dictionary[q].getString().charAt(0);
			insert(dictionary[q].getString() + dictionary[q].getString().charAt(0));
		}
		return output;
	}
	
	public String decompress(String fileName) throws IOException
	{
		String output= "";
		int p , q;
		
		DataInputStream in = new DataInputStream(new FileInputStream(fileName));
		File f = new File(fileName);
	
		p = in.readInt(); 
		output += dictionary[p].getString(); //output the text corresponding to the first code
			q=p;         // q is the first Integer then p precedes in the next loop
		
		for(int i = 0 ; i < ((f.length()-4) / 4) ; i++)
		{
			p = in.readInt(); // p precedes the q
			output += process(q,p); // process q and precedes 
			q=p;     // move q to the next Integer
		}
		in.close();
		
		return output;
	}

	public static void main(String[] args)
	{
		Decompressor file = new Decompressor();
		
		try 
		{
			System.out.println(file.decompress("text.txt.zzz"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!!");
		}
		
		/*
		try {
			DataOutputStream o = new DataOutputStream(new FileOutputStream("bin"));
			o.writeInt(97);
			o.writeInt(98);
			o.writeInt(128);
			o.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
	}
  
 }
