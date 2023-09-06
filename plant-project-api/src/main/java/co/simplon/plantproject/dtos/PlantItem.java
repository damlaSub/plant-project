package co.simplon.plantproject.dtos;

import co.simplon.plantproject.entities.Sun;
import co.simplon.plantproject.entities.Water;

public interface PlantItem {

    Long getId();

    String getName();

    String getLatinName();

    String getDescription();

    String getImage();

    Water getWater();

    Sun getSun();

}
