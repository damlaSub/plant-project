package co.simplon.plantproject.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	

//	@Query("SELECT p FROM Plant p WHERE LOWER(p.commonName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(p.latinName) LIKE LOWER(CONCAT('%', :searchText, '%'))")
//	    Collection<PlantItem> findPlantsBySearchText(
//		    @Param("searchText") String searchText);
	@Query("SELECT p.id AS id, p.commonName AS commonName, p.latinName AS latinName, "
		    + "p.description AS description, p.image AS image, "
		    + "p.hydration.id AS hydrationId, p.sunlight.id AS sunlightId "
		    + "FROM Plant p WHERE LOWER(p.commonName) LIKE LOWER(CONCAT('%', :searchText, '%')) OR LOWER(p.latinName) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    Collection<PlantItem> findPlantsBySearchText(
	    @Param("searchText") String searchText);

	
}
