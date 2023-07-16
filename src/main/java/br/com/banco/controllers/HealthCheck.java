package br.com.banco.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/health/status")
@CrossOrigin()
public class HealthCheck {
	@GetMapping()
	public ResponseEntity<String> applicationStatus() {
		return ResponseEntity.ok().body("OK");
	}
}
