package co.simplon.plantproject.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.HydrationOrSunlightValue;
import co.simplon.plantproject.repositories.HydrationRepository;

@Service
@Transactional(readOnly = true)
public class HydrationServiceImpl
	implements HydrationService {

    public HydrationServiceImpl(
	    HydrationRepository hydrations) {
	this.hydrations = hydrations;
    }

    private HydrationRepository hydrations;

    @Override
    public Collection<HydrationOrSunlightValue> getAll() {
	return hydrations
		.findAllProjectedByOrderByLogicalOrder();
    }

}
