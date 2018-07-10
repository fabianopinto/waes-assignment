package fabianopinto.waes.assignment.scalableweb.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class Md5UtilsTests {

	@Test
	public void data_simpleLine() throws Exception {
		String sample = "The quick brown fox jumps over the lazy dog";
		assertThat(Md5Utils.data(sample)).isEqualTo("The quick brown fox jumps over the lazy dog");
	}

	@Test
	public void data_hashedLine() throws Exception {
		String hashed = "The quick brown fox jumps over the lazy dog.9e107d9d372bb6826bd81d3542a419d6";
		assertThat(Md5Utils.data(hashed)).isEqualTo("The quick brown fox jumps over the lazy dog");
	}

	@Test
	public void check_simpleLine() throws Exception {
		String sample = "The quick brown fox jumps over the lazy dog";
		assertThat(Md5Utils.check(sample)).isTrue();
	}

	@Test
	public void check_validLine() throws Exception {
		String hashed = "The quick brown fox jumps over the lazy dog.9e107d9d372bb6826bd81d3542a419d6";
		assertThat(Md5Utils.check(hashed)).isTrue();
	}

	@Test
	public void check_invalidLine() throws Exception {
		String invalid = "The quick brown fox jumps over the lazy dog.11223344556677889900aabbccddeeff";
		assertThat(Md5Utils.check(invalid)).isFalse();
	}

	@Test
	public void check_validDataHash() throws Exception {
		String sample = "The quick brown fox jumps over the lazy dog";
		String validHash = "9e107d9d372bb6826bd81d3542a419d6";
		assertThat(Md5Utils.check(sample, validHash)).isTrue();
	}

	@Test
	public void check_invalidDataHash() throws Exception {
		String sample = "The quick brown fox jumps over the lazy dog";
		String invalidHash = "11223344556677889900aabbccddeeff";
		assertThat(Md5Utils.check(sample, invalidHash)).isFalse();
	}

	@Test
	public void md5_classicSample() throws Exception {
		byte[] result = Md5Utils.md5("The quick brown fox jumps over the lazy dog");
		byte[] expected = {
				(byte) 0x9e, (byte) 0x10, (byte) 0x7d, (byte) 0x9d,
				(byte) 0x37, (byte) 0x2b, (byte) 0xb6, (byte) 0x82,
				(byte) 0x6b, (byte) 0xd8, (byte) 0x1d, (byte) 0x35,
				(byte) 0x42, (byte) 0xa4, (byte) 0x19, (byte) 0xd6
		};
		assertThat(result).isEqualTo(expected);
	}

}
