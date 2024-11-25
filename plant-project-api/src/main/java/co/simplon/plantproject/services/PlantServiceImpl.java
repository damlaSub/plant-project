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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.plantproject.dtos.PlantCreateDto;
import co.simplon.plantproject.dtos.PlantDetail;
import co.simplon.plantproject.dtos.PlantForUpdate;
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
	String fileName = setFileName(inputs.getFile()
		    .getOriginalFilename());
	entity.setImage(fileName);
	store(file, fileName);
	LocalDate addedAt = LocalDate.now();
	entity.setAddedAt(addedAt);
	Long plantCode = this.plants
			.getNextValMySequence();
		entity.setPlantCode(
			"PL".concat(plantCode.toString()));
	plants.save(entity);

    }

    @Override
    @Transactional
    public void update(Long id, PlantUpdateDto inputs) {
	Plant entity = plants.findById(id).get();
	entity.setCommonName(inputs.getCommonName());
	entity.setLatinName(inputs.getLatinName());
	entity.setDescription(inputs.getDescription());
	Hydration hydration = hydrations
		.getReferenceById(inputs.getHydrationId());
	entity.setHydration(hydration);
	Sunlight sunlight = sunlights
		.getReferenceById(inputs.getSunlightId());
	entity.setSunlight(sunlight);
	if (inputs.getFile() != null) {
	    Path oldImage = Paths.get(uploadDir,
		    entity.getImage());
	    MultipartFile file = inputs.getFile();
	    String fileName = setFileName(inputs.getFile()
			    .getOriginalFilename()); 
	    entity.setImage(fileName);
	    store(file, fileName);
	    oldImage.toFile().delete();
	}
    }
    
    private String setFileName(String originalFilename){
    	String baseName = UUID.randomUUID().toString();
 	    String fileExtention = StringUtils
 		    .getFilenameExtension(originalFilename);
 	    return baseName + "."+ fileExtention;
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
    public PlantForUpdate getForUpdate(Long id) {
	return plants.findProjectedById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
	Plant entity = plants.findById(id).get();
	plants.delete(entity);
	deleteFile(entity.getImage());
    }
    
    private void deleteFile(String image) {
		Path target = Paths.get(uploadDir).resolve(image);
		try {
		    Files.delete(target);
		} catch (IOException ex) {
		    throw new RuntimeException(ex);
		}
    }
    
    @Override
    public Collection<PlantItem> searchPlants(String searchText){
    	return plants.findPlantsBySearchText(searchText);
    }
    
    public Boolean existsByCommonNameIgnoreCase(String commonName) {
        return plants.existsByCommonNameIgnoreCase(commonName);
    }

	@Override
	public Boolean existsByLatinNameIgnoreCase(String latinName) {
		return plants.existsByLatinNameIgnoreCase(latinName);

	}
}
