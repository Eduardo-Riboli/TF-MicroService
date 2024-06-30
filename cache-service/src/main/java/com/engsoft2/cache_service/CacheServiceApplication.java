package com.engsoft2.cache_service;

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

	HashMap<Integer, Boolean> map = new HashMap<>();

	@GetMapping("/assinvalida/{codass}")
	public ResponseEntity<Boolean> checkActiveSubscription(@PathVariable long codass){
		Boolean value = map.get(codass);

		if (value) {
			return ResponseEntity.status(200).body(value);
		} else {
			// CHAMA O T1-SERVICE
			return ResponseEntity.status(200).body(value);
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
		HashMap<String, String> status = new HashMap<>();
		status.put("status", "running");
		return status;
	}

}
