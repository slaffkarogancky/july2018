package golden.horde.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import golden.horde.domain.Animal;

public interface IAnimalService {

	@Transactional(readOnly = true)
	List<Animal> getAllAnimals(int page, int size, String substr);

	@Transactional(readOnly = true)
	Animal getAnimalById(Integer id);

	@Transactional
	Integer createAnimal(Animal animal);

	@Transactional
	void updateAnimal(Animal animal);

	@Transactional
	void deleteAnimal(Integer animalId);

}