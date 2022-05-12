package magbeth.core.moves;

import magbeth.core.IGameState;

public class AttackMove extends Move {
    public AttackMove(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canApply(IGameState gameState) {
        return super.canApply(gameState) && gameState.pieceAt(getFinalTile()) != null;
    }

    @Override
    public AttackMove copy() {
        return new AttackMove(move.row, move.col);
    }
}
