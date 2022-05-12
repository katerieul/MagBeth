package magbeth.views;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import magbeth.core.Game;

public class GameView extends FlowPane {
    public GameView() {
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        BoardView view = new BoardView(new Game());
        NumberBinding binding = Bindings.min(widthProperty(), heightProperty());
        view.widthProperty().bind(binding);
        view.heightProperty().bind(binding);
        setAlignment(Pos.CENTER);
        getChildren().add(view);
    }
}
