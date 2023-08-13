package co.simplon.plantproject.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.dtos.WaterOrSunValue;
import co.simplon.plantproject.entities.Sun;

public interface SunRepository
	extends JpaRepository<Sun, Long> {

    Collection<WaterOrSunValue> findAllProjectedByOrderByLogicalOrder();

}
