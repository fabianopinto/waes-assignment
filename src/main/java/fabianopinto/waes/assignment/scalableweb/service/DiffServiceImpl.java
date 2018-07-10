package fabianopinto.waes.assignment.scalableweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fabianopinto.waes.assignment.scalableweb.exceptions.DiffException;
import fabianopinto.waes.assignment.scalableweb.exceptions.ParseException;
import fabianopinto.waes.assignment.scalableweb.model.DiffRecord;
import fabianopinto.waes.assignment.scalableweb.repository.DiffRecordRepository;
import fabianopinto.waes.assignment.scalableweb.service.jb64.Jb64Comparator;
import fabianopinto.waes.assignment.scalableweb.service.jb64.Jb64Parser;

@Service
public class DiffServiceImpl implements DiffService {

	@Autowired
	private DiffRecordRepository repository;

	@Override
	public void inputLeft(String id, String input) throws ParseException {
		DiffRecord record = repository.findById(id).orElse(new DiffRecord(id));
		record.setLeftInput(input);
		record.setLeftData(Jb64Parser.parse(input));
		record.setResult(null);
		repository.save(record);
	}

	@Override
	public void inputRight(String id, String input) throws ParseException {
		DiffRecord record = repository.findById(id).orElse(new DiffRecord(id));
		record.setRightInput(input);
		record.setRightData(Jb64Parser.parse(input));
		record.setResult(null);
		repository.save(record);
	}
	
	@Override
	public DiffRecord diffResult(String id) throws DiffException {
		DiffRecord record = repository.findById(id).orElseThrow(() -> new DiffException("Record not found"));
		if (record.getLeftData() == null) {
			throw new DiffException("Left data is empty");
		}
		if (record.getRightData() == null) {
			throw new DiffException("Right data is empty");
		}
		record.setResult(Jb64Comparator.diff(record.getLeftData(), record.getRightData()));
		return repository.save(record);
	}

	@Override
	public void deleteRecord(String id) throws DiffException {
		DiffRecord record = repository.findById(id)
				.orElseThrow(() -> new DiffException("Record not found"));
		repository.delete(record);
	}

}
