package magbeth.core.moves;

import magbeth.core.Board;
import magbeth.core.IGameState;
import magbeth.core.Piece;
import magbeth.core.Vec2;

public class Move {
    public Piece piece;
    public Vec2 tile;
    public Vec2 move;

    public Move(int row, int col) {
        this(new Vec2(row, col));
    }

    public Move(Vec2 move) {
        this.move = move;
    }

    public Vec2 getFinalTile() {
        return this.piece.move(tile, move);
    }

    public boolean canApply(IGameState gameState) {
        Vec2 finalTile = getFinalTile();
        if (!Board.isValidVec(finalTile))
            return false;
        Piece p = gameState.pieceAt(finalTile);
        return p == null || p.getColor() != piece.getColor();
    }

    public void apply(IGameState gameState) {
        gameState.getBoard().movePeace(tile, getFinalTile());
    }

    public Move copy() {
        return new Move(move);
    }

    @Override
    public String toString() {
        return piece + " " + tile + " " + getFinalTile();
    }
}
