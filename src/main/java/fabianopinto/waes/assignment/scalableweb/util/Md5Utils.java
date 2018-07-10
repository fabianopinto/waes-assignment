package fabianopinto.waes.assignment.scalableweb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import lombok.extern.java.Log;

/**
 * Utility class for MD5/hexadecimal conversions.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Log
public class Md5Utils {

	private static final Pattern HASH_SUFFIX = Pattern.compile("\\.[0-9a-f]{32}$");

	/**
	 * Splits hash suffix from Base64 encoded string and do basic JSON Base64
	 * characters conversions.
	 * 
	 * @param line
	 *            encoded data with optional MD5 suffix
	 * @return Data without hash suffix
	 */
	public static String data(String line) {
		return HASH_SUFFIX.matcher(line).replaceAll("").replace("-", "+").replace("_", "/");
	}

	/**
	 * Checks Base64 encoded string against MD5 hash suffix, if detected.
	 * 
	 * @param line
	 *            encoded data with optional MD5 suffix
	 * @return True for valid data
	 */
	public static boolean check(String line) {
		if (HASH_SUFFIX.matcher(line).find()) {
			String data = line.substring(0, line.length() - 33);
			String hash = line.substring(line.length() - 32);
			return check(data, hash);
		}
		return true;
	}

	/**
	 * Checks Base64 encoded string against MD5 hash.
	 * 
	 * @param data
	 *            encoded data
	 * @param hash
	 *            MD5 hash suffix
	 * @return True for valid hashing
	 */
	public static boolean check(String data, String hash) {
		try {
			return Arrays.equals(Hex.decodeHex(hash), md5(data));
		} catch (DecoderException e) {
			return false;
		}
	}

	/**
	 * Uses the MD5 algorithm to encode a string.
	 * 
	 * @param input
	 *            input string to perform MD5
	 * @return The 128-bit hash value
	 * 
	 * @see https://en.wikipedia.org/wiki/MD5
	 * @see http://www.md5.cz/
	 */
	public static byte[] md5(String input) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			return messageDigest.digest(input.getBytes());
		} catch (NoSuchAlgorithmException e) {
			log.severe("MD5 algorithm not available: " + e.getMessage());
			return null;
		}
	}

}
