package co.simplon.plantproject.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.dtos.HydrationOrSunlightValue;
import co.simplon.plantproject.entities.Hydration;

public interface HydrationRepository
	extends JpaRepository<Hydration, Long> {

    Collection<HydrationOrSunlightValue> findAllProjectedByOrderByLogicalOrder();
}
