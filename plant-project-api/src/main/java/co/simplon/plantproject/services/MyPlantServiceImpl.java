package co.simplon.plantproject.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.MyPlantAddDto;
import co.simplon.plantproject.dtos.MyPlantDetail;
import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.entities.Account;
import co.simplon.plantproject.entities.MyPlant;
import co.simplon.plantproject.entities.Plant;
import co.simplon.plantproject.errors.AccountNotFoundException;
import co.simplon.plantproject.errors.Conflict;
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
    public Set<MyPlantDetail> getAll() {
	return myPlantRepo.findByAccountId(getCurrentAccountId());

    }

    @Override
    @Transactional
    public void add(MyPlantAddDto inputs) {	    
	    Long plantId = inputs.getPlantId();
	    if (!existsByPlantId(plantId)) {
	    MyPlant entity = new MyPlant();
		entity.setPlant(getPlant(plantId));
		entity.setAccount(getAccount(getCurrentAccountId()));
		myPlantRepo.save(entity);
	    } else {
		throw new Conflict(HttpStatus.CONFLICT,
			"Plant already exists in my plants");
	    }
	  }

    @Override
    @Transactional
    public void delete(Long plantId) {
    	Optional.ofNullable(myPlantRepo.findByAccountIdAndPlantId(getCurrentAccountId(), plantId))
    	.ifPresent(myPlantRepo::delete);
    }
    
    Long getCurrentAccountId() {
	Authentication authentication = SecurityContextHolder
		.getContext().getAuthentication();
	if (!(authentication instanceof AnonymousAuthenticationToken)) {
	    String currentAccountId = authentication
		    .getName();
	    return Long.parseLong(currentAccountId);
	}
	return null;
    }
    
    Account getAccount(Long accountId) {
    	return accountRepo.findById(accountId)
    		.orElseThrow(
    			() -> new AccountNotFoundException(
    				"Account not found"));
    }
    
    Plant getPlant(Long plantId) {
    	return plantRepo.findById(plantId)
    			.orElseThrow(() -> new IllegalArgumentException("Plant not found"));
    }

    @Override
    public boolean existsByPlantId(Long plantId) {
	Long accountId = getCurrentAccountId();
	return Objects.nonNull(
		myPlantRepo.findByAccountIdAndPlantId(
			accountId, plantId));
    }

    @Override
    public Map<Long, Boolean> getPlantStatus() {
	Collection<PlantItem> plants = plantRepo
		.findAllProjectedBy();
	Set<MyPlantDetail> myPlants = myPlantRepo
		.findByAccountId(getCurrentAccountId());
	Map<Long, Boolean> myPlantStatus = new HashMap<>();
	plants.forEach(plant -> {
		boolean isMyPlant = myPlants.stream().anyMatch(myPlant -> 
		myPlant.getId().equals(plant.getId()));
		myPlantStatus.put(plant.getId(), isMyPlant);
	});
	return myPlantStatus;
    }
}