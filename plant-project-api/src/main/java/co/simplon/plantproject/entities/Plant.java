package co.simplon.plantproject.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "plants")
public class Plant extends AbstractEntity {
	
	@Column(name = "plant_code")
    private String plantCode;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hydration_id")
    private Hydration hydration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sunlight_id")
    private Sunlight sunlight;

    public Plant() {

    }
    
    public String getPlantCode() {
    	return plantCode;
        }

        public void setPlantCode(String plantCode) {
    	this.plantCode = plantCode;
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
		return "Plant [plantCode=" + plantCode + ", commonName=" + commonName + ", latinName=" + latinName
				+ ", description=" + description + ", image=" + image + ", addedAt=" + addedAt + ", hydration="
				+ hydration + ", sunlight=" + sunlight + "]";
	}

	@Override
    public int hashCode() {
	return Objects.hash(plantCode);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	Plant other = (Plant) obj;
	return Objects.equals(plantCode, other.plantCode);
    }

}
