package co.simplon.plantproject.services;

import java.util.Collection;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.MyPlantAddDto;
import co.simplon.plantproject.dtos.MyPlantDetail;
import co.simplon.plantproject.entities.Account;
import co.simplon.plantproject.entities.MyPlant;
import co.simplon.plantproject.entities.Plant;
import co.simplon.plantproject.repositories.AccountRepository;
import co.simplon.plantproject.repositories.MyPlantRepository;
import co.simplon.plantproject.repositories.PlantRepository;

@Service
@Transactional(readOnly = true)
public class MyPlantServiceImpl implements MyPlantService {

    private final MyPlantRepository myPlantRepo;
    private final AccountRepository accountRepo;
    private final PlantRepository plantRepo;

    public MyPlantServiceImpl(MyPlantRepository myPlantRepo,
	    AccountRepository accountRepo,
	    PlantRepository plantRepo) {
	this.myPlantRepo = myPlantRepo;
	this.accountRepo = accountRepo;
	this.plantRepo = plantRepo;

    }

    @Override
    public Collection<MyPlantDetail> getAll(Long id) {
	String email = SecurityContextHolder.getContext()
		.getAuthentication().getName();
	Account account = accountRepo.findByEmail(email);
	Long accountId = account.getId();
	if (accountId.equals(id)) {
	    return myPlantRepo.findByAccountId(id);
	} else {
	    return null;
	    // check it!
	}
    }

    @Override
    @Transactional
    public void add(MyPlantAddDto inputs) {
	String email = SecurityContextHolder.getContext()
		.getAuthentication().getName();
	Account account = accountRepo.findByEmail(email);
	MyPlant entity = new MyPlant();
	if ((account.getId() != null) & account.getRole()
		.getCode().contains("USER")) {
	    Long plantId = inputs.getPlantId();
	    Plant plant = plantRepo.findById(plantId).get();
	    entity.setPlant(plant);
	    entity.setAccount(account);
	    myPlantRepo.save(entity);
	}
    }

    @Override
    @Transactional
    public void delete(Long id) {
	MyPlant entity = myPlantRepo.findById(id).get();
	myPlantRepo.delete(entity);

    }

}