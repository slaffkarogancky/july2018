package golden.horde.controller;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import golden.horde.domain.Animal;
import golden.horde.exception.EntityNotFoundedException;
import golden.horde.service.AnimalService;

@RestController
@RequestMapping("/zoo/api/v1")
public class ZooController {

	@Autowired
	private AnimalService animalService;

	// http://localhost:2018/zoo/api/v1/ping
	@GetMapping(value = "/ping")
	public ResponseEntity<String> ping(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		return new ResponseEntity<>(LocalDateTime.now() + " PONG!!!", HttpStatus.OK);
	}

	// http://localhost:2018/zoo/api/v1/animal?page=0&size=3&name=ос
	@GetMapping(value = "/animal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Iterable<Animal>> getAllAnimals(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "0") int size) {
		Iterable<Animal> animals = animalService.getAllAnimals(page, size, name);
		return new ResponseEntity<>(animals, HttpStatus.OK);
	}

	// http://localhost:2018/zoo/api/v1/animal/2
	@GetMapping(value = "/animal/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Animal> getAnimal(@PathVariable int id) {
		Animal animal = animalService.getAnimalById(id);
		if (animal == null) {
			throw new EntityNotFoundedException(String.format("\"Animal with id %s not found", id));
		}
		return new ResponseEntity<>(animal, HttpStatus.OK);
	}

	@PostMapping(value = "/animal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> createAnimal(@Valid @RequestBody Animal animal) {
		int newEntityId = animalService.createAnimal(animal);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEntityId).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/animal/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateAnimal(@Valid @RequestBody Animal animal, @PathVariable int id) {
		checkAnimalIdExists(id);
		animal.setId(id);
		animalService.updateAnimal(animal);
	}

	@DeleteMapping(value = "/animal/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAnimal(@PathVariable int id) {
		checkAnimalIdExists(id);
		animalService.deleteAnimal(id);
	}
	
	private void checkAnimalIdExists(int id) {
		if (animalService.getAnimalById(id) == null) {
			throw new EntityNotFoundedException(String.format("\"Animal with id %s not found", id));
		}
	}

}
