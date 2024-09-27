package com.bdd.automation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Configuration
public class AkhilApplicationConfig {
	@Autowired
	public Environment environment;

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

//	@Bean
//	@ConditionalOnProperty(value = "mongodbstatus", havingValue = "enable", matchIfMissing = true)
//	public MongoDatabase mongoCollection() {
//		System.setProperty("jdk.tls.trustNameService", "true");
//		MongoClientURI uri = new MongoClientURI(environment.getProperty("spring.data.mongodburi"));
//		com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(uri);
//		MongoDatabase database = mongoClient.getDatabase("DB NAME");
//		return database;
//	}
	
}
