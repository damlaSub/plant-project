package co.simplon.plantproject.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.HydrationOrSunlightValue;
import co.simplon.plantproject.repositories.SunlightRepository;

@Service
@Transactional(readOnly = true)
public class SunlightServiceImpl
	implements SunlightService {
    private final SunlightRepository sunlights;

    public SunlightServiceImpl(
	    SunlightRepository sunlights) {
	this.sunlights = sunlights;
    }

    @Override
    public Collection<HydrationOrSunlightValue> getAll() {
	return sunlights
		.findAllProjectedByOrderByLogicalOrder();
    }

}
