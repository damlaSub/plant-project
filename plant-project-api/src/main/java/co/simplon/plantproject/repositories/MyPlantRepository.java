package co.simplon.plantproject.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.plantproject.dtos.MyPlantDetail;
import co.simplon.plantproject.entities.MyPlant;

public interface MyPlantRepository
	extends JpaRepository<MyPlant, Long> {

    
    @Query("SELECT p.id AS id, p.commonName AS commonName, p.latinName AS latinName, "
    	    + "p.description AS description, p.image AS image, "
    	    + "p.hydration.id AS hydrationId, p.sunlight.id AS sunlightId "
    	    + "FROM MyPlant mp "
    	    + "JOIN mp.plant p "
    	    + "JOIN mp.account a "
    	    + "WHERE a.id = :accountId")
    	Set<MyPlantDetail> findByAccountId(@Param("accountId") Long accountId);
 

    @Query("SELECT mp FROM MyPlant mp JOIN mp.plant p JOIN mp.account a WHERE p.id = :plantId AND a.id = :accountId")
    MyPlant findByAccountIdAndPlantId(
	    @Param("accountId") Long accountId,
	    @Param("plantId") Long plantId);
}
