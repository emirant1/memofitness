package com.upsidedown.memofitness.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.upsidedown.memofitness.controller", "com.upsidedown.memofitness.service"})
public class Config {

}
