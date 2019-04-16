import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Decompressor {

	Key [] dictionary;
	int code = 32; // current code of the dictionary    example:  aa --> 128 
	public static final int  ARRAYSIZE =10000;
	int itemsInArray = 0;
	int doublicate;
	
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
		doublicate++;
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
	public int doublicateCount()
	{
		return doublicate;
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
		Scanner input = new Scanner(System.in);
		boolean runAgain = true;
		String command = null;
		String f;
		String append  = ".txt";
		long startTime=0, endTime=0;
        double time=0;
		do
		{
			try 
			{
				//System.out.println(file.decompress("test.txt.zzz"));
				if(args.length >= 1)
				{ 
					f = args[0];
				}
				else
				{
					System.out.println("Please enter the name of the file you wish to decompress");
					f = input.nextLine();
				}
					File fileInput = new File(f);
					if(fileInput.exists())
					{
						if(!f.contains(append)){ f += ".txt";}
						BufferedWriter output = new BufferedWriter(new FileWriter(f + ".txt"));
						String strOut;
						startTime = System.nanoTime();
						strOut = file.decompress(f);
						endTime = System.nanoTime();
						time = (endTime - startTime)/1000000000.0;
						
					    output.write(strOut);
					    System.out.println("New decompressed file created! \n");
					    output.close();
					}
					else
					{
						 System.out.println("Enter a valid file name!");
		                  
					}
					 File deComp = new File(f );
					BufferedWriter log = new BufferedWriter(new FileWriter(f + ".txt.log"));
		              log.write("decompression of " + deComp+"\n");
		              log.write("Compression took " + time  + " seconds\n");
		              log.write("The table was dooubled "+ file.doublicateCount() + " times\n");
		              log.close();
					//Ask user to run again
					System.out.println("Do you wish to run again? \n" + "Enter 'Y' for yes or 'N' for no ");
					command = input.nextLine();
					
				
			} catch (IOException e) {
				
				System.out.println("File Not Found!!");
			}
			 if(command.equalsIgnoreCase("Y"))
	          { 
	        	  runAgain = true;
	          } 
	          else
	          {
	        	  runAgain = false; 
	        	  System.exit(0);
	           } // terminate the program if anything but yes is pressed 
		}
		while(runAgain == true);
		
		
	}
  
 }
