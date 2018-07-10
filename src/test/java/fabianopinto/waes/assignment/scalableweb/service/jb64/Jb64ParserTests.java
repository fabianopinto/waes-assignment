package fabianopinto.waes.assignment.scalableweb.service.jb64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;

import org.json.JSONArray;
import org.junit.Test;

import fabianopinto.waes.assignment.scalableweb.exceptions.ParseException;

public class Jb64ParserTests {

	@Test
	public void parse_phpTools1NoHash() throws Exception {
		String sample =
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d\n" +
				"WzEsIkJvYiJd\n" +
				"WzIsIlN1emllIl0\n" +
				"WzMsIkNhcm9sIl0\n" +
				"WzQsIkpvaG4iXQ\n" +
				"WzUsIkJvbm5pZSJd\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ\n" +
				"WzgsIkhlbnJ5Il0\n";
		JSONArray expected = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\"],\n" +
				"[3,\"Carol\"],\n" +
				"[4,\"John\"],\n" +
				"[5,\"Bonnie\"],\n" +
				"[6,\"\\\"Cool'e'ohhhh\\\\\\\",\"],\n" +
				"[7,\"Reginald is bald\"],\n" +
				"[8,\"Henry\"]]");
		assertThat(Jb64Parser.parse(sample).toList()).isEqualTo(expected.toList());
	}

	@Test
	public void parse_phpTools1Hashed() throws Exception {
		String sample =
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d.d72a7e34d0a477ec0063bc8e8f3a094e\n" +
				"WzEsIkJvYiJd.cdac9777ff0b77ac706855fb61a7b480\n" +
				"WzIsIlN1emllIl0.afb2999052f5924c038d7b503226ef1e\n" +
				"WzMsIkNhcm9sIl0.345841e617e98a1c12f554acc507bbbc\n" +
				"WzQsIkpvaG4iXQ.02c185032333e52a10959c98ed9fc5f1\n" +
				"WzUsIkJvbm5pZSJd.f331c74c364699a5293a6e1229938dd8\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ.d2db968574ec7e29f6c8cd0f1491d39f\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ.8162f9f07c93d252e9bdeb1b54c22938\n" +
				"WzgsIkhlbnJ5Il0.65701dd0cb9a924642629add95dabd47\n";
		JSONArray expected = new JSONArray(
				"[[[\"ID\",\"integer\"],[\"name\",\"string\"]],\n" +
				"[1,\"Bob\"],\n" +
				"[2,\"Suzie\"],\n" +
				"[3,\"Carol\"],\n" +
				"[4,\"John\"],\n" +
				"[5,\"Bonnie\"],\n" +
				"[6,\"\\\"Cool'e'ohhhh\\\\\\\",\"],\n" +
				"[7,\"Reginald is bald\"],\n" +
				"[8,\"Henry\"]]");
		assertThat(Jb64Parser.parse(sample).toList()).isEqualTo(expected.toList());
	}

	@Test(expected = ParseException.class)
	public void parse_phpTools1InvalidData() throws Exception {
		String invalid =
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxx\n";
		Jb64Parser.parse(invalid);
		shouldHaveThrown(ParseException.class);
	}

	@Test(expected = ParseException.class)
	public void parse_phpTools1InvalidHash() throws Exception {
		String invalid =
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d.00000000000000000000000000000000\n" +
				"WzEsIkJvYiJd.00000000000000000000000000000000\n" +
				"WzIsIlN1emllIl0.00000000000000000000000000000000\n" +
				"WzMsIkNhcm9sIl0.00000000000000000000000000000000\n" +
				"WzQsIkpvaG4iXQ.00000000000000000000000000000000\n" +
				"WzUsIkJvbm5pZSJd.00000000000000000000000000000000\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ.00000000000000000000000000000000\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ.00000000000000000000000000000000\n" +
				"WzgsIkhlbnJ5Il0.00000000000000000000000000000000\n";
		Jb64Parser.parse(invalid);
		shouldHaveThrown(ParseException.class);
	}

	@Test
	public void parse_phpTools2NoHash() throws Exception {
		String sample =
				"W1siVXNlciBJRCIsImludGVnZXIiXSxbIkNyZWF0ZWQgT24iLCJkYXRlIl0sWyJOYW1lIiwic3RyaW5nIl1d\n" +
				"WzIyLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiUmVuYSJd\n" +
				"WzIzLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiSG9yYXRpbyJd\n" +
				"WzI0LCIyMDAwLTAxLTA0IDAyOjAwOjAwIiwiTGF3c29uIl0\n" +
				"WzI1LCIyMDAwLTAxLTA1IDAyOjAwOjAwIiwiTmljayJd\n" +
				"WzI2LCIyMDAwLTAxLTA3IDAyOjAwOjAwIiwiS2ltIl0\n" +
				"WzI3LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiKioqU3RlbGxhKioqIl0\n" +
				"WzI4LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiUGV0ZXIncyBzdGFydGluJyB0byB0ZWV0ZXIiXQ\n" +
				"WzI5LCIyMDAwLTAxLTEzIDAyOjAwOjAwIiwiQ2hpY2tlbiBBbGZyZWRvIl0\n";
		JSONArray expected = new JSONArray(
				"[[[\"User ID\",\"integer\"],[\"Created On\",\"date\"],[\"Name\",\"string\"]],\n" +
				"[22,\"2000-01-03 02:00:00\",\"Rena\"],\n" +
				"[23,\"2000-01-03 02:00:00\",\"Horatio\"],\n" +
				"[24,\"2000-01-04 02:00:00\",\"Lawson\"],\n" +
				"[25,\"2000-01-05 02:00:00\",\"Nick\"],\n" +
				"[26,\"2000-01-07 02:00:00\",\"Kim\"],\n" +
				"[27,\"2000-01-12 02:00:00\",\"***Stella***\"],\n" +
				"[28,\"2000-01-12 02:00:00\",\"Peter's startin' to teeter\"],\n" +
				"[29,\"2000-01-13 02:00:00\",\"Chicken Alfredo\"]]");
		assertThat(Jb64Parser.parse(sample).toList()).isEqualTo(expected.toList());
	}

	@Test
	public void parse_phpTools2Hashed() throws Exception {
		String sample =
				"W1siVXNlciBJRCIsImludGVnZXIiXSxbIkNyZWF0ZWQgT24iLCJkYXRlIl0sWyJOYW1lIiwic3RyaW5nIl1d.97fb93b7db2a02b0d3625f76e69c52ab\n" +
				"WzIyLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiUmVuYSJd.21a39f5b7b4d498f770592b462b67e75\n" +
				"WzIzLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiSG9yYXRpbyJd.d9250a4e36c82b982ce2e99ecafac921\n" +
				"WzI0LCIyMDAwLTAxLTA0IDAyOjAwOjAwIiwiTGF3c29uIl0.caf36dac8bb7423ad68c20cfa28d0909\n" +
				"WzI1LCIyMDAwLTAxLTA1IDAyOjAwOjAwIiwiTmljayJd.448f6d6e006833eea25ed959d0c05bb3\n" +
				"WzI2LCIyMDAwLTAxLTA3IDAyOjAwOjAwIiwiS2ltIl0.16036c8c79bc85ad9c5d6b6614129ffc\n" +
				"WzI3LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiKioqU3RlbGxhKioqIl0.9e537baa4f699ebcf4d3cf5a7df1b2c2\n" +
				"WzI4LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiUGV0ZXIncyBzdGFydGluJyB0byB0ZWV0ZXIiXQ.67401564b62424352e347e52c2218864\n" +
				"WzI5LCIyMDAwLTAxLTEzIDAyOjAwOjAwIiwiQ2hpY2tlbiBBbGZyZWRvIl0.c78edeb13bfb4a572cb3d0e1edcf6e27\n";
		JSONArray expected = new JSONArray(
				"[[[\"User ID\",\"integer\"],[\"Created On\",\"date\"],[\"Name\",\"string\"]],\n" +
				"[22,\"2000-01-03 02:00:00\",\"Rena\"],\n" +
				"[23,\"2000-01-03 02:00:00\",\"Horatio\"],\n" +
				"[24,\"2000-01-04 02:00:00\",\"Lawson\"],\n" +
				"[25,\"2000-01-05 02:00:00\",\"Nick\"],\n" +
				"[26,\"2000-01-07 02:00:00\",\"Kim\"],\n" +
				"[27,\"2000-01-12 02:00:00\",\"***Stella***\"],\n" +
				"[28,\"2000-01-12 02:00:00\",\"Peter's startin' to teeter\"],\n" +
				"[29,\"2000-01-13 02:00:00\",\"Chicken Alfredo\"]]");
		assertThat(Jb64Parser.parse(sample).toList()).isEqualTo(expected.toList());
	}

}
