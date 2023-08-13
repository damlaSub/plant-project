package co.simplon.plantproject.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.WaterOrSunValue;
import co.simplon.plantproject.services.WaterService;

@RestController
@RequestMapping("/waters")
public class WaterController {

    private final WaterService service;

    public WaterController(WaterService service) {
	this.service = service;
    }

    @GetMapping
    public Collection<WaterOrSunValue> getAll() {
	return service.getAll();
    }
}
