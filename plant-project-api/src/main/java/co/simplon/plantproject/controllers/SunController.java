package co.simplon.plantproject.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.WaterOrSunValue;
import co.simplon.plantproject.services.SunService;

@RestController
@RequestMapping("/suns")
public class SunController {

    private final SunService service;

    public SunController(SunService service) {

	this.service = service;
    }

    @GetMapping
    public Collection<WaterOrSunValue> getAll() {
	return service.getAll();
    }

}
