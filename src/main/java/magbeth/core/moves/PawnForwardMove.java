package magbeth.core.moves;

import magbeth.core.Board;
import magbeth.core.IGameState;
import magbeth.core.Vec2;

public class PawnForwardMove extends PeaceMove {
    public PawnForwardMove() {
        super(2, 0);
    }

    @Override
    public boolean canApply(IGameState gameState) {
        return super.canApply(gameState) &&
                tile.row == Board.getPawnRow(piece.getColor()) &&
                gameState.pieceAt(piece.move(tile, new Vec2(1, 0))) == null;
    }

    @Override
    public PawnForwardMove copy() {
        return new PawnForwardMove();
    }
}
