package magbeth.core.moves;

import magbeth.core.Vec2;

public class LeftEnPassantMove extends EnPassantMove {
    public LeftEnPassantMove() {
        super(new Vec2(1, -1));
    }

    @Override
    public LeftEnPassantMove copy() {
        return new LeftEnPassantMove();
    }
}

