package fabianopinto.waes.assignment.scalableweb.model;

import org.json.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Data of DIFF operations.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@Document(collection = "diff")
@Data
public class DiffRecord {

	@Id
	private String id;

	private String leftInput;
	private String rightInput;

	private JSONArray leftData;
	private JSONArray rightData;

	private String result;

	public DiffRecord(String id) {
		this.id = id;
	}

}
