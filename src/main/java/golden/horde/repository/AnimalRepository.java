package golden.horde.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import golden.horde.domain.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

	
}
