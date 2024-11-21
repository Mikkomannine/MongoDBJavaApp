package org.example.mongodbjavaapp;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017/";

    public static MongoDatabase getDatabase(String databaseName) {
        MongoClient client = MongoClients.create(CONNECTION_STRING);
        return client.getDatabase(databaseName);
    }
}

