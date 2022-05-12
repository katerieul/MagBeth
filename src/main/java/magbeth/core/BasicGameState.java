package magbeth.core;

import magbeth.core.moves.Move;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class BasicGameState extends IGameState {
    public BasicGameState(BasicGameState gameState) {
        this.board = gameState.board;
        this.currentPlayer = gameState.currentPlayer;
        this.playerInfos = new HashMap<>() {{
            put(Piece.Color.BLACK, new PlayerInfo(gameState.playerInfos.get(Piece.Color.BLACK)));
            put(Piece.Color.WHITE, new PlayerInfo(gameState.playerInfos.get(Piece.Color.WHITE)));
        }};
    }

    public BasicGameState(Board board) {
        this.board = board;
        this.currentPlayer = Piece.Color.WHITE;
        this.playerInfos = new HashMap<>() {{
            put(Piece.Color.BLACK, new PlayerInfo(Piece.Color.BLACK));
            put(Piece.Color.WHITE, new PlayerInfo(Piece.Color.WHITE));
        }};
    }

    public boolean isFriendlyKingUnderAttack(Vec2 tile) {
        return isUnderAttack(tile, PieceType.KING, currentPlayer);
    }

    @Override
    public List<Move> getAvailableMoves(Vec2 tile, Piece.Color color) {
        return super.getAvailableMoves(tile, color).stream().filter(Predicate.not(this::isEndMove)).toList();
    }

    @Override
    public boolean isUnderAttack(PieceType pieceType, Piece.Color player) {
        return new PredictedGameState(this).isUnderAttack(pieceType, player);
    }

    @Override
    public boolean isUnderAttack(Vec2 tile, PieceType pieceType, Piece.Color player) {
        return new PredictedGameState(this).isUnderAttack(tile, pieceType, player);
    }

    private boolean isEndMove(Move move) {
        PredictedGameState predictedGame = new PredictedGameState(this);
        predictedGame.makeMove(move);
        return predictedGame.isUnderAttack(PieceType.KING, currentPlayer);
    }
}
