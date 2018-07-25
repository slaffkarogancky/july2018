package golden.horde.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import golden.horde.service.MockAnimalService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // instead of @ContextConfiguration
//@ActiveProfiles("test")
public class ZooControllerTest {

	@Autowired
	TestRestTemplate restTemplate; // it knows about random port number

	@LocalServerPort
	private int port;

	private static final Logger logger = LoggerFactory.getLogger(MockAnimalService.class);

	@Test
	public void testPing() {
		ResponseEntity<String> respEntity = restTemplate.getForEntity("/zoo/api/v1/ping", String.class);
		assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		logger.info(respEntity.getBody());
	}

	@Test
	public void testTwo() {
		ResponseEntity<String> respEntity = restTemplate.getForEntity("/zoo/api/v1/animal", String.class);
		assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		logger.info(respEntity.getBody());
	}

	@Test
	public void testThree() {
		String content = restTemplate.getForObject("http://localhost:" + port + "/zoo/api/v1/animal", String.class);
		logger.info("Зоопарк: " + content);
		
	}

}
