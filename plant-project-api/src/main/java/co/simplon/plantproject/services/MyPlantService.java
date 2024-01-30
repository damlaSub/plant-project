package co.simplon.plantproject.services;

import java.util.Collection;

import co.simplon.plantproject.dtos.MyPlantAddDto;
import co.simplon.plantproject.dtos.MyPlantDetail;

public interface MyPlantService {

    Collection<MyPlantDetail> getAll(Long id);

    void add(MyPlantAddDto inputs);

    void delete(Long id);

}
