package com.upsidedown.memofitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upsidedown.memofitness.model.Health;
import com.upsidedown.memofitness.service.RestService;

@RestController
public class HealthController {

	@Autowired
	private RestService restService;
	
	@RequestMapping(value = "/health")
	public Health health() {
		return restService.checkApplication();
	}
}
