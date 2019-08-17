package dbMiniProj1;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import dbMiniProj1.Tuple;

public class BlockManager {
	public static final int NUM_TUPLES_PER_BLOCK = 40;
	private Tuple[] tuples = new Tuple[NUM_TUPLES_PER_BLOCK];
	
	/*
	 * An empty Constructor
	 */
	public BlockManager()
	{
		;
	}

	/*
	 * Constructor
	 * @Param t Tuples that form a block
	 */
	public BlockManager(Tuple[] t)
	{
		tuples = t;
	}
	
	/*
	 * Constructor, Construct a tuple with given lines of string, and sort
	 * @param s give a string to form a block, which is up to 40 lines
	 */
	public BlockManager(String s)
	{
		Scanner scanner  = new Scanner(s);
		int i = 0;
		while (scanner.hasNextLine())
		{
			if (i >= 40)	//Bail out if more than 40 lines
			{
				System.out.println("Over index");
				System.exit(0);
			}
			tuples[i] = new Tuple(scanner.nextLine());
			i++;
		}
		scanner.close();
	}
	
	/*
	 * Convert the whole block to byte Array
	 */
	//public byte[] byteArray()
	
	/*
	 * Clear block
	 */
	public void clear()
{
		for(int i=0; i < tuples.length; i++)
			tuples[i] = null;
		
	}

	/*
	 * Sort tuples
	 */
	public void sort() throws IOException	//Implement an in-place sorting here
	{
		java.util.Arrays.sort(tuples);
	}
	
	/*
	 * Add an element to tuple array
	 */
	public void push_back(Tuple in)
	{
		for(int i=0; i < tuples.length; i++){
			if (tuples[i] == null){ tuples[i] = in; break; }
		}
		
	}

	/*
	 * Getters and setters
	 */
	public Tuple[] getTuples() {
		return tuples;
	}

	public void setTuples(Tuple[] tuples) {
		this.tuples = tuples;
	}
	
	public void setTuple(Tuple t, int i) {
		this.tuples[i] = t;
	}
	
	
	/*
	 * Return ith element
	 * @param i index
	 */
	public Tuple at(int i) {
		return tuples[i];
	}
	
	/*
	 * I have no idea how the array in Java works, but it is quite ackward to set every element to null instead of claim space for them;
	 * Initialize a tuple with string s
	 * @param i index of the tuple
	 * @param s give a string to init
	 */
	public void initialize(int i, String s)
	{
		if (tuples[i] != null) {
			tuples[i].setValues(s);
		}
		else if (s == "")
			tuples[i] = new Tuple();
		else
            tuples[i] = new Tuple(s);
			
	}
	
	public void appendToFile(String fileName) throws IOException
	{
        try (FileOutputStream output = new FileOutputStream(fileName, true)) {
                for (int i=0; i< NUM_TUPLES_PER_BLOCK; i++)
                	if (tuples[i] != null)
                		output.write(tuples[i].byteArray());
			}
	}
	
	public int length() {
		return NUM_TUPLES_PER_BLOCK;
	}
	
}
