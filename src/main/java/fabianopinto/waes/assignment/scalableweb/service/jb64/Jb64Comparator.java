package fabianopinto.waes.assignment.scalableweb.service.jb64;

import org.json.JSONArray;

import fabianopinto.waes.assignment.scalableweb.exceptions.DiffException;

/**
 * Working class for DIFF operations.
 * 
 * Attention: This compare (DIFF) operations consider basically any valid JSON
 * data structure. There is no limitations in the format and the comparing
 * operation will be valid. The more strict specification rules, about headers
 * and data-records structure aren't considered.
 * 
 * @author fabianopinto@gmail.com
 *
 */
public class Jb64Comparator {

	/**
	 * Static entry point for working class.
	 * 
	 * @param leftData
	 *            left data array
	 * @param rightData
	 *            right data array
	 * @return DIFF operation status report
	 * @throws DiffException
	 *             For invalid data
	 */
	public static String diff(JSONArray leftData, JSONArray rightData) throws DiffException {
		return new Jb64Comparator(leftData, rightData).diff();
	}

	private JSONArray leftData;
	private JSONArray rightData;
	private StringBuilder diffResult;

	/**
	 * Working class private constructor.
	 * 
	 * @param leftData
	 *            left data array
	 * @param rightData
	 *            right data array
	 * @throws DiffException
	 *             For invalid data
	 */
	private Jb64Comparator(JSONArray leftData, JSONArray rightData) throws DiffException {
		if (leftData == null) {
			throw new DiffException("Record left data is null");
		}
		if (rightData == null) {
			throw new DiffException("Record right data is null");
		}
		this.leftData = leftData;
		this.rightData = rightData;
		this.diffResult = new StringBuilder();
	}

	/**
	 * Starts DIFF operation sequence over two JSON arrays.
	 * 
	 * @return DIFF operation report
	 */
	private String diff() {
		if (compareJSONArrays(leftData, rightData)) {
			diffResult.insert(0, "Data files are EQUAL\n");
		} else {
			diffResult.insert(0, "Data files are DIFFERENT\n");
		}
		return diffResult.toString();
	}

	/**
	 * Compare two generic elements, calling specialized methods.
	 * 
	 * @param left
	 *            left operand
	 * @param right
	 *            right operand
	 * @return True for equal elements
	 */
	private boolean compare(Object left, Object right) {
		if (left.getClass() != right.getClass()) {
			diffResult.append("Different STRUCTURE -" +
					" left: " + left.getClass().getSimpleName() +
					" right: " + right.getClass().getSimpleName() +
					"\n");
			return false;
		}
		if (left instanceof JSONArray && right instanceof JSONArray) {
			return compareJSONArrays((JSONArray) left, (JSONArray) right);
		}
		if (left.equals(right)) {
			return true;
		}
		diffResult.append("Different data ITEM -" +
				" left: " + left.toString() +
				" right: " + right.toString() +
				"\n");
		return false;
	}

	/**
	 * Compare two JSON arrays, using recursion.
	 * 
	 * @param left
	 *            left operand
	 * @param right
	 *            right operand
	 * @return True for equal arrays
	 */
	private boolean compareJSONArrays(JSONArray left, JSONArray right) {
		if (left.length() != right.length()) {
			diffResult.append("Different data SIZE - " +
					" left: " + left.length() +
					" right: " + right.length() +
					"\n");
			return false;
		} else {
			for (int index = 0; index < left.length(); index++) {
				if (!compare(left.get(index), right.get(index))) {
					diffResult.append("Data differ at index " + (index + 1) + "\n");
					return false;
				}
			}
		}
		return true;
	}

}
