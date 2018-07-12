package golden.horde.service;

import java.util.List;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import golden.horde.domain.Animal;
import golden.horde.repository.AnimalRepository;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;

	@Transactional(readOnly = true)
	public List<Animal> getAllAnimals(int page, int size, String substr) {
		if (size > 0)
			return animalRepository.findAll(PageRequest.of(page, size, Direction.ASC, "animalName")).getContent();
		else if (!StringUtils.isNullOrEmpty(substr))
			return animalRepository.getAnimalsByName(substr);			
		else
			return animalRepository.findAll( new Sort(Sort.Direction.ASC, "animalName"));
	}

	@Transactional(readOnly = true)
	public Animal getAnimalById(Integer id) {
		return animalRepository.findById(id).orElse(null);
	}

	@Transactional
	public Integer createAnimal(Animal animal) {
		Animal created = animalRepository.saveAndFlush(animal);
		return created.getId();
	}

	@Transactional
	public void updateAnimal(Animal animal) {
		animalRepository.save(animal);
	}

	@Transactional
	public void deleteAnimal(Integer animalId) {
		animalRepository.deleteById(animalId);
	}
	
	
}
