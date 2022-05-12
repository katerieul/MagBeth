package magbeth.views;

import javafx.beans.binding.Bindings;
import magbeth.core.*;
import magbeth.core.moves.Move;
import magbeth.ui.BoardCanvas;
import magbeth.ui.Tile;

public class BoardView extends BoardCanvas {
    private final Game game;
    private Vec2 selected = null;

    public BoardView(Game game) {
        setOnClickListener(this::clicked);
        setSizeBinding(Bindings.min(widthProperty(), heightProperty()));
        this.game = game;
        update();
    }

    private void select(Vec2 tile) {
        deselectAll();
        if (game.getCurrentState().isFriendlyPiece(tile)) {
            selected = tile;
            setTileState(tile, Tile.State.HIGHLIGHT);
            for (Move move : game.getCurrentState().getAvailableMoves(tile))
                setTileState(move.getFinalTile(), Tile.State.HIGHLIGHT);
        }
    }

    private void deselectAll() {
        selected = null;
        for (Vec2 tile : game.getCurrentState().getBoard()) {
            if (game.getCurrentState().isFriendlyKingUnderAttack(tile))
                setTileState(tile, Tile.State.RED);
            else
                setTileState(tile, Tile.State.STANDARD);
        }
    }

    private void update() {
        for (Vec2 tile : game.getCurrentState().getBoard())
            setPiece(tile, game.getCurrentState().pieceAt(tile));
        deselectAll();
    }

    private void clicked(Vec2 tile) {
        if (selected == null)
            select(tile);
        else {
            if (game.getCurrentState().canMove(selected, tile)) {
                game.makeMove(game.getCurrentState().findMove(selected, tile));
                update();
            } else if (game.getCurrentState().isFriendlyPiece(tile)) {
                select(tile);
            } else {
                deselectAll();
            }
        }
    }
}
