package com.upsidedown.memofitness.model;

public class Health {
	private String applicationName;
	private String elasticHost;
	private int elasticPort;
	private String elasticScheme;
	private boolean elasticReachable;
	
	/**
	 * Constructor
	 * @param applicationName The application's name
	 * @param elasticHost The host where the search engine is reachable
	 * @param elasticPort The port number of the elastic search engine
	 * @param elasticScheme The scheme of the elastic search engine
	 * @param elasticReachable true if operations can be performed on the search engine 
	 */
	public Health(String applicationName, String elasticHost, int elasticPort, String elasticScheme, boolean elasticReachable) {
		this.applicationName = applicationName; 
		this.elasticHost = elasticHost;
		this.elasticPort = elasticPort;
		this.elasticScheme = elasticScheme;
		this.elasticReachable = elasticReachable;
	}

	/**
	 * Bean getter method
	 * @return The application's name
	 */
	public String getApplicationName() {
		return applicationName;
	}
	
	/**
	 * Bean getter method
	 * @return The host where the search engine is reachable
	 */
	public String getElasticHost() {
		return elasticHost;
	}

	/**
	 * Bean getter method
	 * @return The port number of the elastic search engine
	 */
	public int getElasticPort() {
		return elasticPort;
	}

	/**
	 * Bean getter method
	 * @return elasticPort The port number of the elastic search engine
	 */
	public String getElasticScheme() {
		return elasticScheme;
	}

	/**
	 * Bean getter method
	 * @return true if operations can be performed on the search engine
	 */
	public boolean isElasticReachable() {
		return elasticReachable;
	}
	
	
	
	
}
