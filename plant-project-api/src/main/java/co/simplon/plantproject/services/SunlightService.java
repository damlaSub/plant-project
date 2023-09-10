package co.simplon.plantproject.services;

import java.util.Collection;

import co.simplon.plantproject.dtos.HydrationOrSunlightValue;

public interface SunlightService {

    Collection<HydrationOrSunlightValue> getAll();
}
