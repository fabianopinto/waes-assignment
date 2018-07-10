package fabianopinto.waes.assignment.scalableweb.service.jb64;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;

import fabianopinto.waes.assignment.scalableweb.exceptions.ParseException;
import fabianopinto.waes.assignment.scalableweb.util.Base64Utils;
import fabianopinto.waes.assignment.scalableweb.util.Jb64Utils;
import fabianopinto.waes.assignment.scalableweb.util.Md5Utils;

/**
 * Working class for JSON Base64 parsing.
 * 
 * Attention: This JSON Base64 parsing implementation is more loose considering
 * the final JSON object data structure. The rules about headers and type
 * conversions aren't considered, so any valid JSON structure will be parsed and
 * available for data files comparing (the DIFF).
 * 
 * @see https://jb64.org/specification/
 * 
 * @author fabianopinto@gmail.com
 *
 */
public class Jb64Parser {

	/**
	 * Static entry point for working class.
	 * 
	 * @param input
	 *            encoded data
	 * @return decoded data
	 * @throws ParseException
	 *             If anything goes wrong
	 */
	public static JSONArray parse(String input) throws ParseException {
		return new Jb64Parser(input).getJson();
	}

	private String input;
	private List<String> lines;
	private JSONArray json;

	/**
	 * Working class private constructor.
	 * 
	 * @param input
	 *            encoded data
	 */
	private Jb64Parser(String input) {
		this.input = input;
	}

	/**
	 * Parse input data, doing line split and decoding.
	 * 
	 * @return Decoded lines
	 * @throws ParseException
	 *             Parsing error
	 */
	public List<String> getLines() throws ParseException {
		if (lines == null) {
			lines = decodeLines(splitInput(input));
		}
		return lines;
	}

	/**
	 * Split input data and checks MD5 hashing, if existent.
	 * 
	 * @param input
	 *            Encoded data
	 * @return Data lines
	 * @throws ParseException
	 *             MD5 hashing error
	 */
	private List<String> splitInput(String input) throws ParseException {
		List<String> lines = Jb64Utils.splitLines(input);
		if (!lines.stream().allMatch(Md5Utils::check)) {
			throw new ParseException("File corrupted (MD5)");
		}
		return lines;
	}

	/**
	 * Decode Base64 data lines.
	 * 
	 * @param split
	 *            encoded lines
	 * @return Decoded lines
	 * @throws ParseException
	 *             Invalid or corrupted file
	 */
	private List<String> decodeLines(List<String> split) throws ParseException {
		try {
			List<String> lines = split.stream().map(line -> Base64Utils.decode(Md5Utils.data(line)))
					.collect(Collectors.toList());
			if (lines.isEmpty()) {
				throw new ParseException("Empty file");
			}
			return lines;
		} catch (IllegalArgumentException e) {
			throw new ParseException("File corrupted", e);
		}
	}

	/**
	 * Decode and converts data input to JSON array.
	 * 
	 * @return Decoded JSON array
	 * @throws ParseException
	 *             Corrupted data
	 */
	private JSONArray getJson() throws ParseException {
		if (json == null) {
			json = new JSONArray(processLines(getLines()));
		}
		return json;
	}

	/**
	 * Decode and converts each data line to JSON arrays.
	 * 
	 * @param lines
	 *            encoded lines
	 * @return list of JSON arrays
	 * @throws ParseException
	 *             Corrupted data
	 */
	private List<JSONArray> processLines(List<String> lines) throws ParseException {
		AtomicReference<Exception> exception = new AtomicReference<>();
		List<JSONArray> list = lines.stream().map(line -> {
			try {
				return new JSONArray(line);
			} catch (JSONException e) {
				exception.set(e);
				return null;
			}
		}).collect(Collectors.toList());
		if (exception.get() != null) {
			throw new ParseException("Error parsing data", exception.get());
		}
		return list;
	}

}
