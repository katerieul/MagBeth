package magbeth.core;

import magbeth.core.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<BasicGameState> states = new ArrayList<>(List.of(new BasicGameState(Board.basicConfig())));
    private final List<Move> moves = new ArrayList<>();;
    private final StringBuilder logs = new StringBuilder();

    public void makeMove(Move move) {
        BasicGameState gameState = new BasicGameState(getCurrentState());
        gameState.makeMove(move);
        logs.append(move);
        moves.add(move);
        states.add(gameState);
    }

    public String getLogs() {
        return logs.toString();
    }

    public BasicGameState getCurrentState() {
        return states.get(states.size() - 1);
    }
}
