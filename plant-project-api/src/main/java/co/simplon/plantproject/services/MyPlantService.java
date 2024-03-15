package co.simplon.plantproject.services;

import java.util.Collection;

import org.apache.coyote.BadRequestException;

import co.simplon.plantproject.dtos.MyPlantAddDto;
import co.simplon.plantproject.dtos.MyPlantDetail;

public interface MyPlantService {

    Collection<MyPlantDetail> getAll();

    void add(MyPlantAddDto inputs)
	    throws BadRequestException;

    void delete(Long plantId) throws BadRequestException;

    boolean myPlant(Long plantId);

}
