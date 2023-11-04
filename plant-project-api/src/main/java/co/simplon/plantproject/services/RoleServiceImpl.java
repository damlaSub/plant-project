package co.simplon.plantproject.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.RoleValue;
import co.simplon.plantproject.repositories.RoleRepository;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    public RoleServiceImpl(RoleRepository roles) {
	this.roles = roles;
    }

    private RoleRepository roles;

    @Override
    public Collection<RoleValue> getAll() {
	return roles.findAllProjectedBy();
    }
}
