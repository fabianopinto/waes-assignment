package fabianopinto.waes.assignment.scalableweb.util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Utility class for Base64 parsing and string manipulation.
 * 
 * @author fabianopinto@gmail.com
 *
 */
public class Jb64Utils {

	private static final Pattern NOISE = Pattern.compile("^\\s+|[^\\n]+\\z");
	private static final Pattern SEPARATOR = Pattern.compile("\\s*\\r?\\n\\s*");

	/**
	 * Removes leading blanks, trailing noise (after last \n) and split/trim in
	 * individual lines.
	 * 
	 * @param input
	 *            Input data string
	 * @return List of separated/trimmed lines
	 */
	public static List<String> splitLines(String input) {
		return Arrays.asList(SEPARATOR.split(NOISE.matcher(input).replaceAll("")));
	}

}