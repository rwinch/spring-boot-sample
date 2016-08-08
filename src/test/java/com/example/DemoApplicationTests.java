package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DemoApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	public void homeWhenUnauthenticatedThenRequiresAuthentication() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser
	public void homeWhenAuthenticatedThenOk() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void homeWhenAuthenticatedThenCustomHeader() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(header().string("Custom", "Header"));
	}

}
