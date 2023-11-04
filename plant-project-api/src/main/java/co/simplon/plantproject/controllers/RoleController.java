package co.simplon.plantproject.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.plantproject.dtos.RoleValue;
import co.simplon.plantproject.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
	this.service = service;
    }

    @GetMapping
    public Collection<RoleValue> getAll() {
	return service.getAll();
    }
}
