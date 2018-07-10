package fabianopinto.waes.assignment.scalableweb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import fabianopinto.waes.assignment.scalableweb.model.DiffResult;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainApplicationIT {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void leftPosting_phpTools1NoHash() throws Exception {
		String url = "http://localhost:" + port + "/v1/diff/ID/left";
		HttpEntity<String> request = new HttpEntity<String>(
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d\n" +
				"WzEsIkJvYiJd\n" +
				"WzIsIlN1emllIl0\n" +
				"WzMsIkNhcm9sIl0\n" +
				"WzQsIkpvaG4iXQ\n" +
				"WzUsIkJvbm5pZSJd\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ\n" +
				"WzgsIkhlbnJ5Il0\n");
		DiffResult result = restTemplate.postForObject(url, request, DiffResult.class);
		assertThat(result).isNotNull();
		assertThat(result.isOk()).isTrue();
		assertThat(result.getMessage()).containsIgnoringCase("SUCCESS");
	}

	@Test
	public void leftPosting_phpTools1Hashed() throws Exception {
		String url = "http://localhost:" + port + "/v1/diff/ID/left";
		HttpEntity<String> request = new HttpEntity<String>(
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d.d72a7e34d0a477ec0063bc8e8f3a094e\n" +
				"WzEsIkJvYiJd.cdac9777ff0b77ac706855fb61a7b480\n" +
				"WzIsIlN1emllIl0.afb2999052f5924c038d7b503226ef1e\n" +
				"WzMsIkNhcm9sIl0.345841e617e98a1c12f554acc507bbbc\n" +
				"WzQsIkpvaG4iXQ.02c185032333e52a10959c98ed9fc5f1\n" +
				"WzUsIkJvbm5pZSJd.f331c74c364699a5293a6e1229938dd8\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ.d2db968574ec7e29f6c8cd0f1491d39f\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ.8162f9f07c93d252e9bdeb1b54c22938\n" +
				"WzgsIkhlbnJ5Il0.65701dd0cb9a924642629add95dabd47\n");
		DiffResult result = restTemplate.postForObject(url, request, DiffResult.class);
		assertThat(result).isNotNull();
		assertThat(result.isOk()).isTrue();
		assertThat(result.getMessage()).containsIgnoringCase("SUCCESS");
	}

	@Test
	public void leftPosting_phpTools1InvalidData() throws Exception {
		String url = "http://localhost:" + port + "/v1/diff/ID/left";
		HttpEntity<String> request = new HttpEntity<String>(
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" +
				"xxxxxxxxxxxxxxx\n");
		DiffResult result = restTemplate.postForObject(url, request, DiffResult.class);
		assertThat(result).isNotNull();
		assertThat(result.isOk()).isFalse();
		assertThat(result.getMessage()).containsIgnoringCase("ERROR");
	}

	@Test
	public void leftPosting_phpToolsInvalid1Hash() throws Exception {
		String url = "http://localhost:" + port + "/v1/diff/ID/left";
		HttpEntity<String> request = new HttpEntity<String>(
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d.00000000000000000000000000000000\n" +
				"WzEsIkJvYiJd.00000000000000000000000000000000\n" +
				"WzIsIlN1emllIl0.00000000000000000000000000000000\n" +
				"WzMsIkNhcm9sIl0.00000000000000000000000000000000\n" +
				"WzQsIkpvaG4iXQ.00000000000000000000000000000000\n" +
				"WzUsIkJvbm5pZSJd.00000000000000000000000000000000\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ.00000000000000000000000000000000\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ.00000000000000000000000000000000\n" +
				"WzgsIkhlbnJ5Il0.00000000000000000000000000000000\n");
		DiffResult result = restTemplate.postForObject(url, request, DiffResult.class);
		assertThat(result).isNotNull();
		assertThat(result.isOk()).isFalse();
		assertThat(result.getMessage()).containsIgnoringCase("CORRUPT");
	}

	@Test
	public void rightPosting_phpTools2NoHash() throws Exception {
		String url = "http://localhost:" + port + "/v1/diff/ID/right";
		HttpEntity<String> request = new HttpEntity<String>(
				"W1siVXNlciBJRCIsImludGVnZXIiXSxbIkNyZWF0ZWQgT24iLCJkYXRlIl0sWyJOYW1lIiwic3RyaW5nIl1d\n" +
				"WzIyLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiUmVuYSJd\n" +
				"WzIzLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiSG9yYXRpbyJd\n" +
				"WzI0LCIyMDAwLTAxLTA0IDAyOjAwOjAwIiwiTGF3c29uIl0\n" +
				"WzI1LCIyMDAwLTAxLTA1IDAyOjAwOjAwIiwiTmljayJd\n" +
				"WzI2LCIyMDAwLTAxLTA3IDAyOjAwOjAwIiwiS2ltIl0\n" +
				"WzI3LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiKioqU3RlbGxhKioqIl0\n" +
				"WzI4LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiUGV0ZXIncyBzdGFydGluJyB0byB0ZWV0ZXIiXQ\n" +
				"WzI5LCIyMDAwLTAxLTEzIDAyOjAwOjAwIiwiQ2hpY2tlbiBBbGZyZWRvIl0\n");
		DiffResult result = restTemplate.postForObject(url, request, DiffResult.class);
		assertThat(result).isNotNull();
		assertThat(result.isOk()).isTrue();
		assertThat(result.getMessage()).containsIgnoringCase("SUCCESS");
	}

	@Test
	public void rightPosting_phpTools2Hashed() throws Exception {
		String url = "http://localhost:" + port + "/v1/diff/ID/right";
		HttpEntity<String> request = new HttpEntity<String>(
				"W1siVXNlciBJRCIsImludGVnZXIiXSxbIkNyZWF0ZWQgT24iLCJkYXRlIl0sWyJOYW1lIiwic3RyaW5nIl1d.97fb93b7db2a02b0d3625f76e69c52ab\n" +
				"WzIyLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiUmVuYSJd.21a39f5b7b4d498f770592b462b67e75\n" +
				"WzIzLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiSG9yYXRpbyJd.d9250a4e36c82b982ce2e99ecafac921\n" +
				"WzI0LCIyMDAwLTAxLTA0IDAyOjAwOjAwIiwiTGF3c29uIl0.caf36dac8bb7423ad68c20cfa28d0909\n" +
				"WzI1LCIyMDAwLTAxLTA1IDAyOjAwOjAwIiwiTmljayJd.448f6d6e006833eea25ed959d0c05bb3\n" +
				"WzI2LCIyMDAwLTAxLTA3IDAyOjAwOjAwIiwiS2ltIl0.16036c8c79bc85ad9c5d6b6614129ffc\n" +
				"WzI3LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiKioqU3RlbGxhKioqIl0.9e537baa4f699ebcf4d3cf5a7df1b2c2\n" +
				"WzI4LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiUGV0ZXIncyBzdGFydGluJyB0byB0ZWV0ZXIiXQ.67401564b62424352e347e52c2218864\n" +
				"WzI5LCIyMDAwLTAxLTEzIDAyOjAwOjAwIiwiQ2hpY2tlbiBBbGZyZWRvIl0.c78edeb13bfb4a572cb3d0e1edcf6e27\n");
		DiffResult result = restTemplate.postForObject(url, request, DiffResult.class);
		assertThat(result).isNotNull();
		assertThat(result.isOk()).isTrue();
		assertThat(result.getMessage()).containsIgnoringCase("SUCCESS");
	}

	@Test
	public void diffResult_phpTools1EqualFiles() throws Exception {
		String url = "http://localhost:" + port + "/v1/diff/ID";
		HttpEntity<String> request;
		request = new HttpEntity<String>(
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d\n" +
				"WzEsIkJvYiJd\n" +
				"WzIsIlN1emllIl0\n" +
				"WzMsIkNhcm9sIl0\n" +
				"WzQsIkpvaG4iXQ\n" +
				"WzUsIkJvbm5pZSJd\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ\n" +
				"WzgsIkhlbnJ5Il0\n");
		restTemplate.postForObject(url + "/left", request, DiffResult.class);
		request = new HttpEntity<String>(
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d.d72a7e34d0a477ec0063bc8e8f3a094e\n" +
				"WzEsIkJvYiJd.cdac9777ff0b77ac706855fb61a7b480\n" +
				"WzIsIlN1emllIl0.afb2999052f5924c038d7b503226ef1e\n" +
				"WzMsIkNhcm9sIl0.345841e617e98a1c12f554acc507bbbc\n" +
				"WzQsIkpvaG4iXQ.02c185032333e52a10959c98ed9fc5f1\n" +
				"WzUsIkJvbm5pZSJd.f331c74c364699a5293a6e1229938dd8\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ.d2db968574ec7e29f6c8cd0f1491d39f\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ.8162f9f07c93d252e9bdeb1b54c22938\n" +
				"WzgsIkhlbnJ5Il0.65701dd0cb9a924642629add95dabd47\n");
		restTemplate.postForObject(url + "/right", request, DiffResult.class);
		DiffResult result = restTemplate.getForObject(url, DiffResult.class);
		assertThat(result).isNotNull();
		assertThat(result.isOk()).isTrue();
		assertThat(result.getMessage()).containsIgnoringCase("EQUAL");
	}

	@Test
	public void diffResult_phpTools1And2Files() throws Exception {
		String url = "http://localhost:" + port + "/v1/diff/ID";
		HttpEntity<String> request;
		request = new HttpEntity<String>(
				"W1siSUQiLCJpbnRlZ2VyIl0sWyJuYW1lIiwic3RyaW5nIl1d.d72a7e34d0a477ec0063bc8e8f3a094e\n" +
				"WzEsIkJvYiJd.cdac9777ff0b77ac706855fb61a7b480\n" +
				"WzIsIlN1emllIl0.afb2999052f5924c038d7b503226ef1e\n" +
				"WzMsIkNhcm9sIl0.345841e617e98a1c12f554acc507bbbc\n" +
				"WzQsIkpvaG4iXQ.02c185032333e52a10959c98ed9fc5f1\n" +
				"WzUsIkJvbm5pZSJd.f331c74c364699a5293a6e1229938dd8\n" +
				"WzYsIlwiQ29vbCdlJ29oaGhoXFxcIiwiXQ.d2db968574ec7e29f6c8cd0f1491d39f\n" +
				"WzcsIlJlZ2luYWxkIGlzIGJhbGQiXQ.8162f9f07c93d252e9bdeb1b54c22938\n" +
				"WzgsIkhlbnJ5Il0.65701dd0cb9a924642629add95dabd47\n");
		restTemplate.postForObject(url + "/left", request, DiffResult.class);
		request = new HttpEntity<String>(
				"W1siVXNlciBJRCIsImludGVnZXIiXSxbIkNyZWF0ZWQgT24iLCJkYXRlIl0sWyJOYW1lIiwic3RyaW5nIl1d.97fb93b7db2a02b0d3625f76e69c52ab\n" +
				"WzIyLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiUmVuYSJd.21a39f5b7b4d498f770592b462b67e75\n" +
				"WzIzLCIyMDAwLTAxLTAzIDAyOjAwOjAwIiwiSG9yYXRpbyJd.d9250a4e36c82b982ce2e99ecafac921\n" +
				"WzI0LCIyMDAwLTAxLTA0IDAyOjAwOjAwIiwiTGF3c29uIl0.caf36dac8bb7423ad68c20cfa28d0909\n" +
				"WzI1LCIyMDAwLTAxLTA1IDAyOjAwOjAwIiwiTmljayJd.448f6d6e006833eea25ed959d0c05bb3\n" +
				"WzI2LCIyMDAwLTAxLTA3IDAyOjAwOjAwIiwiS2ltIl0.16036c8c79bc85ad9c5d6b6614129ffc\n" +
				"WzI3LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiKioqU3RlbGxhKioqIl0.9e537baa4f699ebcf4d3cf5a7df1b2c2\n" +
				"WzI4LCIyMDAwLTAxLTEyIDAyOjAwOjAwIiwiUGV0ZXIncyBzdGFydGluJyB0byB0ZWV0ZXIiXQ.67401564b62424352e347e52c2218864\n" +
				"WzI5LCIyMDAwLTAxLTEzIDAyOjAwOjAwIiwiQ2hpY2tlbiBBbGZyZWRvIl0.c78edeb13bfb4a572cb3d0e1edcf6e27\n");
		restTemplate.postForObject(url + "/right", request, DiffResult.class);
		DiffResult result = restTemplate.getForObject(url, DiffResult.class);
		assertThat(result).isNotNull();
		assertThat(result.isOk()).isTrue();
		assertThat(result.getMessage()).containsIgnoringCase("DIFF");
	}

}
