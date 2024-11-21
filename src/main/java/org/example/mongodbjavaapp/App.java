package org.example.mongodbjavaapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.mongodb.client.MongoDatabase;

public class App extends Application {
    private UserController userDAO;

    @Override
    public void start(Stage primaryStage) {
        MongoDatabase database = MongoDBConnection.getDatabase("userDB");
        userDAO = new UserController(database);

        primaryStage.setTitle("MongoDB CRUD Operations");

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        TextField cityField = new TextField();

        Button addButton = new Button("Add");
        Button readButton = new Button("Read");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button listButton = new Button("List All");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Age:"), 0, 2);
        grid.add(ageField, 1, 2);
        grid.add(new Label("City:"), 0, 3);
        grid.add(cityField, 1, 3);
        grid.add(addButton, 0, 4);
        grid.add(readButton, 1, 4);
        grid.add(updateButton, 0, 5);
        grid.add(deleteButton, 1, 5);
        grid.add(listButton, 0, 6, 2, 1);

        addButton.setOnAction(e -> userDAO.createUser(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                Integer.parseInt(ageField.getText()),
                cityField.getText()
        ));

        readButton.setOnAction(e -> userDAO.readUserById(Integer.parseInt(idField.getText())));

        updateButton.setOnAction(e -> userDAO.updateUser(
                Integer.parseInt(idField.getText()),
                cityField.getText()
        ));

        deleteButton.setOnAction(e -> userDAO.deleteUser(Integer.parseInt(idField.getText())));

        listButton.setOnAction(e -> userDAO.listAllUsers());

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

