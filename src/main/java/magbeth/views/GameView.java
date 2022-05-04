package magbeth.views;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import magbeth.services.GameService;

import static java.lang.Math.min;

public class GameView extends VBox{
    GameService game;
    BoardView boardView;
    public GameView(GameService game, Stage parent) {
        setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        this.game=game;
        boardView = new BoardView(game.getBoardState());
        getChildren().add(boardView);
        setVgrow(getChildren().get(0), Priority.ALWAYS);
    }
}
