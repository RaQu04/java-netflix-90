package pl.yellowduck.netflix90;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NetflixGuiApp extends Application {

  public static Stage primaryStageObj;

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStageObj = primaryStage;
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/main.fxml"));
    primaryStage.initStyle(StageStyle.DECORATED);
    primaryStage.setTitle("Netflix90 0.0.1");
    Scene mainScene = new Scene(root, 1280, 800);
    mainScene.setRoot(root);
    primaryStage.setScene(mainScene);
    primaryStage.setResizable(false);
    primaryStage.show();
    primaryStage.setOnCloseRequest(e -> Platform.exit());
  }


  public static void main(String[] args) {
    launch(args);
  }
}

//--module-path C:\javafx-sdk-11.0.2\lib --add-modules=javafx.graphics,javafx.controls,javafx.fxml --add-reads javafx.controls=ALL-UNNAMED --add-reads javafx.graphics=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.iio.common=ALL-UNNAMED --add-opens javafx.graphics/com.sun.javafx.css=ALL-UNNAMED --add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED
