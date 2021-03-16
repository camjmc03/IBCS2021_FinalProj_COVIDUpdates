package Cam.IAProject.CovidUpdater;

import Cam.IAProject.CovidUpdater.model.ISDENewsScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.MalformedURLException;

@SpringBootApplication
public class CovidUpdaterApplication {

	public static void main(String[] args) {
		//runs the application and starts the server setup process
		SpringApplication.run(CovidUpdaterApplication.class, args);

	}

}


