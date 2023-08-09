package co.simplon.plantproject.controllers;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.PlantCreateDto;
import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.services.PlantService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantService service;

    public PlantController(PlantService service) {
	this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(
	    @Valid @RequestBody PlantCreateDto inputs) {
	service.create(inputs);
    }

    @GetMapping
    public Collection<PlantItem> getAll() {
	return service.getAll();
    }
}
