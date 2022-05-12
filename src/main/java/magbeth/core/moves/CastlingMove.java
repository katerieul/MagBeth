package magbeth.core.moves;

import magbeth.core.IGameState;

public abstract class CastlingMove extends PeaceMove {
    public CastlingMove(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean canApply(IGameState gameState) {
        IGameState.PlayerInfo playerInfo = gameState.getPlayerInfos().get(piece.getColor());
        return !playerInfo.isKingMoved;
    }
}