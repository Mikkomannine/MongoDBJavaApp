package org.example.mongodbjavaapp;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.bson.Document;

public class UserController {
    private final MongoCollection<Document> collection;

    public UserController(MongoDatabase database) {
        this.collection = database.getCollection("users");
    }

    public void createUser(int id, String name, int age, String city) {
        Document document = new Document("id", id)
                .append("name", name)
                .append("age", age)
                .append("city", city);
        collection.insertOne(document);
        showAlert(AlertType.INFORMATION, "User added successfully!");
    }

    public void readUserById(int id) {
        Document query = new Document("id", id);
        Document user = collection.find(query).first();

        if (user != null) {
            showAlert(AlertType.INFORMATION, "Found User: " + user.toJson());
        } else {
            showAlert(AlertType.WARNING, "User not found!");
        }
    }

    public void updateUser(int id, String newCity) {
        Document query = new Document("id", id);
        Document update = new Document("$set", new Document("city", newCity));
        collection.updateOne(query, update);
        showAlert(AlertType.INFORMATION, "User updated successfully!");
    }

    public void deleteUser(int id) {
        Document query = new Document("id", id);
        collection.deleteOne(query);
        showAlert(AlertType.INFORMATION, "User deleted successfully!");
    }

    public void listAllUsers() {
        StringBuilder usersList = new StringBuilder();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                usersList.append(cursor.next().toJson()).append("\n");
            }
        }
        showAlert(AlertType.INFORMATION, usersList.length() > 0 ? usersList.toString() : "No users found!");
    }

    private void showAlert(AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

