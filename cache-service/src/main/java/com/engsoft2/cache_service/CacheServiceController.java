package com.engsoft2.cache_service;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/servcad")
public class CacheServiceController {

	T1ServiceProxy proxy;
	HashMap<Integer, String> map = new HashMap<>();

	public CacheServiceController(T1ServiceProxy proxy) {
		this.proxy = proxy;
	}

	@GetMapping("/getStatus")
	public HashMap<String, String> getStatus() {
		HashMap<String, String> status = new HashMap<>();
		status.put("name", "cache-service");
		status.put("status", "running");
		status.put("1", map.get(1));
		status.put("2", map.get(2));
		status.put("3", map.get(3));
		status.put("4", map.get(4));
		status.put("5", map.get(5));
		status.put("6", map.get(6));
		status.put("7", map.get(7));
		status.put("8", map.get(8));
		return status;
	}

	@GetMapping("/assinvalida/{codass}")
	public ResponseEntity<Boolean> checkActiveSubscription(@PathVariable long codass) {
		String value = map.get((int) codass);

		if (value != null) {
			LocalDate date = LocalDate.parse(value);

			if (date.equals(java.time.LocalDate.now()) || date.isAfter(java.time.LocalDate.now())) {
				return ResponseEntity.status(200).body(true);
			} else {
				return ResponseEntity.status(200).body(false);
			}
		} else {
			// CHAMAR O t1-service
			String date = proxy.retrieveEndDate(Long.toString(codass));
			String datePart = date.substring(0, 10);
			LocalDate localDate = LocalDate.parse(datePart);
			map.put((int) codass, datePart);

			if (localDate.equals(java.time.LocalDate.now()) || localDate.isAfter(java.time.LocalDate.now())) {
				return ResponseEntity.status(200).body(true);
			} else {
				return ResponseEntity.status(200).body(false);
			}
		}
	}

	@PostMapping("/eraseass/{codass}")
	public ResponseEntity<Boolean> eraseSubscriptionInformation(@PathVariable long codass) {
		map.remove(codass);

		return ResponseEntity.status(200).body(true);
	}
}