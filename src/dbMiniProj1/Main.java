package dbMiniProj1;
import dbMiniProj1.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Main {
	public static void main(String args[]) throws IOException
	{
		//Runtime rt = Runtime.getRuntime();
		
		//long before = rt.freeMemory();
		//System.out.println(rt.freeMemory()/1024/1024);
		
		//LineNumberReader reader = new LineNumberReader(new FileReader("./newBigfile.txt"));
		

		//Tuple[] tupleArray = new Tuple[40];
		

		//String s = reader.readLine();

		//int i = 0;
		//while (s != null)
		//{
		//	tupleArray[i] = new Tuple(s);
		//	

		//	s=reader.readLine();

		//	i++;
		//	if (i >= 40)
		//	{
        //        java.util.Arrays.sort(tupleArray);
		//		for (int j = 0; j < tupleArray.length; j++)
		//			System.out.println(tupleArray[j].output());
		//		i = 0;
		//		long after = rt.freeMemory();
        //        System.out.println("Memory left: "+after/1024/1024);
		//	}
		//}
		
		//reader.close();
        //System.out.println("Memory left: "+rt.freeMemory()/1024/1024);

		MemoManager memory = new MemoManager();

		for (int i=0; i<=40040/(MemoManager.NUM_BLOCKS_INPUT * BlockManager.NUM_TUPLES_PER_BLOCK); i++)
		{
			System.out.println("Reading from "+i*(MemoManager.NUM_BLOCKS_INPUT * BlockManager.NUM_TUPLES_PER_BLOCK)+" line. "+"Free memory left "+Runtime.getRuntime().freeMemory()/1024/1024);
			memory.readIntoMemo(i*(MemoManager.NUM_BLOCKS_INPUT * BlockManager.NUM_TUPLES_PER_BLOCK));
			memory.mergeNOutput(i+".out");
		}

        memory.clear();
        System.out.println("Merging to file...");
        
        FileManager[] files = new FileManager[40040/BlockManager.NUM_TUPLES_PER_BLOCK/MemoManager.NUM_BLOCKS_INPUT];
        for (int i=0; i<files.length;i++)
        	files[i]=new FileManager(i+".out");


        int minIndex[] = new int[files.length];
        int fuckThisVariable = 0;
        
        for (int i=0; i<40040;i++)   {      //for all of the tuples
        	Tuple min = new Tuple("9999999John           Smith          8888888881455 Maisonneuve West, Montreal, QC, H3G 1M            ");
            for (int j=0; j<minIndex.length; j++)
            	//Extract min
            {
                //TODO: Extract min
                if (minIndex[j] > MemoManager.NUM_BLOCKS_INPUT * BlockManager.NUM_TUPLES_PER_BLOCK || 
                		files[j].getTuple(minIndex[j]).compareTo(new Tuple("9999999John           Smith          8888888881455 Maisonneuve West, Montreal, QC, H3G 1M            ")) == 0)
                	continue;
                else if(min.compareTo(files[j].getTuple(minIndex[j])) > 0)
                {
                	min = files[j].getTuple(minIndex[j]);
                	fuckThisVariable = j;
                }
            }
            

            minIndex[fuckThisVariable]++;
            memory.push_back(min);
            if (memory.isFull);
            {
            	memory.appendToFile("sorted");	
            	memory.clear();
            }
        }
        System.out.println("Done!");


	}

}
