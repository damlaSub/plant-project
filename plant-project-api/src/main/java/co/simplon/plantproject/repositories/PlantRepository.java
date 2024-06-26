package co.simplon.plantproject.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.plantproject.dtos.PlantDetail;
import co.simplon.plantproject.dtos.PlantForUpdate;
import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.entities.Plant;

public interface PlantRepository
	extends JpaRepository<Plant, Long> {

    Collection<PlantItem> findAllProjectedBy();

    PlantDetail findProjectedDetailById(Long id);

    PlantForUpdate findProjectedById(Long id);

	Boolean existsByCommonNameIgnoreCase(String commonName);
	
	Boolean existsByLatinNameIgnoreCase(String latinName);
	
	@Query(value = "SELECT nextval('plants_plant_code_seq')", nativeQuery = true)
    public Long getNextValMySequence();

}
