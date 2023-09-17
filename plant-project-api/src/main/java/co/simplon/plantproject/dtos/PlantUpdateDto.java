package co.simplon.plantproject.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlantUpdateDto {

    @NotBlank
    @Size(max = 1000)
    private String description;

    private MultipartFile file;

    public PlantUpdateDto() {

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

    @Override
    public String toString() {
	return "{description=" + description + ", file="
		+ file + "}";
    }

}
