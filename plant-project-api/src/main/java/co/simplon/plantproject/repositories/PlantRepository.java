package co.simplon.plantproject.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.dtos.PlantDetail;
import co.simplon.plantproject.dtos.PlantForUpdate;
import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.entities.Plant;

public interface PlantRepository
	extends JpaRepository<Plant, Long> {

    Collection<PlantItem> findAllProjectedBy();

    PlantDetail findProjectedDetailById(Long id);

    PlantForUpdate findProjectedById(Long id);

}
