package co.simplon.plantproject.dtos;

import co.simplon.plantproject.entities.Hydration;
import co.simplon.plantproject.entities.Sunlight;

public interface PlantForSearch {

	Long getId();

    String getCommonName();

    String getImage();
    
    Hydration getHydration();

    Sunlight getSunlight();
}
