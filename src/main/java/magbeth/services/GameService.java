package magbeth.services;

import magbeth.core.*;
import java.util.Map;

public class GameService {
    //TODO: is this necessary?
    Map<Integer, Player> players;
    BoardModel board;

    public BoardModel getBoardState() {
        return board;
    }


    public boolean newGame() {
        this.newGame(Constants.STARTING_POSITION);
        return true;
    }

    public boolean newGame(String FEN) {
        board=new BoardModel(new Board(FEN));
        players= Map.of(
                PieceCodes.white, new Player(PieceCodes.white),
                PieceCodes.black, new Player(PieceCodes.black)
        );
        if(board==null) {
            System.out.println("Board is null");
        }
        else
            System.out.println("Board is not null");
        return true;
    }
}
