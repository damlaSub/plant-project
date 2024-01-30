package co.simplon.plantproject.dtos;

public interface MyPlantDetail {

    Long getId();

    String getCommonName();

    String getLatinName();

    String getDescription();

    String getImage();

    Long getHydrationId();

    Long getSunlightId();
}