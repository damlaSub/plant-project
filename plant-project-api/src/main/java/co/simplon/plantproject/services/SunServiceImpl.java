package co.simplon.plantproject.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.WaterOrSunValue;
import co.simplon.plantproject.repositories.SunRepository;

@Service
@Transactional(readOnly = true)
public class SunServiceImpl implements SunService {
    private final SunRepository suns;

    public SunServiceImpl(SunRepository suns) {
	this.suns = suns;
    }

    @Override
    public Collection<WaterOrSunValue> getAll() {
	return suns.findAllProjectedByOrderByLogicalOrder();
    }

}
