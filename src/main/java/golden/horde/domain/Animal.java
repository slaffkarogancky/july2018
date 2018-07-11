package golden.horde.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zoo_animal")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Animal {

	@Id
	@Column(name="animal_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generate_animal_id")	
	@SequenceGenerator(name="generate_animal_id", sequenceName = "ZOO_GEN_ANIMAL_ID", allocationSize=1)
	private Integer id;
	
	@Column(name="animal_name")
	private String animalName;
}
