package co.simplon.plantproject.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.plantproject.dtos.MyPlantDetail;
import co.simplon.plantproject.entities.MyPlant;

public interface MyPlantRepository
	extends JpaRepository<MyPlant, Long> {

    @Query(value = "SELECT p.id AS id, p.common_name AS commonName, p.latin_name AS latinName, "
	    + "p.description AS description, p.image AS image, p.hydration_id AS hydrationId, p.sunlight_id AS sunlightId "
	    + "FROM plants p "
	    + "JOIN account_plants ac ON p.id = ac.plant_id "
	    + "JOIN accounts a ON ac.account_id = a.id "
	    + "WHERE a.id = :accountId", nativeQuery = true)
    Collection<MyPlantDetail> findByAccountId(
	    @Param("accountId") Long accountId);
}
