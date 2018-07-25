package golden.horde.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ZooTooControllerTest {

	@Autowired
	private MockMvc mvc;

	//@Test
	public void test() throws Exception {
		this.mvc.perform(get("/zoo/api/v1/animal")
				 .accept(MediaType.APPLICATION_JSON))
				 .andExpect(status().isOk())
				 .andExpect(jsonPath("$[0].id", is(1)))
				 .andExpect(jsonPath("$[0].animalName", is("Козел")));
	}
	
	@Test
	public void testOne() throws Exception {
		String content = "{\"animalName\": \"алKлигатор уссурийская\"}";
		MvcResult result = this.mvc.perform(post("/zoo/api/v1/animal")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andReturn();
		System.out.println("------------ FUCK " + result.getResponse().getHeader("location"));
	}

}
