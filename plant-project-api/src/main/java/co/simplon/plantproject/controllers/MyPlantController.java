package co.simplon.plantproject.controllers;

import java.util.Map;
import java.util.Set;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.MyPlantAddDto;
import co.simplon.plantproject.dtos.MyPlantDetail;
import co.simplon.plantproject.services.MyPlantService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/my-plants")
public class MyPlantController {

    private final MyPlantService service;

    public MyPlantController(MyPlantService service) {
	this.service = service;
    }

    @GetMapping
    public Set<MyPlantDetail> getAll() {
	return service.getAll();

    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void add(
	    @Valid @RequestBody MyPlantAddDto inputs)
	    throws BadRequestException,
	    AccountNotFoundException {
	service.add(inputs);
    }

    @DeleteMapping("/{plantId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long plantId)
	    throws BadRequestException {
	service.delete(plantId);
    }

    @GetMapping("/status")
    public Map<Long, Boolean> getPlantStatus() {
	return service.getPlantStatus();
    }

}
