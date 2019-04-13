import java.io.*;
import java.lang.*;
import java.util.*;
public class Compressor{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean repeat = true;
        File file;
        String inputfile, outputfile, runAgian;
        ArrayList<Integer> compressed = new ArrayList<Integer>();

        try {
            file = new File(args[0]);
            
        } catch (FileNotFoundException e) {
            //TODO: handle exception
            System.out.println("Please enter a valid file");
            System.out.println("Please enter the file you wish to compress");
                inputfile = input.nextLine();
                file = new File(inputfile);
            
        }
        while(repeat == true){
        //Initalize Dictionary
        Dictionary dt = new Dictionary();
        dt.intzDic();


    

       
            BufferedReader br = null;
            String  line, temp1, q, p;
            int code = 0;
            Integer text;

        try {
                br = new BufferedReader(new FileReader(file));
                int i = 0;
                while((line = br.readLine()) != null){
                
                    
                        p =  Character.toString(line.charAt(i));
                        
                        
                        q = (Character.toString(line.charAt(i+1)) + p);
                        code = dt.hashFunc(q);
                        if(dt.theArray[code] == null ){
                            dt.insert(q);
                            compressed.add(code);
                            i++;
                        }else {//((dt.theArray[code] != null) && (dt.theArray[code].ListContains(q) == false)) {
                            dt.theArray[code].ListInsertEnd(q, code);
                            compressed.add(code);
                            i++;
                            q = "";
                        }
                }
                br.close();
        }
        catch (IndexOutOfBoundsException e) {
                //TODO: handle exception
                System.out.println("Fix it");
        }    
            
            //Prompt users for outputfile
            System.out.println("Please enter the file you wish to write to: ");
            outputfile = input.nextLine();
            fileWriter(outputfile, compressed);
            
            System.out.println("Do you wish to run again!?  \n Enter 'Y' to run again \n Enter 'N' for no");
            runAgian = input.nextLine(); 
            if(runAgian.equalsIgnoreCase("N")){
                repeat = false;
                break;
            }
        }
        System.exit(0);

    }

    public static void fileWriter(String filename, ArrayList<Integer> data){
        try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(filename+".zzz"));
            for(int i: data){
                out.writeInt(i);
            }
            out.close();
        }
        catch(IOException e){
            
        }
    }
}