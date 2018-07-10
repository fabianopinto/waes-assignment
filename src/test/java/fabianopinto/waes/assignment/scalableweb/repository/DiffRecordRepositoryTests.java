package fabianopinto.waes.assignment.scalableweb.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import fabianopinto.waes.assignment.scalableweb.model.DiffRecord;

@RunWith(SpringRunner.class)
@DataMongoTest
public class DiffRecordRepositoryTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private DiffRecordRepository repository;

	@Test
	public void saveLeftInput() throws Exception {
		// arrange
		DiffRecord sample = new DiffRecord("ID");
		sample.setLeftInput("SAMPLEFIRST");
		// act
		repository.save(sample);
		// assert
		DiffRecord result = mongoTemplate.findById("ID", DiffRecord.class);
		assertThat(result).isNotNull();
		assertThat(result.getLeftInput()).isEqualTo("SAMPLEFIRST");
	}

	@Test
	public void saveRightInput() throws Exception {
		// arrange
		DiffRecord sample = new DiffRecord("ID");
		sample.setLeftInput("SAMPLEFIRST");
		mongoTemplate.save(sample);
		// act
		sample.setRightInput("SAMPLESECOND");
		repository.save(sample);
		// assert
		DiffRecord result = mongoTemplate.findById("ID", DiffRecord.class);
		assertThat(result).isNotNull();
		assertThat(result.getLeftInput()).isEqualTo("SAMPLEFIRST");
		assertThat(result.getRightInput()).isEqualTo("SAMPLESECOND");
	}

	@Test
	public void saveDiffResult() throws Exception {
		// arrange
		DiffRecord sample = new DiffRecord("ID");
		sample.setLeftInput("SAMPLEFIRST");
		sample.setRightInput("SAMPLESECOND");
		mongoTemplate.save(sample);
		// act
		sample.setLeftData(new JSONArray("[\"L1\", \"L2\"]"));
		sample.setRightData(new JSONArray("[\"R1\", \"R2\"]"));
		sample.setResult("SAMPLERESULT");
		repository.save(sample);
		// assert
		DiffRecord result = mongoTemplate.findById("ID", DiffRecord.class);
		assertThat(result).isNotNull();
		assertThat(result.getLeftInput()).isEqualTo("SAMPLEFIRST");
		assertThat(result.getRightInput()).isEqualTo("SAMPLESECOND");
		assertThat(result.getLeftData()).isNotNull();
		assertThat(result.getLeftData().get(0)).isEqualTo("L1");
		assertThat(result.getLeftData().get(1)).isEqualTo("L2");
		assertThat(result.getRightData()).isNotNull();
		assertThat(result.getRightData().get(0)).isEqualTo("R1");
		assertThat(result.getRightData().get(1)).isEqualTo("R2");
		assertThat(result.getResult()).isEqualTo("SAMPLERESULT");
	}

	@Test
	public void retrieveDiffResult() throws Exception {
		// arrange
		DiffRecord sample = new DiffRecord("ID");
		sample.setLeftData(new JSONArray("[\"L1\", \"L2\"]"));
		sample.setRightData(new JSONArray("[\"R1\", \"R2\"]"));
		sample.setResult("SAMPLERESULT");
		mongoTemplate.save(sample);
		// act
		Optional<DiffRecord> optional = repository.findById("ID");
		// assert
		assertThat(optional.isPresent()).isTrue();
		assertThat(optional.get().getLeftData()).isNotNull();
		assertThat(optional.get().getLeftData().get(0)).isEqualTo("L1");
		assertThat(optional.get().getLeftData().get(1)).isEqualTo("L2");
		assertThat(optional.get().getRightData().get(0)).isEqualTo("R1");
		assertThat(optional.get().getRightData().get(1)).isEqualTo("R2");
		assertThat(optional.get().getResult()).isEqualTo("SAMPLERESULT");
	}

	@Test
	public void deleteDiffRecord() throws Exception {
		// arrange
		mongoTemplate.save(new DiffRecord("ID"));
		mongoTemplate.save(new DiffRecord("XX"));
		// act
		repository.deleteById("XX");
		// assert
		assertThat(repository.findById("ID")).isNotEmpty();
		assertThat(repository.findById("XX")).isEmpty();
	}

}
