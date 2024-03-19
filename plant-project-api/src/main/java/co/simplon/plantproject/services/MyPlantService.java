package co.simplon.plantproject.services;

import java.util.Set;

import org.apache.coyote.BadRequestException;

import co.simplon.plantproject.dtos.MyPlantAddDto;
import co.simplon.plantproject.dtos.MyPlantDetail;

public interface MyPlantService {

    Set<MyPlantDetail> getAll();

    void add(MyPlantAddDto inputs)
	    throws BadRequestException;

    void delete(Long plantId) throws BadRequestException;

    boolean exists(Long plantId);

}
