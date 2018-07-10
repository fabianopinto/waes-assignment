package fabianopinto.waes.assignment.scalableweb.service.jb64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import org.json.JSONArray;
import org.junit.Test;

import fabianopinto.waes.assignment.scalableweb.exceptions.DiffException;

public class Jb64ComparatorTests {

	@Test
	public void diff_equalDataFiles() throws Exception {
		JSONArray leftSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\"],\n" +
				"[3,\"Carol\"],\n" +
				"[4,\"John\"],\n" +
				"[5,\"Bonnie\"],\n" +
				"[6,\"\\\"Cool'e'ohhhh\\\\\\\",\"],\n" +
				"[7,\"Reginald is bald\"],\n" +
				"[8,\"Henry\"]]");
		JSONArray rightSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\"],\n" +
				"[3,\"Carol\"],\n" +
				"[4,\"John\"],\n" +
				"[5,\"Bonnie\"],\n" +
				"[6,\"\\\"Cool'e'ohhhh\\\\\\\",\"],\n" +
				"[7,\"Reginald is bald\"],\n" +
				"[8,\"Henry\"]]");
		assertThat(Jb64Comparator.diff(leftSample, rightSample)).contains("EQUAL");
	}

	@Test
	public void diff_differentDataSize() throws Exception {
		JSONArray leftSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\"],\n" +
				"[3,\"Carol\"]]\n");
		JSONArray rightSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\"]]\n");
		assertThat(Jb64Comparator.diff(leftSample, rightSample))
				.contains("DIFFERENT")
				.contains("SIZE");
	}

	@Test
	public void diff_differentDataStructure() throws Exception {
		JSONArray leftSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\"]]\n");
		JSONArray rightSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"{\"ID\":1,\"name\":\"Bob\"},\n" +
				"[2,\"Suzie\"]]\n");
		assertThat(Jb64Comparator.diff(leftSample, rightSample))
				.contains("DIFFERENT")
				.contains("STRUCTURE");
	}

	@Test
	public void diff_differentDataItem() throws Exception {
		JSONArray leftSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\"]]\n");
		JSONArray rightSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Robert\"],\n" +
				"[2,\"Suzie\"]]\n");
		assertThat(Jb64Comparator.diff(leftSample, rightSample))
				.contains("DIFFERENT")
				.contains("ITEM");
	}

	@Test
	public void diff_differentDataList() throws Exception {
		JSONArray leftSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\",[1,2,3]]]\n");
		JSONArray rightSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Robert\"],\n" +
				"[2,\"Suzie\",[4,5,6]]]\n");
		assertThat(Jb64Comparator.diff(leftSample, rightSample))
				.contains("DIFFERENT")
				.contains("ITEM");
	}

	@Test(expected = DiffException.class)
	public void diff_nullLeftData() throws Exception {
		JSONArray rightSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Robert\"],\n" +
				"[2,\"Suzie\"]]\n");
		Jb64Comparator.diff(null, rightSample);
		shouldHaveThrown(DiffException.class);
	}

	@Test(expected = DiffException.class)
	public void diff_nullRightData() throws Exception {
		JSONArray leftSample = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Robert\"],\n" +
				"[2,\"Suzie\"]]\n");
		Jb64Comparator.diff(leftSample, null);
		shouldHaveThrown(DiffException.class);
	}

}
