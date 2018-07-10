package fabianopinto.waes.assignment.scalableweb.service;

import fabianopinto.waes.assignment.scalableweb.exceptions.DiffException;
import fabianopinto.waes.assignment.scalableweb.exceptions.ParseException;
import fabianopinto.waes.assignment.scalableweb.model.DiffRecord;

public interface DiffService {

	/**
	 * Stores first file (left) referenced by Id.
	 * 
	 * @param id
	 *            reference for first file
	 * @param input
	 *            first data file
	 */
	void inputLeft(String id, String input) throws ParseException;

	/**
	 * Stores second file (right) referenced by Id.
	 * 
	 * @param id
	 *            reference for second file
	 * @param input
	 *            second data file
	 */
	void inputRight(String id, String input) throws ParseException;

	/**
	 * Performs diff operation of input files referenced by Id.
	 * 
	 * @param id
	 *            reference for input files
	 * @return The full {@link DiffRecord} result
	 */
	DiffRecord diffResult(String id) throws DiffException;

	/**
	 * TODO
	 * 
	 * @param id
	 * @throws DiffException
	 */
	void deleteRecord(String id) throws DiffException;

}
