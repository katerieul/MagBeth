package magbeth.core;

import java.util.HashMap;

public class PredictedGameState extends IGameState {
    @Override
    public boolean isUnderAttack(PieceType pieceType, Piece.Color player) {
        for (Vec2 tile : board)
            if (isUnderAttack(tile, pieceType, player))
                return true;
        return false;
    }

    @Override
    public boolean isUnderAttack(Vec2 tile, PieceType pieceType, Piece.Color player) {
        Piece piece = pieceAt(tile);
        if (piece == null && pieceType != null)
            return false;
        if (piece != null && (piece.getColor() != player || piece.getType() != pieceType))
            return false;
        for (Vec2 cell : board)
            if (getAvailableAttackMoves(cell, player.opposite()).stream().anyMatch(x -> x.getFinalTile().equals(tile)))
                return true;
        return false;
    }

    public PredictedGameState(BasicGameState state) {
        this.board = new Board(state.getBoard());
        this.currentPlayer = state.getCurrentPlayer();
        this.playerInfos = new HashMap<>() {{
            put(Piece.Color.BLACK, new PlayerInfo(state.playerInfos.get(Piece.Color.BLACK)));
            put(Piece.Color.WHITE, new PlayerInfo(state.playerInfos.get(Piece.Color.WHITE)));
        }};
    }
}