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

    @Override
    protected String getDatabaseName() {
        return "heroku_app37356552";
    }

    @Override
    public Mongo mongo() throws Exception {
    	MongoCredential credential = MongoCredential.createMongoCRCredential("heroku_rebios", getDatabaseName(), "199300".toCharArray());

        return new MongoClient(new ServerAddress("ds031631.mongolab.com:31631"),Arrays.asList(credential));
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.librarymanagementsystem.domain";
    }
}
