package magbeth.core;

import magbeth.core.moves.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MoveGenerator {
    record RepeatedMove(Move move) {
    }

    public static List<Move> getUnitMoves() {
        List<Move> moves = new ArrayList<>();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                moves.add(new Move(i, j));
        return moves;
    }

    public static List<Move> of(IGameState state, Piece piece, Vec2 tile, Predicate<Move> predicate) {
        List<Move> items = new ArrayList<>();
        for (Object possibleMove : piece.getType().getMoves()) {
            Move move = possibleMove instanceof RepeatedMove ? ((RepeatedMove) possibleMove).move : (Move) (possibleMove);
            for (int i = 1; i < (possibleMove instanceof RepeatedMove ? 8 : 2); i++) {
                Move newMove = move.copy();
                newMove.move = move.move.mult(i);
                newMove.tile = tile;
                newMove.piece = piece;
                if (predicate.test(move) && newMove.canApply(state)) {
                    items.add(newMove);
                    if (state.pieceAt(newMove.getFinalTile()) != null)
                        break;
                } else
                    break;
            }
        }
        return items;
    }
}