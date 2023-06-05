package co.simplon.plantproject.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.services.PlantService;

@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantService service;

    public PlantController(PlantService service) {
	this.service = service;
    }

    @GetMapping
    public Collection<PlantItem> getAll() {
	return service.getAll();
    }
}
