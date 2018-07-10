package fabianopinto.waes.assignment.scalableweb.util;

import java.util.Base64;

import javax.validation.constraints.NotNull;

/**
 * Utility class for Base64 operations.
 * 
 * @author fabianopinto@gmail.com
 *
 */
public class Base64Utils {

	/**
	 * Decode a Base64 string.
	 * 
	 * @param input
	 *            Base64 encoded string
	 * @return Decoded string
	 * @throws IllegalArgumentException
	 *             Input not in valid Base64 scheme
	 * @see https://www.base64decode.org/
	 */
	public static String decode(String input) throws IllegalArgumentException {
		byte[] bytes = Base64.getDecoder().decode(input);
		return new String(bytes);
	}

}
