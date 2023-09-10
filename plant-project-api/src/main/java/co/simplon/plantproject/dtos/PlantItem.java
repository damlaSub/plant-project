package co.simplon.plantproject.dtos;

import co.simplon.plantproject.entities.Hydration;
import co.simplon.plantproject.entities.Sunlight;

public interface PlantItem {

    Long getId();

    String getCommonName();

    String getLatinName();

    String getDescription();

    String getImage();

    Hydration getHydration();

    Sunlight getSunlight();

}
