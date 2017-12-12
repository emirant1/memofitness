package com.upsidedown.memofitness.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.upsidedown.memofitness.model.Health;

@Service
public class RestService {
	/* The logger */
	private static Logger logger = LoggerFactory.getLogger(RestService.class);
	
	/* The type of document used in the health page */
	private static final String TYPE_TEST = "test";
	
	@Value("${application.name}")
	private String applicationName;
	
	@Value("${elastic.host}")
	private String host;
	
	@Value("${elastic.port}")
	private int port;
	
	@Value("${elastic.scheme}")
	private String scheme;
	
	/* The clients for REST calls on the elastic search engine */
	private RestHighLevelClient highLevelClient;
	
	
	public Health checkApplication() {	
		/* Dummy document data for test purpose */
		Map<String, Object> source = new HashMap<>();
		source.put("test", "test");
		
		/* Build the index request */
		IndexRequest indexRequest = new IndexRequest(applicationName, TYPE_TEST).source(source);
		
		try {
			/* Send it to elastic search */
			IndexResponse indexResponse = highLevelClient.index(indexRequest);
			if(indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
				
				/* The id of the created document */
				String documentId = indexResponse.getId();
				
				/* Build the delete Request */
				DeleteRequest deleteRequest = new DeleteRequest(applicationName, TYPE_TEST, documentId);
				
				/* Send it to elastic search */
				DeleteResponse deleteResponse = highLevelClient.delete(deleteRequest);
				
				if(deleteResponse.getResult() != DocWriteResponse.Result.NOT_FOUND) {
					Health health = new Health(applicationName, host, port, scheme, true);
					
					return health;
				}
			}
		} 
		catch (Exception e) {
			logger.error("An error occured while checking the application's health: {}", e.getMessage());
		}
		
		return new Health("", "", 0, "", false);
	}
	
	@PostConstruct
	public void init() {		
		highLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, scheme)));
	}
	
	@PreDestroy
	public void clean() {
		try {
			highLevelClient.close();
		} 
		catch (IOException e) {
			logger.error("An error occured while closing the rest client: {}", e.getMessage());
		}
	}
}
