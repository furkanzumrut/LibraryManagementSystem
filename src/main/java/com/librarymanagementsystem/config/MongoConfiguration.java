package com.librarymanagementsystem.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {

	private static final String database = "heroku_app37442521";
	private static final String serverName = "ds053529.mongolab.com:53529";
	private static final String userName = "heroku_rebios";
	private static final String userPassword = "199300";
	private static final String basePackage = "com.librarymanagementsystem.domain";

	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public Mongo mongo() throws Exception {
		MongoCredential credential = MongoCredential.createMongoCRCredential(
				userName, database, userPassword.toCharArray());

		return new MongoClient(new ServerAddress(serverName),
				Arrays.asList(credential));
	}

	@Override
	protected String getMappingBasePackage() {
		return basePackage;
	}
}
