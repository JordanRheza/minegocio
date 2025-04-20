package com.alquimiasoft.minegocio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinegocioApplication {

	private static final Logger log = LogManager.getLogger(MinegocioApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MinegocioApplication.class, args);
		log.info("Se levantó el API");
	}
}
