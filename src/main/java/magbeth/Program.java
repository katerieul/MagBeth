package magbeth;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import magbeth.services.GameService;
import magbeth.views.BoardView;
import magbeth.views.GameView;


public class Program extends Application {

    @Override
    public void start(Stage stage) {

//        scene.getStylesheets().add("style.css");
        // ^ could be implemented at some point to make everything nicer and more beautiful
        GameService gameService= new GameService();
        gameService.newGame();
        GameView gameView = new GameView(gameService, stage);

        Scene scene = new Scene(gameView, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args);}
}
