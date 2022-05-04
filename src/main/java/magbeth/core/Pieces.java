package magbeth.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Pieces {

    public static final Map<Integer, Class<? extends Piece>> pieceToClass = Map.of(
            PieceCodes.pawn, Pawn.class,
            PieceCodes.knight, Knight.class,
            PieceCodes.bishop, Bishop.class,
            PieceCodes.rook, Rook.class,
            PieceCodes.queen, Queen.class,
            PieceCodes.king, King.class
    );
    public static Piece makePiece(int code) {
        int type=PieceCodes.getType(code);
        int colour=PieceCodes.getColour(code);
        Class<? extends Piece> resClass = pieceToClass.get(Integer.valueOf(type));
        Piece result=null;
        try {
            Constructor ct = resClass.getDeclaredConstructor(int.class);
            result = resClass.getDeclaredConstructor(int.class).newInstance(code);
        }
        catch(Exception e) {
            System.out.println("could not create");
            System.out.println(type);
            System.out.println(e);

            Constructor [] cts = resClass.getDeclaredConstructors();
            for(Constructor c : cts) {
                Class<?>[] types = c.getParameterTypes();
                System.out.println(types.length);
                for(Class<?> cl : types)
                    System.out.println(cl.getName());
                System.out.println();
            }
        }
        return result;
    }
    public static abstract class Piece {
        int pieceCode;

        int moveUnit() {
            return PieceCodes.isWhite(pieceCode) ? -1 : 1;
        }
        public Piece() { }
        public Piece(int pieceCode) {
            this.pieceCode=pieceCode;
        }
        public int getPieceCode() {
            return pieceCode;
        }
        public int getColor() { return PieceCodes.getColour(pieceCode); }

        // List of pairs of pairs of legal moves: we keep pairs of (bool single, pair<int, int> coordinates)
        // The moves are grouped by direction
        // can the move be made more than once (ie: are there more than one moves achieved by repeating the formula)
        // change of positions relative to (i-1)-th move (or the current position)
        public boolean repeat;
        // List of moves that can always be made, provided there are no pieces that are ours on our path
        List<Move> basicMoves;

        //TODO: The next two functions return moves in an absolute sense (coordinates of the squares),
        // the basicMoves^ List contains moves relative to current square
        // (something should probably be done to reflect that)
        List<Integer> extraMoves(Board b, int x, int y) {
            return null;
        };

        public List<Integer> legalMoves(Board board, int a, int b) {
            List<Integer> result = extraMoves(board, a, b);
            if(result==null)
                result = new ArrayList<>();
            System.out.println("coordinates "+a+" "+b);
            for(int i=0; i<basicMoves.size(); i++) {
                int x=a+basicMoves.get(i).x;
                int y=b+basicMoves.get(i).y;
                while(board.validIndex(x,y)) {
                    System.out.println("checking "+x+" "+y+" moveunit "+moveUnit());
                    Piece potentialMove = board.pieceAt(x, y);
                    if(potentialMove==null || !PieceCodes.sameColour(potentialMove.getPieceCode(), pieceCode)) {
                        result.add(Board.getIndex(x, y));
                        if (repeat) {
                            x += basicMoves.get(i).x;
                            y += basicMoves.get(i).y;
                            continue;
                        }
                    }
                    break;
                }
            }
            System.out.println(result.size());
            return result;
        }
    }
    public static class Pawn extends Piece {
        public boolean firstMove;
        //public boolean enPassant = false;
        public boolean justMoved;
        public Pawn(int pieceCode) {
            super(pieceCode);
            firstMove = false;
            justMoved = false;
            basicMoves = new ArrayList<>();
            repeat = false;
            basicMoves = Arrays.asList(new Move(0, moveUnit()));
        }
        @Override
        List<Integer> extraMoves(Board b, int x, int y) {
            List<Integer> result = new ArrayList<>();
            if(!justMoved) {
                result.add(Board.getIndex(x, y + 2*moveUnit()));
                justMoved = true;
            }
            Piece potentialCapture=b.pieceAt(x+moveUnit(), y+moveUnit());
            if(potentialCapture!=null && potentialCapture.getColor() == 8 - this.getColor())
                result.add(Board.getIndex(x+moveUnit(), x+moveUnit()));

            potentialCapture=b.pieceAt(x-moveUnit(), y+moveUnit());
            if(potentialCapture!=null && potentialCapture.getColor() == 8 - this.getColor())
                result.add(Board.getIndex(x-moveUnit(), y+moveUnit()));

            return result;
        }
    }

    public static class Knight extends Piece {
        public Knight(int pieceCode) {
            super(pieceCode);
            Boolean[] r = new Boolean[8];
            Arrays.fill(r, false);
            repeat = false;
            basicMoves = new ArrayList<>();
            for (int i = -1; i < 2; i += 2) {
                for (int j = -1; j < 2; j += 2) {
                    for (int k = 0; k < 2; k++) {
                        Move toAdd = new Move(i * (1 + k), j * (1 + (k + 1) % 2));
                        basicMoves.add(toAdd);
                    }
                }
            }
        }
    }

    public static class Bishop extends Piece {
        public Bishop(int pieceCode) {
            super(pieceCode);
            Boolean[] r = new Boolean[8];
            Arrays.fill(r, true);
            repeat=true;

            basicMoves = new ArrayList<>();
            for(int i=-1; i<2; i++) {
                for(int j=-1; j<2; j++) {
                    if(i!=0 && j!=0)
                        basicMoves.add(new Move(i, j));
                }
            }
        }
    }
    public static class Rook extends Piece {
        public boolean hasMoved = false;
        public Rook(int pieceCode) {
            super(pieceCode);
            Boolean[] r = new Boolean[8];
            Arrays.fill(r, true);
            repeat = true;
            basicMoves = new ArrayList<>();
            for(int i=-1; i<2; i++) {
                for(int j = -1; j < 2; j++)
                    if(j == 0 || i == 0) basicMoves.add(new Move(i, j));
            }
        }
    }
    public static class Queen extends Piece {
        public Queen(int pieceCode) {
            super(pieceCode);
            Boolean[] r = new Boolean[8];
            Arrays.fill(r, true);

            repeat=true;
            basicMoves = new ArrayList<>();
            basicMoves.add(new Move(0, moveUnit()));
            basicMoves.add(new Move(0, -moveUnit()));
            basicMoves.add(new Move(moveUnit(), moveUnit()));
            basicMoves.add(new Move(moveUnit(), -moveUnit()));
            basicMoves.add(new Move(-moveUnit(), moveUnit()));
            basicMoves.add(new Move(-moveUnit(), -moveUnit()));
            basicMoves.add(new Move(moveUnit(),0));
            basicMoves.add(new Move(-moveUnit(),0));
        }
    }

    public static class King extends Piece {
        public boolean hasMoved = false;
        public boolean underCheck = false;
        public King(int pieceCode) {
            super(pieceCode);
            basicMoves = new ArrayList<>();
            repeat = false;

            basicMoves.add(new Move(0, moveUnit()));
            basicMoves.add(new Move(0, -moveUnit()));
            basicMoves.add(new Move(moveUnit(), moveUnit()));
            basicMoves.add(new Move(moveUnit(), -moveUnit()));
            basicMoves.add(new Move(-moveUnit(), moveUnit()));
            basicMoves.add(new Move(-moveUnit(), -moveUnit()));
            basicMoves.add(new Move(moveUnit(),0));
            basicMoves.add(new Move(-moveUnit(),0));
        }
    }

}
