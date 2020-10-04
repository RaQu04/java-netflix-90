package pl.yellowduck.netflix90.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    FlowPane mainPanel;

    public void quit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void films(ActionEvent actionEvent) {

    }

    public void categories(ActionEvent actionEvent) throws IOException {
        Parent categories = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/categories.fxml")));


        Stage stage = new Stage();
        Scene scene = new Scene(categories, 800,600);
        stage.setScene(scene);
        stage.show();
    }

    public void newCategory(ActionEvent actionEvent) throws IOException {
        Parent category = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("views/category.fxml")));

        Stage stage = new Stage();
        Scene scene = new Scene(category, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}