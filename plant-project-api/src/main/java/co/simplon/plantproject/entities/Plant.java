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

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "latin_name")
    private String latinName;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "added_at")
    private LocalDate addedAt;

    @ManyToOne
    @JoinColumn(name = "hydration_id")
    private Hydration hydration;

    @ManyToOne
    @JoinColumn(name = "sunlight_id")
    private Sunlight sunlight;

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

    public String getImage() {
	return image;
    }

    public void setImage(String image) {
	this.image = image;
    }

    public LocalDate getAddedAt() {
	return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
	this.addedAt = addedAt;
    }

    public Hydration getHydration() {
	return hydration;
    }

    public void setHydration(Hydration hydration) {
	this.hydration = hydration;
    }

    public Sunlight getSunlight() {
	return sunlight;
    }

    public void setSunlight(Sunlight sunlight) {
	this.sunlight = sunlight;
    }

    @Override
    public String toString() {
	return "{commonName=" + commonName + ", latinName="
		+ latinName + ", description=" + description
		+ ", image=" + image + ", addedAt="
		+ addedAt + ", hydration=" + hydration
		+ ", sunlight=" + sunlight + "}";
    }

}
