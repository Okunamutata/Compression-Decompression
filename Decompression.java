import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decompressor {

	Key [] dictionary;
	int code = 32; // current code of the dictionary    example:  aa --> 128 
	public static final int  ARRAYSIZE =10000;
	int itemsInArray = 0;
	
	
	public Decompressor()
	{
    	dictionary = new Key[ARRAYSIZE];
        for(int i = 0; i < dictionary.length; i++)
        {
            dictionary[i] = new Key();
        }
   	 	intzDic();
      
	}
	public void dublicateDict()
	{
		Key [] oldDic;
		oldDic = dictionary;
		int newDicSize = (dictionary.length * 2 );
		
		dictionary = new Key[newDicSize];
		for(int i = 0; i < dictionary.length; i++)
        {
            dictionary[i] = oldDic[i];
        }
		
	}
	public void intzDic()
	{
		char codeC;
		String str;
		for(codeC = 32 ; codeC < 127; codeC++)
        { 
            //complete initializing dictionary with ASCII 
        	
        	str= Character.toString(codeC);
        	dictionary[codeC-32].setBoth(str, code); 
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
		dictionary[code-32].setBoth(str, code);
		code++;
		itemsInArray++;
		if(itemsInArray == dictionary.length )
			dublicateDict();
		
    }
	public String process(int q , int p)
	{
		String output= "";
		
		
		// p= q + proceeds 
		if( dictionary[p-32].getString() != null ) // if p is in the dic meaning not Null
		{
			output += dictionary[p-32].getString();
			insert(dictionary[q-32].getString()+dictionary[p-32].charAt(0) );
		}
		else // if it is not in the dictionary
		{
			output += dictionary[q-32].getString() + dictionary[q-32].getString().charAt(0);
			insert(dictionary[q-32].getString() + dictionary[q-32].getString().charAt(0));
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
		output += dictionary[p-32].getString(); //output the text corresponding to the first code
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
			System.out.println(file.decompress("test.txt.zzz"));
			
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