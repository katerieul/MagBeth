package magbeth;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import magbeth.views.GameView;

import java.util.Objects;


public class Program extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new GameView(), 589, 589);
        stage.setScene(scene);
//        stage.minWidthProperty().bind(scene.heightProperty());
//        stage.minHeightProperty().bind(scene.widthProperty());
        stage.setTitle("Chess");
        stage.getIcons().add(new Image(Objects.requireNonNull(
                getClass().getResource("/images/queen_black.png")).toString()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
