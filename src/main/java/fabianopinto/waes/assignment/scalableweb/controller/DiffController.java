package fabianopinto.waes.assignment.scalableweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fabianopinto.waes.assignment.scalableweb.exceptions.DiffException;
import fabianopinto.waes.assignment.scalableweb.exceptions.ParseException;
import fabianopinto.waes.assignment.scalableweb.model.DiffRecord;
import fabianopinto.waes.assignment.scalableweb.model.DiffResult;
import fabianopinto.waes.assignment.scalableweb.service.DiffService;

/**
 * Main web controller for DIFF operations.
 * 
 * @author fabianopinto@gmail.com
 *
 */
@RestController
@RequestMapping("v1/diff")
public class DiffController {

	private DiffService service;

	public DiffController(DiffService service) {
		this.service = service;
	}

	/**
	 * Receives and stores left file.
	 * 
	 * @param id
	 *            key referencing storage
	 * @param data
	 *            left data file
	 */
	@PostMapping("{id}/left")
	public ResponseEntity<DiffResult> leftPosting(@PathVariable String id, @RequestBody String data) {
		try {
			service.inputLeft(id, data);
			
			return ResponseEntity.ok(
					new DiffResult(true, "Left file successfully received"));
		} catch (ParseException e) {
			return ResponseEntity.badRequest().body(
					new DiffResult(false, e.getMessage()));
		}
	}

	/**
	 * Receives and stores right file.
	 * 
	 * @param id
	 *            key referencing storage
	 * @param data
	 *            right data file
	 */
	@PostMapping("{id}/right")
	public ResponseEntity<DiffResult> rightPosting(@PathVariable String id, @RequestBody String data) {
		try {
			service.inputRight(id, data);
			return ResponseEntity.ok(
					new DiffResult(true, "Right file successfully received"));
		} catch (ParseException e) {
			return ResponseEntity.badRequest().body(
					new DiffResult(false, "Posting failed: " + e.getMessage()));
		}
	}

	/**
	 * Return DIFF result between files, referenced by Id.
	 * 
	 * @param id
	 *            key referencing stored files
	 * @return DIFF operation results
	 */
	@GetMapping("{id}")
	public ResponseEntity<DiffResult> diffResult(@PathVariable String id) {
		try {
			DiffRecord record = service.diffResult(id);
			return ResponseEntity.ok(
					new DiffResult(true, record.getResult()));
		} catch (DiffException e) {
			return ResponseEntity.badRequest().body(
					new DiffResult(false, "No results: " + e.getMessage()));
		}
	}

	/**
	 * Deletes DIFF data from storage.
	 * 
	 * @param id
	 *            key referencing stored data
	 * @return Operation status
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<DiffResult> deleteRecord(@PathVariable String id) {
		try {
			service.deleteRecord(id);
			return ResponseEntity.ok(
					new DiffResult(true, "Record successfully removed"));
		} catch (DiffException e) {
			return ResponseEntity.badRequest().body(
					new DiffResult(false, "Can't delete: " + e.getMessage()));
		}
	}

}
