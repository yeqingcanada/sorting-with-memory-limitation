package dbMiniProj1;

import java.io.UnsupportedEncodingException;
import java.lang.Byte;

/*
 * Class of tuple, store data in byte which would save some space
 */
public class Tuple implements Comparable<Tuple>{
	private Byte[] customerID = new Byte[7];
	private Byte[] firstName = new Byte[15];
	private Byte[] lastName = new Byte[15];
	private Byte[] securityNumber = new Byte[9];
	private Byte[] address = new Byte[54];

	/*
	 * Getters and setters
	 */
	public Byte[] getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Byte[] customerID) {
		this.customerID = customerID;
	}
	public Byte[] getFirstName() {
		return firstName;
	}
	public void setFirstName(Byte[] firstName) {
		this.firstName = firstName;
	}
	public Byte[] getLastName() {
		return lastName;
	}
	public void setLastName(Byte[] lastName) {
		this.lastName = lastName;
	}
	public Byte[] getSecurityNumber() {
		return securityNumber;
	}
	public void setSecurityNumber(Byte[] securityNumber) {
		this.securityNumber = securityNumber;
	}
	public Byte[] getAddress() {
		return address;
	}
	public void setAddress(Byte[] address) {
		this.address = address;
	}
	
	/*
	 * Constructor, construct a tuple with given string
	 * @param s give a string to construct the tuple
	 */
	public Tuple(String s)
	{
		//String array[] = s.split("\\s+");

	    try {
			customerID = byteToByte( (s.substring(0,7)).getBytes("US-ASCII") );
			firstName = byteToByte( (s.substring(7, 22)).getBytes("US-ASCII") );
			lastName = byteToByte( s.substring(22, 37).getBytes("US-ASCII")) ;
			securityNumber = byteToByte( s.substring(37,46).getBytes("US-ASCII")) ;
            address = byteToByte(s.substring(46, 100).getBytes("US-ASCII"));
			//customerID = byteToByte( (array[0].substring(0,7)).getBytes("US-ASCII") );
			//firstName = byteToByte( (array[0].substring(7)).getBytes("US-ASCII") );
			//lastName = byteToByte( array[1].getBytes("US-ASCII")) ;
			//securityNumber = byteToByte( array[2].substring(0,9).getBytes("US-ASCII")) ;

            //String add = array[2].substring(9);
            //for (int i = 3; i < array.length; i++)
            //        add += array[i];
            
            //address = byteToByte(add.getBytes("US-ASCII"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * An empty constructor
	 */
	public Tuple()
	{
		
	}
	
	public Tuple(byte[] b)
	{
        int j=0;
        for (int i=0; i<7; i++,j++)
        	customerID[i] = b[j];
        for (int i=0; i<15; i++,j++)
        	firstName[i] = b[j];
        for (int i=0; i<15; i++,j++)
        	lastName[i] = b[j];
        for (int i=0; i<9; i++,j++)
        	securityNumber[i] = b[j];
        for (int i=0; i<54; i++,j++)
        	address[i] = b[j];
	}
	
	/*
	 * Convert the whole info to byte array, in order to save them
	 */
	public byte[] byteArray()
	{
		int i = 0;
		Byte[] all = new Byte[100];
		for (int j = 0; j<customerID.length;j++,i++)
			all[i] = customerID[j];
		
		for (int j = 0; j<firstName.length;j++,i++)
			all[i] = firstName[j];
		for (int j = 0; j<lastName.length;j++,i++)
			all[i] = lastName[j];
		for (int j = 0; j<securityNumber.length;j++,i++)
			all[i] = securityNumber[j];
		for (int j = 0; j<address.length;j++,i++)
			all[i] = address[j];

		return ByteTobyte(all);
		
	}

	/*
	 * Set bytes with given string, encode with ASCII
	 */
	public static Byte[] byteToByte(byte b[])
	{
		Byte[] res = new Byte[b.length];
		int i=0;
		for (byte bb: b)
			res[i++]=bb;
		return res;
	}
	
	public static byte[] ByteTobyte(Byte B[])
	{
		byte [] res = new byte[B.length];
		int i=0;
		for (byte bb: B)
			res[i++]=bb;
		return res;
	}

	public void setValues(String s)
	{
		//String array[] = s.split("\\s+");

		if (s==null)
			s="9999999John           Smith          8888888881455 Maisonneuve West, Montreal, QC, H3G 1M            ";

	    try {
			customerID = byteToByte( (s.substring(0,7)).getBytes("US-ASCII") );
			firstName = byteToByte( (s.substring(7, 22)).getBytes("US-ASCII") );
			lastName = byteToByte( s.substring(22, 37).getBytes("US-ASCII")) ;
			securityNumber = byteToByte( s.substring(37,46).getBytes("US-ASCII")) ;
            address = byteToByte(s.substring(46, 100).getBytes("US-ASCII"));
			//customerID = byteToByte( (array[0].substring(0,7)).getBytes("US-ASCII") );
			//firstName = byteToByte( (array[0].substring(7)).getBytes("US-ASCII") );
			//lastName = byteToByte( array[1].getBytes("US-ASCII")) ;
			//securityNumber = byteToByte( array[2].substring(0,9).getBytes("US-ASCII")) ;

            //String add = array[2].substring(9);
            //for (int i = 3; i < array.length; i++)
            //        add += array[i];
            
            //address = byteToByte(add.getBytes("US-ASCII"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int compareTo(Tuple other)
	{
		//for (int i=customerID.length-1; i > 0; i--)
		for (int i=0; i < customerID.length; i++)
			if (this.getCustomerID()[i] != other.getCustomerID()[i])
				return this.getCustomerID()[i].compareTo(other.getCustomerID()[i]);
		return this.getCustomerID()[0].compareTo(other.getCustomerID()[0]);
	}
	
	/*
	 * Output the string of a tuple
	 */
	public String output() throws UnsupportedEncodingException
	{
		StringBuilder sb = new StringBuilder(customerID.length
		+firstName.length+lastName.length+securityNumber.length + address.length);

		//for(int i=0; i<customerID.length; i++){
		//	if (customerID[i] < 0) throw new IllegalArgumentException();
		//	sb.append((short) customerID[i]);
		//}
		
		sb.append(new String(ByteTobyte(customerID), "US-ASCII"));
		sb.append("|");
		sb.append(new String(ByteTobyte(firstName), "US-ASCII"));
		sb.append("|");
		sb.append(new String(ByteTobyte(lastName), "US-ASCII"));
		sb.append("|");
		sb.append(new String(ByteTobyte(securityNumber), "US-ASCII"));
		sb.append("|");
		sb.append(new String(ByteTobyte(address), "US-ASCII"));
		return sb.toString();
	}
	
}
