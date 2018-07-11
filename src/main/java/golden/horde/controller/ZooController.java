package golden.horde.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import golden.horde.domain.Animal;
import golden.horde.service.AnimalService;

@RestController
@RequestMapping("/zoo/api/v1")
public class ZooController {

	@Autowired
	private AnimalService animalService;
	
	// http://localhost:2018/zoo/api/v1/ping
	@GetMapping(value = "/ping")
	public ResponseEntity<String> ping() {
		return new ResponseEntity<>(LocalDateTime.now() + " PONG!!!", HttpStatus.OK);
	}

	// http://localhost:2018/zoo/api/v1/animal
	@GetMapping(value = "/animal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Iterable<Animal>> getAllAnimals() {
		Iterable<Animal> animals = animalService.getAllAnimals();
		return new ResponseEntity<>(animals, HttpStatus.OK);
	}

}
