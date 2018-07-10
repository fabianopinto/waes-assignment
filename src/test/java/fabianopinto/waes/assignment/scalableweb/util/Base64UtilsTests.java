package fabianopinto.waes.assignment.scalableweb.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class Base64UtilsTests {

	@Test
	public void decode_simpleSample() throws Exception {
		String sample = "VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZw==";
		assertThat(Base64Utils.decode(sample)).isEqualTo("The quick brown fox jumps over the lazy dog");
	}

}
