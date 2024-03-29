/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.*;

import java.util.*;
import java.io.*;

public class NameSurferDataBase implements NameSurferConstants {
	
	//instant variables
	private HashMap <String, String> nameDataBase;
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		nameDataBase=new HashMap <String, String> ();
		try {
			BufferedReader rd=new BufferedReader(new FileReader(filename));
			while (true) {
				String line=rd.readLine();
				if (line==null) break;
				StringTokenizer tokenizer = new StringTokenizer(line);
				nameDataBase.put(tokenizer.nextToken(), line);
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException (ex);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		//not case sensitive (NCS)
		String nameNCS="";
		nameNCS+=Character.toUpperCase(name.charAt(0));
		for (int i=1; i<name.length(); i++) {
			nameNCS+=Character.toLowerCase(name.charAt(i));
		}
		
		//search for the name in the database
		String n=nameDataBase.get(nameNCS); 
		if (n==null) { //if here doesn't return null, the program will stop if user 
			return null; //enter a name that doesn't exist in the data base.
		} else {
			return new NameSurferEntry(n);
		}
	}
}

