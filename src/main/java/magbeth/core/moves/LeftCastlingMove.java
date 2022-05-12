package magbeth.core.moves;

import magbeth.core.Board;
import magbeth.core.IGameState;
import magbeth.core.Vec2;

public class LeftCastlingMove extends CastlingMove {
    public LeftCastlingMove() {
        super(0, -2);
    }

    @Override
    public boolean canApply(IGameState gameState) {
        IGameState.PlayerInfo playerInfo = gameState.getPlayerInfos().get(piece.getColor());
        if (!super.canApply(gameState) || playerInfo.isLeftRookMoved)
            return false;
        for (int i = 1; i < 4; i++)
            if (gameState.pieceAt(new Vec2(Board.getMainRow(playerInfo.player), i)) != null)
                return false;
        for (int i = 0; i <= 2; i++)
            if (gameState.isUnderAttack(new Vec2(Board.getMainRow(playerInfo.player), i), null, playerInfo.player))
                return false;
        return true;
    }

    @Override
    public void apply(IGameState gameState) {
        IGameState.PlayerInfo playerInfo = gameState.getPlayerInfos().get(piece.getColor());
        gameState.getBoard().movePeace(new Vec2(Board.getMainRow(playerInfo.player), 0), new Vec2(Board.getMainRow(playerInfo.player), 3));
        super.apply(gameState);
    }

    @Override
    public LeftCastlingMove copy() {
        return new LeftCastlingMove();
    }
}
