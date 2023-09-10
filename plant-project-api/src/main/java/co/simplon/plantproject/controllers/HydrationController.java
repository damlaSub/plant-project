package co.simplon.plantproject.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.HydrationOrSunlightValue;
import co.simplon.plantproject.services.HydrationService;

@RestController
@RequestMapping("/hydrations")
public class HydrationController {

    private final HydrationService service;

    public HydrationController(HydrationService service) {
	this.service = service;
    }

    @GetMapping
    public Collection<HydrationOrSunlightValue> getAll() {
	return service.getAll();
    }
}
