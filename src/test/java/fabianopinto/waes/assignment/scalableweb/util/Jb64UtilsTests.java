package fabianopinto.waes.assignment.scalableweb.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import fabianopinto.waes.assignment.scalableweb.util.Jb64Utils;

public class Jb64UtilsTests {

	@Test
	public void splitLines_singleLine() throws Exception {
		String sample = "SINGLE LINE\n";
		List<String> expected = Arrays.asList("SINGLE LINE");
		assertThat(Jb64Utils.splitLines(sample)).isEqualTo(expected);
	}

	@Test
	public void splitLines_multipleLines() throws Exception {
		String sample = "FIRST LINE\nSECOND LINE\nTHIRD LINE\n";
		List<String> expected = Arrays.asList("FIRST LINE", "SECOND LINE", "THIRD LINE");
		assertThat(Jb64Utils.splitLines(sample)).isEqualTo(expected);
	}

	@Test
	public void splitLines_trailingNoise() throws Exception {
		String sample = "SINGLE LINE\nNOISE";
		List<String> expected = Arrays.asList("SINGLE LINE");
		assertThat(Jb64Utils.splitLines(sample)).isEqualTo(expected);
	}

	@Test
	public void splitLines_extraBlanks() throws Exception {
		String sample = " FIRST LINE \n SECOND LINE \n THIRD LINE \n ";
		List<String> expected = Arrays.asList("FIRST LINE", "SECOND LINE", "THIRD LINE");
		assertThat(Jb64Utils.splitLines(sample)).isEqualTo(expected);
	}

}
