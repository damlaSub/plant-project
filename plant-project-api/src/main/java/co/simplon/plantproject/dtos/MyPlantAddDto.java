package co.simplon.plantproject.dtos;

import jakarta.validation.constraints.NotNull;

public class MyPlantAddDto {

    @NotNull
    private Long PlantId;

    public MyPlantAddDto() {

    }

    public Long getPlantId() {
	return PlantId;
    }

    public void setPlantId(Long plantId) {
	PlantId = plantId;
    }

    @Override
    public String toString() {
	return "{PlantId=" + PlantId + "}";
    }

}
