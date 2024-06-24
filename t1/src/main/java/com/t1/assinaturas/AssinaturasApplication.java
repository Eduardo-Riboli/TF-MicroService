package com.t1.assinaturas;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AssinaturasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssinaturasApplication.class, args);
	}

	@GetMapping("/getStatus")
	public HashMap<String, String> getStatus() {
		HashMap<String, String> status = new HashMap<>();
		status.put("status", "running");
		return status;
	}

}
