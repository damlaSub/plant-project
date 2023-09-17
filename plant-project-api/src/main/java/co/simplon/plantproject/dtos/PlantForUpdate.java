package co.simplon.plantproject.dtos;

import co.simplon.plantproject.entities.Hydration;
import co.simplon.plantproject.entities.Sunlight;

public interface PlantForUpdate {

    String getCommonName();

    String getLatinName();

    String getDescription();

    Hydration getHydration();

    Sunlight getSunlight();
}
