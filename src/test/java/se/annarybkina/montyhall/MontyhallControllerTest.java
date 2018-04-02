package se.annarybkina.montyhall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MontyhallControllerTest {

	@Autowired
	private MockMvc mockMvc;
 
	@Test
	public void shouldReceiveRequestDataAndReturnOk() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode userJson = objectMapper.createObjectNode();
		userJson.put("iterations", "100");
		userJson.put("alwaysSwitch", true);
	
		this.mockMvc.perform(post("/data").contentType(
				MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(userJson)))
				.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
	}
}
