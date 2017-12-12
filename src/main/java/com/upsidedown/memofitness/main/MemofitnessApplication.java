package com.upsidedown.memofitness.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.upsidedown.memofitness.config.Config;

@SpringBootApplication
@Import({Config.class})
public class MemofitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemofitnessApplication.class, args);
	}
}
