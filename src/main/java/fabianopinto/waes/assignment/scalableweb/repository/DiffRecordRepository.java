package fabianopinto.waes.assignment.scalableweb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fabianopinto.waes.assignment.scalableweb.model.DiffRecord;

/**
 * MongoDB repository for DIFF operations data.
 * 
 * @author fabianopinto@gmail.com
 *
 */
public interface DiffRecordRepository extends MongoRepository<DiffRecord, String> {
}
