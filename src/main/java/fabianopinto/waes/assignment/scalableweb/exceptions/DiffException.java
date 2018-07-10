package fabianopinto.waes.assignment.scalableweb.exceptions;

import fabianopinto.waes.assignment.scalableweb.model.DiffRecord;

/**
 * Exception for {@link DiffRecord} handling fails.
 * 
 * @author fabianopinto@gmail.com
 *
 */
public class DiffException extends Exception {

	private static final long serialVersionUID = 5002455853141545554L;

	public DiffException(String message) {
		super(message);
	}

	public DiffException(String message, Throwable cause) {
		super(message, cause);
	}

}
