package magbeth.core

import magbeth.core.moves.Move
import spock.lang.Specification

class BasicGameStateTest extends Specification {

    def "should return two available moves for white pawn on a2"() {
        given:
        def board = Board.basicConfig()
        def underTest = new BasicGameState(board)
        def pawn = new Vec2(6, 0)

        when:
        def result = underTest.getAvailableMoves(pawn, Piece.Color.WHITE)

        then:
        result.size() == 2
        result.containsAll([new Move(new Piece(PieceType.PAWN, Piece.Color.WHITE), pawn, new Vec2(5, 0)),
                            new Move(new Piece(PieceType.PAWN, Piece.Color.WHITE), pawn, new Vec2(4, 0))])
    }
}
