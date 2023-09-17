package co.simplon.plantproject.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.plantproject.dtos.PlantCreateDto;
import co.simplon.plantproject.dtos.PlantDetail;
import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.dtos.PlantUpdateDto;
import co.simplon.plantproject.entities.Hydration;
import co.simplon.plantproject.entities.Plant;
import co.simplon.plantproject.entities.Sunlight;
import co.simplon.plantproject.repositories.HydrationRepository;
import co.simplon.plantproject.repositories.PlantRepository;
import co.simplon.plantproject.repositories.SunlightRepository;

@Service
@Transactional(readOnly = true)
public class PlantServiceImpl implements PlantService {

    @Value("${plant.uploads.location}")
    private String uploadDir;

    private final SunlightRepository sunlights;

    private final HydrationRepository hydrations;

    private final PlantRepository plants;

    public PlantServiceImpl(HydrationRepository hydrations,
	    SunlightRepository sunlights,
	    PlantRepository plants) {
	this.hydrations = hydrations;
	this.sunlights = sunlights;
	this.plants = plants;
    }

    @Override
    @Transactional
    public void create(PlantCreateDto inputs) {
	Plant entity = new Plant();
	entity.setCommonName(inputs.getCommonName());
	entity.setLatinName(inputs.getLatinName());
	entity.setDescription(inputs.getDescription());
	Hydration hydration = hydrations
		.getReferenceById(inputs.getHydrationId());
	entity.setHydration(hydration);
	Sunlight sunlight = sunlights
		.getReferenceById(inputs.getSunlightId());
	entity.setSunlight(sunlight);
	MultipartFile file = inputs.getFile();
	String baseName = UUID.randomUUID().toString();
	String fileName = baseName
		+ inputs.getFile().getOriginalFilename();
	entity.setImage(fileName);
	store(file, fileName);
	LocalDate addedAt = LocalDate.now();
	entity.setAddedAt(addedAt);
	plants.save(entity);
	System.out.println(
		inputs.getFile().getOriginalFilename());

    }

    @Override
    @Transactional
    public void update(Long id, PlantUpdateDto inputs) {
	Plant entity = plants.findById(id).get();

	if (inputs.getFile() != null) {
	    Path oldImage = Paths.get(uploadDir,
		    entity.getImage());
	    MultipartFile file = inputs.getFile();
	    String baseName = UUID.randomUUID().toString();
	    String fileName = baseName + inputs.getFile()
		    .getOriginalFilename();
	    entity.setImage(fileName);
	    store(file, fileName);
	    oldImage.toFile().delete();
	}

	entity.setDescription(inputs.getDescription());
	LocalDate addedAt = LocalDate.now();
	entity.setAddedAt(addedAt);
	plants.save(entity);
	System.out.println(
		inputs.getFile().getOriginalFilename());

    }

    private void store(MultipartFile file,
	    String fileName) {
	Path uploadPath = Paths.get(uploadDir);
	Path target = uploadPath.resolve(fileName);
	try (InputStream in = file.getInputStream()) {
	    Files.copy(in, target,
		    StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}
    }

    @Override
    public Collection<PlantItem> getAll() {
	return plants.findAllProjectedBy();
    }

    @Override
    public PlantDetail getPlant(Long id) {
	return plants.findProjectedDetailById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
	Plant entity = plants.findById(id).get();
	String image = entity.getImage();
	plants.delete(entity);
	Path target = Paths.get(uploadDir).resolve(image);
	try {
	    Files.delete(target);
	} catch (IOException ex) {
	    throw new RuntimeException(ex);
	}

    }

}
