package magbeth.views;

import javafx.scene.layout.*;
import magbeth.core.Board;
import magbeth.core.BoardModel;

import java.util.ArrayList;
import java.util.List;

import static magbeth.core.Constants.BOARDSIZE;


public class BoardView extends GridPane {

    BoardModel boardModel;

    // "Why?", you might ask. This is because I am using a GridPane, and a GridPane doesn't give access to its elements
    // very easily. So because I do want to access the fields (for example for highlighting), I also keep this 2d array.
    // If you have any better ideas, please let me know
    final FieldView[] fields;
    List<FieldView> highlighted;

    FieldView selected=null;

    public BoardView(BoardModel boardModel) {
        fields = new FieldView[BOARDSIZE*BOARDSIZE];
        this.boardModel=boardModel;
        highlighted=new ArrayList<>();
        boardModel.setParent(this);
        // Make BOARDSIZExBOARDSIZE grid with evenly sized fields
        RowConstraints rowc = new RowConstraints();
        rowc.setPercentHeight(100d/BOARDSIZE);
        ColumnConstraints colc= new ColumnConstraints();
        colc.setPercentWidth(100d/BOARDSIZE);

        for(int i=0; i<BOARDSIZE; i++) {
            getRowConstraints().add(rowc);
            getColumnConstraints().add(colc);
        }

        // Add fields to the board
        for(int i=0; i<BOARDSIZE; i++) {
            for(int j=0; j<BOARDSIZE; j++) {
                FieldView a=new FieldView(i, j, this, boardModel.pieceAt(i, j));
                add(a, i, j);
                fields[Board.getIndex(i, j)]=a;
            }
        }

    }
    void click(int x, int y) {
        boardModel.select(x, y);
    }
    public void move(int x, int y) {
        fields[Board.getIndex(x, y)].getChildren().clear();
        fields[Board.getIndex(x,y)].getChildren().addAll(selected.getChildren());
        selected.getChildren().clear();
        deselect();
    }

    public void select(int x, int y, List<Integer> toHighlight) {
        selected=fields[Board.getIndex(x, y)];
        selected.setActive();
        for(int i : toHighlight) {
            fields[i].highlight();
            highlighted.add(fields[i]);
        }
    }
    public void deselect() {
        for(FieldView f : highlighted) {
            f.removeHighlight();
        }
        highlighted.clear();
        selected.deactive();
        selected=null;
    }
}
