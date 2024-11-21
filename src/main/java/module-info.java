module org.example.mongodbjavaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires java.desktop;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;


    opens org.example.mongodbjavaapp to javafx.fxml;
    exports org.example.mongodbjavaapp;
}