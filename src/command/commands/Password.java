/** This will take the password from Ch.class
 *  and hash it, then send it back to Ch.class
 */
package command.commands;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author devore07
 *
 */
public class Password {
	
	public Password(Ch password){
		System.out.println(password);
	}
	
	public static String hash(String password){
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); //sets hash type
			md.update(password.getBytes());//converts password String to hash
			byte passwordByte[] = md.digest();
			
			//convert byte to hex
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<passwordByte.length;i++) {
				sb.append(Integer.toString((passwordByte[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString(); //returns hashed password as String
		} catch(NoSuchAlgorithmException cnse) {
			 //massive failure
		}
		
		return null;
	}
}
