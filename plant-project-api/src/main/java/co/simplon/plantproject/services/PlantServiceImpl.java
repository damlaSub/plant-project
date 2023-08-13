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
import co.simplon.plantproject.dtos.PlantItem;
import co.simplon.plantproject.entities.Plant;
import co.simplon.plantproject.entities.Sun;
import co.simplon.plantproject.entities.Water;
import co.simplon.plantproject.repositories.PlantRepository;
import co.simplon.plantproject.repositories.SunRepository;
import co.simplon.plantproject.repositories.WaterRepository;

@Service
@Transactional(readOnly = true)
public class PlantServiceImpl implements PlantService {

    private final SunRepository suns;

    private final WaterRepository waters;

    private final PlantRepository plants;

    @Value("${plant.uploads.location}")
    private String uploadDir;

    public PlantServiceImpl(WaterRepository waters,
	    SunRepository suns, PlantRepository plants) {
	this.waters = waters;
	this.suns = suns;
	this.plants = plants;
    }

    @Override
    @Transactional
    public void create(PlantCreateDto inputs) {
	Plant entity = new Plant();
	entity.setName(inputs.getName());
	entity.setLatinName(inputs.getLatinName());
	entity.setDescription(inputs.getDescription());

	if ((inputs.getImageUrl() != null)) {
	    MultipartFile file = inputs.getImageUrl();
	    String baseName = UUID.randomUUID().toString();
	    String fileName = baseName + inputs
		    .getImageUrl().getOriginalFilename();
	    entity.setImageUrl(fileName);
	    store(file, fileName);
	}

	Water water = waters
		.getReferenceById(inputs.getWaterId());
	entity.setWater(water);
	Sun sun = suns.getReferenceById(inputs.getSunId());
	entity.setSun(sun);
	LocalDate addedAt = LocalDate.now();
	entity.setAddedAt(addedAt);
	plants.save(entity);

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

}
