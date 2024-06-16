package co.simplon.plantproject.dtos;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.plantproject.customValidation.FileType;
import co.simplon.plantproject.customValidation.MaxSize;
import co.simplon.plantproject.customValidation.UniqueCommonName;
import co.simplon.plantproject.customValidation.UniqueLatinName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PlantCreateDto {

    @NotBlank
    @Size(min =2, max = 100)
    @UniqueCommonName
    @Pattern(regexp = "^[a-zA-Z-éàâèêôûîç'’ ]+$")
    private String commonName;

    @NotBlank
    @Size(min =2, max = 200)
    @UniqueLatinName
    @Pattern(regexp = "^[a-zA-Z-éàâèêôûîç'’ ]+$")
    private String latinName;

    @NotBlank
    @Size(min =2, max = 1000)
    private String description;

    @NotNull
    @MaxSize(maxSizeInMB = 1)
    @FileType
    private MultipartFile file;

    @NotNull
    @Positive
    private Long hydrationId;

    @NotNull
    @Positive
    private Long sunlightId;

    public PlantCreateDto() {
	// TODO Auto-generated constructor stub
    }

    public String getCommonName() {
	return commonName;
    }

    public void setCommonName(String commonName) {
	this.commonName = commonName;
    }

    public String getLatinName() {
	return latinName;
    }

    public void setLatinName(String latinName) {
	this.latinName = latinName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public MultipartFile getFile() {
	return file;
    }

    public void setFile(MultipartFile file) {
	this.file = file;
    }

    public Long getHydrationId() {
	return hydrationId;
    }

    public void setHydrationId(Long hydrationId) {
	this.hydrationId = hydrationId;
    }

    public Long getSunlightId() {
	return sunlightId;
    }

    public void setSunlightId(Long sunlightId) {
	this.sunlightId = sunlightId;
    }

    @Override
    public String toString() {
	return "{commonName=" + commonName + ", latinName="
		+ latinName + ", description=" + description
		+ ", file=" + file + ", hydrationId="
		+ hydrationId + ", sunlightId=" + sunlightId
		+ "}";
    }

}
