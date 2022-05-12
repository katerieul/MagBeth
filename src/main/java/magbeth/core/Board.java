package magbeth.core;

import java.util.Iterator;

public class Board implements Iterable<Vec2> {
    public static final int SIZE = 8;
    private final Piece[][] pieces = new Piece[SIZE][SIZE];

    public Board(Board other) {
        for (Vec2 tile : this)
            pieces[tile.row][tile.col] = other.pieceAt(tile);
    }

    public Board() {

    }

    public static boolean isValidVec(Vec2 vec) {
        return 0 <= vec.row && vec.row < Board.SIZE && 0 <= vec.col && vec.col < Board.SIZE;
    }

    public static Board basicConfig() {
        Board board = new Board();

        board.pieces[getMainRow(Piece.Color.BLACK)] = new Piece[]{
                new Piece(PieceType.ROOK, Piece.Color.BLACK),
                new Piece(PieceType.KNIGHT, Piece.Color.BLACK),
                new Piece(PieceType.BISHOP, Piece.Color.BLACK),
                new Piece(PieceType.QUEEN, Piece.Color.BLACK),
                new Piece(PieceType.KING, Piece.Color.BLACK),
                new Piece(PieceType.BISHOP, Piece.Color.BLACK),
                new Piece(PieceType.KNIGHT, Piece.Color.BLACK),
                new Piece(PieceType.ROOK, Piece.Color.BLACK),
        };
        for (int col = 0; col < SIZE; col++)
            board.pieces[getPawnRow(Piece.Color.BLACK)][col] = new Piece(PieceType.PAWN, Piece.Color.BLACK);

        board.pieces[getMainRow(Piece.Color.WHITE)] = new Piece[]{
                new Piece(PieceType.ROOK, Piece.Color.WHITE),
                new Piece(PieceType.KNIGHT, Piece.Color.WHITE),
                new Piece(PieceType.BISHOP, Piece.Color.WHITE),
                new Piece(PieceType.QUEEN, Piece.Color.WHITE),
                new Piece(PieceType.KING, Piece.Color.WHITE),
                new Piece(PieceType.BISHOP, Piece.Color.WHITE),
                new Piece(PieceType.KNIGHT, Piece.Color.WHITE),
                new Piece(PieceType.ROOK, Piece.Color.WHITE),
        };
        for (int col = 0; col < SIZE; col++)
            board.pieces[getPawnRow(Piece.Color.WHITE)][col] = new Piece(PieceType.PAWN, Piece.Color.WHITE);

        return board;
    }

    public void setPiece(Vec2 tile, Piece piece) {
        pieces[tile.row][tile.col] = piece;
    }

    public Piece pieceAt(Vec2 tile) {
        return pieces[tile.row][tile.col];
    }

    public void movePeace(Vec2 start, Vec2 end) {
        setPiece(end, pieceAt(start));
        setPiece(start, null);
    }

    public static int getPawnRow(Piece.Color color) {
        return color == Piece.Color.BLACK ? 1 : (SIZE - 2);
    }

    public static int getMainRow(Piece.Color color) {
        return color == Piece.Color.BLACK ? 0 : (SIZE - 1);
    }

    @Override
    public Iterator<Vec2> iterator() {
        return new Iterator<>() {
            int row = 0;
            int col = 0;

            @Override
            public boolean hasNext() {
                return row != SIZE;
            }

            @Override
            public Vec2 next() {
                Vec2 tile = new Vec2(row, col);
                col++;
                if (col == SIZE) {
                    col = 0;
                    row++;
                }
                return tile;
            }
        };
    }
}
