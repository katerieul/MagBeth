package magbeth.core;
import java.util.Arrays;
import java.util.List;

public class Board {
    //FEN - what will it be? some kind of dictionary
    Pieces.Piece[] board = new Pieces.Piece[Constants.BOARDSIZE*Constants.BOARDSIZE];
    Integer selectedField=null;
    List<Integer> validMoves;
    public Board(String FEN) {
        //TODO: Add exceptions when FEN is not valid

        int i=0, j=0;
        for(int stringIterator = 0; stringIterator<FEN.length() && i< Constants.BOARDSIZE; stringIterator++) {
            char currentSymbol = FEN.charAt(stringIterator);
            if(Character.isAlphabetic(currentSymbol)) {
                //why is this? So that it is consistent with how Gridpane operates. We can change that later if we want to
                board[8*j+i]= Pieces.makePiece(PieceCodes.FENcodeToCode(currentSymbol));
                j++;
            }
            else if(Character.isDigit(currentSymbol))
                j += Integer.valueOf(currentSymbol);
            else {
                i++;
                j = 0;
            }
        }

        //DEBUGGING
        System.out.println(Arrays.deepToString(board));
    }
    public Board() {
        this(Constants.STARTING_POSITION);
    }
    public boolean isEmpty(int x, int y) {
        return board[getIndex(x, y)]==null;
    }
    public Pieces.Piece pieceAt(int x, int y) {
        if(!validIndex(x, y))
            throw new IllegalArgumentException("Invalid index");
        /*if(x<0 || x== Constants.BOARDSIZE || y<0 || y== Constants.BOARDSIZE)
            return null;*/
        else return board[getIndex(x,y)];
    }
    public boolean validIndex(int x, int y) {
        if(x<0 || y<0 || x>=Constants.BOARDSIZE || y>=Constants.BOARDSIZE)
            return false;
        return true;
    }
    public static int getIndex(int x, int y) {
        return x*Constants.BOARDSIZE+y;
    }
    public boolean validMove(int x, int y) {
        int move=getIndex(x, y);
        return validMoves.contains(Integer.valueOf(move));
    }
    public void move(int xTo, int yTo) {
        if(selectedField==null)
            throw new IllegalArgumentException("Piece doesn't exist");
        if(!validIndex(xTo, yTo))
            throw new IllegalArgumentException("Invalid index");
        //TODO: do something if captured
        board[getIndex(xTo,yTo)]=board[selectedField];
        board[selectedField]=null;
        selectedField=null;
    }
    boolean validSelect(int x, int y, int playerColor) {
        if(validIndex(x, y) && !isEmpty(x, y) && pieceAt(x, y).getColor()==playerColor)
            return true;
        return false;
    }
    List<Integer> select(int x, int y) {
        selectedField=getIndex(x, y);
        validMoves=board[selectedField].legalMoves(this, x, y);
        return validMoves;
    }
    void deselect() {
        selectedField=null;
        validMoves=null;
    }
    public boolean selected() {
        return !(selectedField==null);
    }
    //TODO: implement "reset" function (?)
}
