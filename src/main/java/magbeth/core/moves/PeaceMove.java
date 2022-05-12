package magbeth.core.moves;

import magbeth.core.IGameState;

public class PeaceMove extends Move {
    public PeaceMove(int row, int col) {
    super(row, col);
}

    @Override
    public boolean canApply(IGameState gameState) {
        return super.canApply(gameState) && gameState.pieceAt(getFinalTile()) == null;
    }

    @Override
    public PeaceMove copy() {
        return new PeaceMove(move.row, move.col);
    }
}
