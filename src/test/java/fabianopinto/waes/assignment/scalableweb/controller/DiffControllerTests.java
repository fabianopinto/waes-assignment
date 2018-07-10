package fabianopinto.waes.assignment.scalableweb.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import fabianopinto.waes.assignment.scalableweb.model.DiffRecord;
import fabianopinto.waes.assignment.scalableweb.service.DiffService;

@RunWith(SpringRunner.class)
@WebMvcTest(DiffController.class)
public class DiffControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DiffService service;

	@Test
	public void leftPosting_callsService() throws Exception {
		this.mockMvc.perform(post("/v1/diff/ID/left").content("LEFTSAMPLE"))
				.andDo(print())
				.andExpect(status().isOk());
		verify(service, times(1)).inputLeft("ID", "LEFTSAMPLE");
	}

	@Test
	public void rightPosting_callsService() throws Exception {
		this.mockMvc.perform(post("/v1/diff/ID/right").content("RIGHTSAMPLE"))
				.andDo(print())
				.andExpect(status().isOk());
		verify(service, times(1)).inputRight("ID", "RIGHTSAMPLE");
	}

	@Test
	public void diffResult_callsService() throws Exception {
		DiffRecord sample = new DiffRecord("ID");
		sample.setResult("SAMPLERESULT");
		when(service.diffResult("ID")).thenReturn(sample);
		this.mockMvc.perform(get("/v1/diff/ID"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("ok").value(true))
				.andExpect(jsonPath("message").value("SAMPLERESULT"));
	}

}
