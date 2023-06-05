package co.simplon.plantproject.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.repositories.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {

    private PlantRepository plants;

    public PlantServiceImpl(PlantRepository plantes) {
	this.plants = plantes;
    }

    @Override
    public Collection<PlantItem> getAll() {
	return plants.findAllProjectedBy();
    }

}
