package dbMiniProj1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import dbMiniProj1.Tuple;

public class FileManager {

	private RandomAccessFile file;
	/*
	 * Open a file
	 */
	public FileManager(String fileName) throws IOException
	{
		file=new RandomAccessFile(fileName, "r");
	}

	/*
	 * Return ith tuple of the file
	 */
	public Tuple getTuple(int i) throws IOException
	{
		byte myByte[] = new byte[100];
		file.seek(i*100);
		file.read(myByte);
		return new Tuple(myByte);
	}

}
