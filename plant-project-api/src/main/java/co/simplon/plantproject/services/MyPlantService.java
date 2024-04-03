package co.simplon.plantproject.services;

import java.util.Map;
import java.util.Set;

import co.simplon.plantproject.dtos.MyPlantAddDto;
import co.simplon.plantproject.dtos.MyPlantDetail;

public interface MyPlantService {

    Set<MyPlantDetail> getAll();

    void add(MyPlantAddDto inputs);

    void delete(Long plantId);

    boolean existsByPlantId(Long plantId);

    Map<Long, Boolean> getPlantStatus();

}
