package fabianopinto.waes.assignment.scalableweb.exceptions;

/**
 * General exception for parsing processes.
 * 
 * @author fabianopinto@gmail.com
 *
 */
public class ParseException extends Exception {

	private static final long serialVersionUID = 1144754723344373829L;

	public ParseException(String message) {
		super(message);
	}

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

}
