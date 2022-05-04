package magbeth.views;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import magbeth.core.Constants;
import magbeth.core.Move;
import magbeth.core.Pieces;

import java.util.List;

public class FieldView extends StackPane {
    int x;
    int y;
    boolean highlighted=false;
    boolean active=false;
    final BoardView parent;
    Background fieldBackground;
    Background highlightBackground;

    FieldView(int x, int y, BoardView parent, Pieces.Piece childmodel) {
        super();
        this.parent=parent;
        this.x=x;
        this.y=y;

        Color fieldColor = (((x + y) % 2) == 0) ? Constants.FIELD_COLOR0 : Constants.FIELD_COLOR1;
        Color highlightColor = (((x + y) % 2) == 0) ? Constants.ACTIVE_COLOR0 : Constants.ACTIVE_COLOR1;
        fieldBackground = new Background(new BackgroundFill(fieldColor, null, null));
        highlightBackground = new Background(new BackgroundFill(highlightColor, null, null));
        //TODO: consider changing nulls to more fancy things
        setBackground(fieldBackground);

        if(childmodel!=null) {
            PieceView child = new PieceView(childmodel, this);
            getChildren().add(child);
        }

        EventHandler<javafx.scene.input.MouseEvent> e= (EventHandler) event -> parent.click(x,y);
        setOnMouseClicked(e);
    }

    void highlight() {
        highlighted=true;
        setBackground(highlightBackground);
    }
    void removeHighlight() {
        highlighted=false;
        setBackground(fieldBackground);
    }
    void setActive () {
        active=true;
    }
    void deactive() {
        active=false;
    }
    void capture() {
        getChildren().clear();
    }

}
