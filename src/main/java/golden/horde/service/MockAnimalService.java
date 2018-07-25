package golden.horde.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import golden.horde.domain.Animal;

@Service
@Profile("test")
public class MockAnimalService implements IAnimalService {

	
	private List<Animal> animals = new ArrayList<>();

	{
		animals.add(new Animal(1, "Кенгуру"));
		animals.add(new Animal(2, "Тигра"));
		animals.add(new Animal(3, "Винни-Пух"));
		animals.add(new Animal(4, "Пятачок"));
		animals.add(new Animal(5, "Кролик"));
		animals.add(new Animal(6, "Сова"));
		animals.add(new Animal(7, "Ослик Иа-Иа"));
	}

	@Override
	public List<Animal> getAllAnimals(int page, int size, String substr) {
		return animals;
	}

	@Override
	public Animal getAnimalById(Integer id) {
		return animals.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
	}

	@Override
	public Integer createAnimal(Animal animal) {
		Integer id = LocalTime.now().getHour() * 3600 + LocalTime.now().getMinute() * 60 + LocalTime.now().getSecond();
		animals.add(new Animal(id, animal.getAnimalName()));
		return id;
	}

	@Override
	public void updateAnimal(Animal animal) {
		animals.stream().filter((a -> a.getId() == animal.getId())).findFirst()
				.ifPresent(a -> a.setAnimalName(animal.getAnimalName()));

	}

	@Override
	public void deleteAnimal(Integer animalId) {
		animals.removeIf(a -> a.getId() == animalId);
	}

}
