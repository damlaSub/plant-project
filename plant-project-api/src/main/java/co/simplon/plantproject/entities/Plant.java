package co.simplon.plantproject.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "image")
    private String imageUrl;

    @Column(name = "added_at")
    private LocalDate addedAt;

    @ManyToOne
    @JoinColumn(name = "water_id")
    private Water water;

    @ManyToOne
    @JoinColumn(name = "sun_id")
    private Sun sun;

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

    public LocalDate getAddedAt() {
	return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
	this.addedAt = addedAt;
    }

    public Water getWater() {
	return water;
    }

    public void setWater(Water water) {
	this.water = water;
    }

    public Sun getSun() {
	return sun;
    }

    public void setSun(Sun sun) {
	this.sun = sun;
    }

    @Override
    public String toString() {
	return "{name=" + name + ", latinName=" + latinName
		+ ", description=" + description
		+ ", imageUrl=" + imageUrl + ", addedAt="
		+ addedAt + ", water=" + water + ", sun="
		+ sun + "}";
    }

}
