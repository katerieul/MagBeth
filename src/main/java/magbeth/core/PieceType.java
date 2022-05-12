package magbeth.core;

import magbeth.core.moves.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum PieceType {
    PAWN(
            new PeaceMove(1, 0),
            new AttackMove(1, -1),
            new AttackMove(1, 1),
            new PawnForwardMove(),
            new LeftEnPassantMove(),
            new RightEnPassantMove()
    ),
    KNIGHT(
            new Move(1, 2),
            new Move(2, 1),
            new Move(-1, 2),
            new Move(2, -1),
            new Move(1, -2),
            new Move(-2, 1),
            new Move(-1, -2),
            new Move(-2, -1)
    ),
    BISHOP(
            new MoveGenerator.RepeatedMove(new Move(1, 1)),
            new MoveGenerator.RepeatedMove(new Move(-1, 1)),
            new MoveGenerator.RepeatedMove(new Move(1, -1)),
            new MoveGenerator.RepeatedMove(new Move(-1, -1))
    ),
    ROOK(
            new MoveGenerator.RepeatedMove(new Move(0, 1)),
            new MoveGenerator.RepeatedMove(new Move(1, 0)),
            new MoveGenerator.RepeatedMove(new Move(0, -1)),
            new MoveGenerator.RepeatedMove(new Move(-1, 0))
    ),
    QUEEN(
            MoveGenerator.getUnitMoves().stream().map(MoveGenerator.RepeatedMove::new).toList().toArray()
    ),
    KING(
            Stream.concat(Arrays.stream(MoveGenerator.getUnitMoves().toArray()),
                    Stream.of(new LeftCastlingMove(), new RightCastlingMove())).toArray()
    );

    private final List<Object> moves;

    PieceType(Object... moves) {
        this.moves = Arrays.stream(moves).toList();
    }

    public List<Object> getMoves() {
        return moves;
    }

    public String getImageID() {
        return this.name();
    }
}
