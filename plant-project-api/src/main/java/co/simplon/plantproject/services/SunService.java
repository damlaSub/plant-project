package co.simplon.plantproject.services;

import java.util.Collection;

import co.simplon.plantproject.dtos.WaterOrSunValue;

public interface SunService {

    Collection<WaterOrSunValue> getAll();
}
