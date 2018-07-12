package golden.horde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import golden.horde.domain.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

	@Query("SELECT a FROM Animal a WHERE LOWER(a.animalName) LIKE LOWER(CONCAT('%',:substr, '%')) ORDER BY a.animalName ASC")
	List<Animal> getAnimalsByName(@Param("substr") String substr);
	
}
