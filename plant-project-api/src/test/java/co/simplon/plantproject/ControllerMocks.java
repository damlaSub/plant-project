package co.simplon.plantproject;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import co.simplon.plantproject.controllers.AuthController;
import co.simplon.plantproject.controllers.HydrationController;
import co.simplon.plantproject.controllers.MyPlantController;
import co.simplon.plantproject.controllers.PlantController;
import co.simplon.plantproject.controllers.SunlightController;

@TestConfiguration
public class ControllerMocks {

	@Bean
    AuthController authController() {
    return Mockito.mock(AuthController.class);
    }
	
	@Bean
    PlantController plantController() {
    return Mockito.mock(PlantController.class);
    }
	
	@Bean
    MyPlantController myPlantController() {
    return Mockito.mock(MyPlantController.class);
    }
	
	@Bean
    SunlightController sunlightController() {
    return Mockito.mock(SunlightController.class);
    }
	
	@Bean
    HydrationController hydrationController() {
    return Mockito.mock(HydrationController.class);
    }
}
