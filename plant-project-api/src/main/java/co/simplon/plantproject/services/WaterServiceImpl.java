package co.simplon.plantproject.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.WaterOrSunValue;
import co.simplon.plantproject.repositories.WaterRepository;

@Service
@Transactional(readOnly = true)
public class WaterServiceImpl implements WaterService {

    public WaterServiceImpl(WaterRepository waters) {
	this.waters = waters;
    }

    private WaterRepository waters;

    @Override
    public Collection<WaterOrSunValue> getAll() {
	return waters
		.findAllProjectedByOrderByLogicalOrder();
    }

}
