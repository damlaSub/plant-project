package co.simplon.plantproject.controllers;

import java.util.Collection;

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

    @GetMapping("user/{id}")
    public Collection<MyPlantDetail> getAll(
	    @PathVariable Long id) {
	return service.getAll(id);

    }

    @PostMapping("user/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void add(
	    @Valid @RequestBody MyPlantAddDto inputs) {
	service.add(inputs);
    }

    @DeleteMapping("{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
	service.delete(id);
    }
}
