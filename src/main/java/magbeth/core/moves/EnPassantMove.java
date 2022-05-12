package magbeth.core.moves;

import magbeth.core.Board;
import magbeth.core.IGameState;
import magbeth.core.PieceType;
import magbeth.core.Vec2;

public abstract class EnPassantMove extends Move {
    public EnPassantMove(Vec2 move) {
        super(move);
    }

    @Override
    public boolean canApply(IGameState gameState) {
        IGameState.PlayerInfo oppositePlayerInfo = gameState.getPlayerInfos().get(piece.getColor().opposite());
        Move lastOpponentMove = oppositePlayerInfo.lastMove;
        return lastOpponentMove != null && lastOpponentMove.piece.getType() == PieceType.PAWN &&
                lastOpponentMove.tile.row == Board.getPawnRow(lastOpponentMove.piece.getColor()) &&
                lastOpponentMove.move.row == 2 &&
                tile.row == lastOpponentMove.getFinalTile().row &&
                tile.col + move.col == lastOpponentMove.tile.col;
    }

    @Override
    public void apply(IGameState gameState) {
        gameState.getBoard().setPiece(new Vec2(tile.row, tile.col + move.col), null);
        super.apply(gameState);
    }
}
