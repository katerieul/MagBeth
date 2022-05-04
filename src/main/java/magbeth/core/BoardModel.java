package magbeth.core;

import magbeth.views.BoardView;

import java.util.List;

public class BoardModel {
    Board board;
    BoardView parent;
    int currentPlayerColor;

    public BoardModel(Board board) {
        this.board=board;
        currentPlayerColor=PieceCodes.white;
    }
    //TODO: Okay, all right, this should be changed, I don't want to deal with it right now.
    // Some kind of emitter magic or something. It stays for now
    public void setParent(BoardView parent) {
        this.parent=parent;
    }


    public void select(int x, int y) {
        if(board.selected()) {
            if(board.validMove(x, y)) {
                board.move(x,y);
                parent.move(x, y);
                currentPlayerColor = PieceCodes.isWhite(currentPlayerColor) ? PieceCodes.black : PieceCodes.white;
            }
            else {
                board.deselect();
                parent.deselect();
            }
        }
        else if(board.validSelect(x, y, currentPlayerColor)) {
            List<Integer> toHighlight = board.select(x, y);
            parent.select(x, y, toHighlight);
        }
    }
    public Pieces.Piece pieceAt(int x, int y) {
        return board.pieceAt(x, y);
    }
}
