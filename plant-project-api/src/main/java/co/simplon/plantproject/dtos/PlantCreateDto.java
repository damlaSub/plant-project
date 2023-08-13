package co.simplon.plantproject.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PlantCreateDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String latinName;

    @NotBlank
    @Size(max = 1000)
    private String description;

    // @NotBlank
    private MultipartFile imageUrl;

    @NotNull
    @Positive
    private Long waterId;

    @NotNull
    @Positive
    private Long sunId;

    public PlantCreateDto() {
	// TODO Auto-generated constructor stub
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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

    public MultipartFile getImageUrl() {
	return imageUrl;
    }

    public void setImageUrl(MultipartFile imageUrl) {
	this.imageUrl = imageUrl;
    }

    public Long getWaterId() {
	return waterId;
    }

    public void setWaterId(Long waterId) {
	this.waterId = waterId;
    }

    public Long getSunId() {
	return sunId;
    }

    public void setSunId(Long sunId) {
	this.sunId = sunId;
    }

    @Override
    public String toString() {
	return "{name=" + name + ", latinName=" + latinName
		+ ", description=" + description
		+ ", imageUrl=" + imageUrl + ", waterId="
		+ waterId + ", sunId=" + sunId + "}";
    }

}
