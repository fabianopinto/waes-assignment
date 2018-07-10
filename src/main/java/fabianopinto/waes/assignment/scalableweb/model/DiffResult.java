package fabianopinto.waes.assignment.scalableweb.model;

import lombok.Data;

/**
 * Simple results of operations.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Data
public class DiffResult {

	private boolean ok;
	private String message;

	public DiffResult() {
	}

	public DiffResult(boolean ok, String message) {
		this.ok = ok;
		this.message = message;
	}

}
