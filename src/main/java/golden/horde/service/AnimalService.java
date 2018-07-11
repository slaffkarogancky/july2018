package golden.horde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import golden.horde.domain.Animal;
import golden.horde.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;

	@Transactional(readOnly = true)
	public List<Animal> getAllAnimals() {
			return animalRepository.findAll();
	}
}
