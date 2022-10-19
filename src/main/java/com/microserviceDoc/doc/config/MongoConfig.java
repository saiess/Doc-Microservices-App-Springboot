package com.microserviceDoc.doc.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Override
    @NonNull
    protected String getDatabaseName() {
        return this.database;
    }

    @Override
    @NonNull
    public MongoClient mongoClient() {
        return MongoClients.create(this.host);
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
