package golden.horde.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import golden.horde.domain.Animal;
import golden.horde.repository.AnimalRepository;


@Service
public class AnimalService implements IAnimalService {

	@Autowired
	private AnimalRepository animalRepository;

	@Override	
	public List<Animal> getAllAnimals(int page, int size, String substr) {
		if (size > 0)
			return animalRepository.findAll(PageRequest.of(page, size, Direction.ASC, "animalName")).getContent();
		/*else if (!StringUtils.isNullOrEmpty(substr))
			return animalRepository.getAnimalsByName(substr);*/			
		else
			return animalRepository.findAll( new Sort(Sort.Direction.ASC, "animalName"));
	}

	@Override
	public Animal getAnimalById(Integer id) {
		return animalRepository.findById(id).orElse(null);
	}

	@Override
	public Integer createAnimal(Animal animal) {
		Animal created = animalRepository.saveAndFlush(animal);
		return created.getId();
	}

	@Override
	public void updateAnimal(Animal animal) {
		animalRepository.save(animal);
	}

	@Override
	public void deleteAnimal(Integer animalId) {
		animalRepository.deleteById(animalId);
	}


}
