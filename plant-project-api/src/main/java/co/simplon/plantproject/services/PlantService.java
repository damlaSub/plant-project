package co.simplon.plantproject.services;

import java.util.Collection;

import co.simplon.plantproject.dtos.PlantItem;

public interface PlantService {

    Collection<PlantItem> getAll();
}
