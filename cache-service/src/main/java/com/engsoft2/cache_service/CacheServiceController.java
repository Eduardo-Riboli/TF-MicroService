package com.engsoft2.cache_service;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
public class CacheServiceController {

    T1ServiceProxy proxy;
    HashMap<Integer, String> map = new HashMap<>();

    public CacheServiceController(T1ServiceProxy proxy) {
        this.proxy = proxy;
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

	@GetMapping("/assinvalida/{codass}")
	// public ResponseEntity<Boolean> checkActiveSubscription(@PathVariable long codass){
	public ResponseEntity<HashMap<String, String>> checkActiveSubscription(@PathVariable long codass){
		String value = map.get((int)codass);

		if (value != null) {
			LocalDate date = LocalDate.parse(value);

			if (date.equals(java.time.LocalDate.now()) || date.isAfter(java.time.LocalDate.now())) {
                // DEBUG
                // HashMap<String, String> response = new HashMap<>();
                // response.put("value", value);
                // response.put("equal or less", "sim");
                // response.put("nao chamou url", "nao chamou");
                // response.put("codass", Long.toString(codass));
				// return ResponseEntity.status(200).body(response);

				// return ResponseEntity.status(200).body(true);
			} else {
                // DEBUG
                // HashMap<String, String> response = new HashMap<>();
                // response.put("value", value);
                // response.put("equal or less", "nao");
                // response.put("nao chamou url", "nao chamou");
                // response.put("codass", Long.toString(codass));
				// return ResponseEntity.status(200).body(response);

				// return ResponseEntity.status(200).body(false);
			}
		} else {
			// CHAMAR O t1-service
            CacheService cache = proxy.retrieveEndDate(Long.toString(codass));
            
            // DEBUG
			HashMap<String, String> response = new HashMap<>();
            response.put("codass", Long.toString(codass));
            response.put("value", value);
            response.put("url t1-service", "chamou");
            response.put("proxy", cache.getActive());
			return ResponseEntity.status(404).body(response);

			// return ResponseEntity.status(404).body(false);
		}
		
	}

	@PostMapping("/eraseass/{codass}")
	public ResponseEntity<Boolean> eraseSubscriptionInformation(@PathVariable long codass){
		map.remove(codass);

		return ResponseEntity.status(200).body(true);
	}
}