package co.simplon.plantproject.services;

import java.util.Collection;

import co.simplon.plantproject.dtos.PlantCreateDto;
import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.dtos.PlantUpdateDto;

public interface PlantService {

    Collection<PlantItem> getAll();

    void create(PlantCreateDto inputs);

    void update(Long id, PlantUpdateDto inputs);

    // void delete(Long id);
}
