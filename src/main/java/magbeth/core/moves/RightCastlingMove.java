package magbeth.core.moves;

import magbeth.core.Board;
import magbeth.core.IGameState;
import magbeth.core.Vec2;

public class RightCastlingMove extends CastlingMove {
    public RightCastlingMove() {
        super(0, 2);
    }

    @Override
    public boolean canApply(IGameState gameState) {
        IGameState.PlayerInfo playerInfo = gameState.getPlayerInfos().get(piece.getColor());
        if (!super.canApply(gameState) || playerInfo.isRightRookMoved)
            return false;
        for (int i = Board.SIZE - 3; i < Board.SIZE - 1; i++)
            if (gameState.pieceAt(new Vec2(Board.getMainRow(playerInfo.player), i)) != null)
                return false;
        for (int i = Board.SIZE - 4; i <= Board.SIZE - 2; i++)
            if (gameState.isUnderAttack(new Vec2(Board.getMainRow(playerInfo.player), i), null, playerInfo.player))
                return false;
        return true;
    }

    @Override
    public void apply(IGameState gameState) {
        IGameState.PlayerInfo playerInfo = gameState.getPlayerInfos().get(piece.getColor());
        gameState.getBoard().movePeace(new Vec2(Board.getMainRow(playerInfo.player), 7), new Vec2(Board.getMainRow(playerInfo.player), 5));
        super.apply(gameState);
    }

    @Override
    public RightCastlingMove copy() {
        return new RightCastlingMove();
    }
}
