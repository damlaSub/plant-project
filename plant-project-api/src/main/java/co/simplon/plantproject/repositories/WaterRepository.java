package co.simplon.plantproject.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.dtos.WaterOrSunValue;
import co.simplon.plantproject.entities.Water;

public interface WaterRepository
	extends JpaRepository<Water, Long> {

    Collection<WaterOrSunValue> findAllProjectedByOrderByLogicalOrder();
}
