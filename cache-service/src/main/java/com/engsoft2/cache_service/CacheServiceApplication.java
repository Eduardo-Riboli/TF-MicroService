package com.engsoft2.cache_service;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CacheServiceApplication {

	HashMap<Integer, String> map = new HashMap<>();

	@GetMapping("/assinvalida/{codass}")
	public ResponseEntity<Boolean> checkActiveSubscription(@PathVariable long codass){
		String value = map.get((int)codass);

		if (value != null) {
			LocalDate date = LocalDate.parse(value);

			if (date.equals(java.time.LocalDate.now()) || date.isAfter(java.time.LocalDate.now())) {
				return ResponseEntity.status(200).body(true);
			} else {
				return ResponseEntity.status(200).body(false);
			}
		} else {
			// CHAMAR O T1-service

			HashMap<String, String> response = new HashMap<>();
			response.put("error", "404");
			response.put("message", "Could not find Subscription ID.");
			return ResponseEntity.status(404).body(response);
		}
		
	}

	@PostMapping("/eraseass/{codass}")
	public ResponseEntity<Boolean> eraseSubscriptionInformation(@PathVariable long codass){
		map.remove(codass);

		return ResponseEntity.status(200).body(true);
	}

	public static void main(String[] args) {
		SpringApplication.run(CacheServiceApplication.class, args);
	}

	@GetMapping("/getStatus")
	public HashMap<String, String> getStatus(){
		map.put(1, "2024-07-30");
		map.put(2, "2024-06-30");
		map.put(3, "2024-05-30");
		HashMap<String, String> status = new HashMap<>();
		status.put("name", "cache-service");
		status.put("status", "running");
		return status;
	}

}
