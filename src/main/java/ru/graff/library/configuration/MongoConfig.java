package ru.graff.library.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    public Environment env;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(env.getRequiredProperty("spring.data.mongodb.host"),
                        Integer.valueOf(env.getRequiredProperty("spring.data.mongodb.port")));
    }

    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("spring.data.mongodb.database");
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(new SimpleMongoDbFactory(mongoClient(), env.getRequiredProperty("spring.data.mongodb.database")));
    }

}
