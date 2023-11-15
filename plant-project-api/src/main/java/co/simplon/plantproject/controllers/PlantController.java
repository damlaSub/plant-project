package co.simplon.plantproject.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.PlantCreateDto;
import co.simplon.plantproject.dtos.PlantForUpdate;
import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.dtos.PlantUpdateDto;
import co.simplon.plantproject.services.PlantService;

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
	    @ModelAttribute @Valid PlantCreateDto inputs) {
	service.create(inputs);
    }

    @GetMapping
    public Collection<PlantItem> getAll() {
	return service.getAll();
    }

    @GetMapping("/{id}/for-update")
    public PlantForUpdate getForUpdate(
	    @PathVariable("id") Long id) {
	return service.getForUpdate(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id,
	    @ModelAttribute @Valid PlantUpdateDto inputs) {
	service.update(id, inputs);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
	service.delete(id);
    }

}
