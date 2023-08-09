package co.simplon.plantproject.dtos;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @DecimalMax("3.00")
    private Short water;

    @DecimalMax("3.00")
    private Short sun;

    private LocalDate addedAt;

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

    public void setImageUrl(MultipartFile imageP) {
	this.imageUrl = imageUrl;
    }

    public Short getWater() {
	return water;
    }

    public void setWater(Short water) {
	this.water = water;
    }

    public Short getSun() {
	return sun;
    }

    public void setSun(Short sun) {
	this.sun = sun;
    }

    public LocalDate getAddedAt() {
	return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
	this.addedAt = addedAt;
    }

    @Override
    public String toString() {
	return "{name=" + name + ", latinName=" + latinName
		+ ", description=" + description
		+ ", imageUrl=" + imageUrl + ", water="
		+ water + ", sun=" + sun + ", addedAt="
		+ addedAt + "}";
    }

}
