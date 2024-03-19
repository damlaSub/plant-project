package co.simplon.plantproject.services;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public Set<MyPlantDetail> getAll() {
	Long id = getAccountId();
	return myPlantRepo.findByAccountId(id);

    }

    @Override
    @Transactional
    public void add(MyPlantAddDto inputs)
	    throws BadRequestException {
	Long accountId = getAccountId();
	if ((Objects.nonNull(accountId))) {
	    Account account = accountRepo
		    .findById(accountId).orElse(null);
	    if ((Objects.nonNull(account))) {
		MyPlant entity = new MyPlant();
		Long plantId = inputs.getPlantId();
		Plant plant = plantRepo.findById(plantId)
			.orElse(null);
		if (Objects.nonNull(plant)
			&& !exists(plantId)) {
		    entity.setPlant(plant);
		    entity.setAccount(account);
		    myPlantRepo.save(entity);
		} else if (exists(plantId)) {
		    throw new BadRequestException(
			    "Plant already exists in my plants");
		}
	    }
	}
    }

    @Override
    @Transactional
    public void delete(Long plantId)
	    throws NoSuchElementException {
	Long accountId = getAccountId();
	if (!exists(plantId)) {
	    throw new NoSuchElementException(
		    "Plant does not exists in my plants");
	}
	MyPlant entity = myPlantRepo
		.findByAccountIdAndPlantId(accountId,
			plantId);
	myPlantRepo.delete(entity);

    }

    public Long getAccountId() {
	Authentication authentication = SecurityContextHolder
		.getContext().getAuthentication();
	if (!(authentication instanceof AnonymousAuthenticationToken)) {
	    String currentAccountId = authentication
		    .getName();
	    return Long.parseLong(currentAccountId);
	}
	return null;
    }

    @Override
    public boolean exists(Long plantId) {
	Long accountId = getAccountId();
	return Objects.nonNull(
		myPlantRepo.findByAccountIdAndPlantId(
			accountId, plantId));

    }

}