package co.simplon.plantproject.services;

import java.util.Map;
import java.util.Set;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.coyote.BadRequestException;

import co.simplon.plantproject.dtos.MyPlantAddDto;
import co.simplon.plantproject.dtos.MyPlantDetail;

public interface MyPlantService {

    Set<MyPlantDetail> getAll();

    void add(MyPlantAddDto inputs)
	    throws BadRequestException,
	    AccountNotFoundException;

    void delete(Long plantId);

    boolean existsByPlantId(Long plantId);

    Map<Long, Boolean> getPlantStatus();

}
