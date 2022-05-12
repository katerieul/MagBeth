package magbeth.core.moves;

import magbeth.core.Vec2;

public class RightEnPassantMove extends EnPassantMove {
    public RightEnPassantMove() {
        super(new Vec2(1, 1));
    }

    @Override
    public RightEnPassantMove copy() {
        return new RightEnPassantMove();
    }
}