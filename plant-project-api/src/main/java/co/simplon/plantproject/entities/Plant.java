package co.simplon.plantproject.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "plants")
public class Plant extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "latin_name")
    private String latinName;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "water")
    private int water;

    @Column(name = "sun")
    private int sun;

    @Column(name = "added_at")
    private LocalDate addedAt;

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

    public String getImageUrl() {
	return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
    }

    public int getWater() {
	return water;
    }

    public void setWater(int water) {
	this.water = water;
    }

    public int getSun() {
	return sun;
    }

    public void setSun(int sun) {
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
