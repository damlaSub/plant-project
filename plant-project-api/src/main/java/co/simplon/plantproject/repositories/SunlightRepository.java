package co.simplon.plantproject.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.dtos.HydrationOrSunlightValue;
import co.simplon.plantproject.entities.Sunlight;

public interface SunlightRepository
	extends JpaRepository<Sunlight, Long> {

    Collection<HydrationOrSunlightValue> findAllProjectedByOrderByLogicalOrder();

}
