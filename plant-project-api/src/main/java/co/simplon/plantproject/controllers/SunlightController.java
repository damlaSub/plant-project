package co.simplon.plantproject.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.HydrationOrSunlightValue;
import co.simplon.plantproject.services.SunlightService;

@RestController
@RequestMapping("/sunlights")
public class SunlightController {

    private final SunlightService service;

    public SunlightController(SunlightService service) {

	this.service = service;
    }

    @GetMapping
    public Collection<HydrationOrSunlightValue> getAll() {
	return service.getAll();
    }

}
