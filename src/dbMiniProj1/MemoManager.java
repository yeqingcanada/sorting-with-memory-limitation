package dbMiniProj1;
import java.io.File;

import dbMiniProj1.BlockManager;
import dbMiniProj1.Tuple;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

/*
 * Class manage blocks in memory
 */
public class MemoManager {
	public static final int NUM_BLOCKS_INPUT = 101;	//11 Blocks
	public static final int NUM_BLOCKS_OUTPUT = 1;	//11 Blocks
	private BlockManager[] blocks = new BlockManager[NUM_BLOCKS_INPUT+NUM_BLOCKS_OUTPUT];	
	
	private int []minIndex = new int[NUM_BLOCKS_INPUT];
	private int readIndex = 0;
	public boolean isFull;
	
	public MemoManager() {
		for (int i=0; i<blocks.length; i++)
			blocks[i] = new BlockManager();
	}
	
	/*
	 * Read big ASCII file into memory
	 * @param startLine line number of start point
	 * @return The line where function stopped
	 */
	public int readIntoMemo(int startLine) throws IOException
	{
		//@SuppressWarnings("resource")
		LineNumberReader reader = new LineNumberReader(new FileReader("./newBigfile.txt"), 102);

		String s = new String();

		/*Skip the lines*/
		for (int i=1; i < startLine; i++)
			reader.readLine();
		
		int i=0,j=0;
		while( i < NUM_BLOCKS_INPUT)
		{
            j=0;
			while ( j < blocks[0].length())
			{
				s = reader.readLine();
                blocks[i].initialize(j, s);
                j++;
			}

			blocks[i].sort();
			try (FileOutputStream output = new FileOutputStream("range", true)) {
                    Tuple min = blocks[i].getTuples()[0];
                    Tuple max = blocks[i].getTuples()[39];
                    if (min.compareTo(new Tuple("9999999John           Smith          8888888881455 Maisonneuve West, Montreal, QC, H3G 1M            ")) != 0)
                            output.write(min.byteArray());
                    if (max.compareTo(new Tuple("9999999John           Smith          8888888881455 Maisonneuve West, Montreal, QC, H3G 1M            ")) != 0)
                            output.write(max.byteArray());
			}
            i++;
		}
		int line = reader.getLineNumber();
		reader.close();
		return line;
	}
	
	/*
	 * Extract the minimum from tuple and update the minimum pointer
	 */
	private Tuple extractMin()
	{
		Tuple min = new Tuple("9999999John           Smith          8888888881455 Maisonneuve West, Montreal, QC, H3G 1M            ");
		int minp= 0;
		for (int i=0; i<NUM_BLOCKS_INPUT; i++)
		{
			if (minIndex[i] >= BlockManager.NUM_TUPLES_PER_BLOCK)	//If have reached the minimum
				continue;

			if (min.compareTo(blocks[i].at(minIndex[i])) > 0) {		//min > new
                min = blocks[i].at(minIndex[i]);
                minp = i;
			}
		}
		minIndex[minp]++;
		return min;
	}

	/*
	 *  Merge and output to file
	 */
	public void mergeNOutput(String fileName) throws IOException
	{
        //TODO: Merge and output to file;
		for (int i=0; i < NUM_BLOCKS_INPUT; i++){	//Read all of the tuples
			for (int j=0; j< BlockManager.NUM_TUPLES_PER_BLOCK; j++) {
				blocks[NUM_BLOCKS_INPUT+NUM_BLOCKS_OUTPUT-1].push_back(extractMin());	//The output block
			}

			blocks[NUM_BLOCKS_INPUT+NUM_BLOCKS_OUTPUT-1].appendToFile(fileName);
			blocks[NUM_BLOCKS_INPUT+NUM_BLOCKS_OUTPUT-1].clear();
		}

		for (int i=0; i < minIndex.length; i++)		//reset index
			minIndex[i] = 0;
	}
	
	/*
	 * Simply write whole memory to file
	 */
	public void appendToFile(String fileName) throws IOException
	{
		for (int i=0; i<(NUM_BLOCKS_INPUT+NUM_BLOCKS_OUTPUT);i++)
			if (blocks[i] != null)
                    blocks[i].appendToFile(fileName);
	}
	
	/*
	 * Push a tuple to memo
	 */
	public void push_back(Tuple t)
	{
		blocks[readIndex/40].setTuple(t, readIndex%(40));
		readIndex++;
		if (readIndex >= 40*NUM_BLOCKS_INPUT+1)
			isFull = true;
	}
	
	
	public void clear()
	{
		for(int i=0; i<blocks.length; i++)
			blocks[i].clear();
		readIndex=0;
		isFull=false;
	}

}
