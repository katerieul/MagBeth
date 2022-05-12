package magbeth.core;

import magbeth.core.moves.Move;
import magbeth.core.moves.PeaceMove;

import java.util.*;

public abstract class IGameState {
    public class PlayerInfo {
        public boolean isLeftRookMoved = false;
        public boolean isRightRookMoved = false;
        public boolean isKingMoved = false;
        public Move lastMove = null;
        public Piece.Color player;

        public PlayerInfo(PlayerInfo playerInfo) {
            this.isKingMoved = playerInfo.isKingMoved;
            this.isLeftRookMoved = playerInfo.isLeftRookMoved;
            this.isRightRookMoved = playerInfo.isRightRookMoved;
            this.lastMove = playerInfo.lastMove;
            this.player = playerInfo.player;
        }

        public PlayerInfo(Piece.Color player) {
            this.player = player;
        }

        public void processMove(Move move) {
            if (move.piece.getColor() == player) {
                if (move.piece.getType() == PieceType.ROOK) {
                    if (move.tile.equals(new Vec2(Board.getMainRow(player), 0)))
                        isLeftRookMoved = true;
                    else if (move.tile.equals(new Vec2(Board.getMainRow(player), Board.SIZE - 1)))
                        isRightRookMoved = true;
                } else if (move.piece.getType() == PieceType.KING)
                    if (move.tile.equals(new Vec2(Board.getMainRow(player), 4)))
                        isKingMoved = true;
            } else {
                Piece piece2 = pieceAt(move.getFinalTile());
                if (piece2 != null && piece2.getType() == PieceType.ROOK) {
                    if (move.getFinalTile().equals(new Vec2(Board.getMainRow(player), 0)))
                        isLeftRookMoved = true;
                    else if (move.getFinalTile().equals(new Vec2(Board.getMainRow(player), Board.SIZE - 1)))
                        isRightRookMoved = true;
                }
            }

            lastMove = move;
        }
    }

    protected Board board;
    protected Piece.Color currentPlayer;
    protected HashMap<Piece.Color, PlayerInfo> playerInfos;

    /* Piece type is King or null. Attack does not check outcome */
    public abstract boolean isUnderAttack(PieceType pieceType, Piece.Color player);

    public abstract boolean isUnderAttack(Vec2 tile, PieceType pieceType, Piece.Color player);

    public Piece.Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isFriendlyPiece(Vec2 cell) {
        return pieceAt(cell) != null && pieceAt(cell).getColor() == currentPlayer;
    }

    private PlayerInfo getCurrentPlayerInfo() {
        return playerInfos.get(currentPlayer);
    }

    public List<Move> getAvailableMoves(Vec2 tile) {
        return getAvailableMoves(tile, currentPlayer);
    }

    protected List<Move> getAvailableAttackMoves(Vec2 tile, Piece.Color color) {
        Piece piece = pieceAt(tile);
        if (piece == null || piece.getColor() != color)
            return new ArrayList<>();
        return MoveGenerator.of(this, piece, tile, move -> !(move instanceof PeaceMove));
    }

    protected List<Move> getAvailableMoves(Vec2 tile, Piece.Color color) {
        Piece piece = pieceAt(tile);
        if (piece == null || piece.getColor() != color)
            return new ArrayList<>();
        return MoveGenerator.of(this, piece, tile, move -> true);
    }

    public void makeMove(Move move) {
        move.apply(this);
        for (PlayerInfo playerInfo : playerInfos.values())
            playerInfo.processMove(move);
        this.currentPlayer = currentPlayer.opposite();
    }

    public boolean canMove(Vec2 start, Vec2 end) {
        return getAvailableMoves(start, currentPlayer).stream().anyMatch(x -> x.getFinalTile().equals(end));
    }

    public Move findMove(Vec2 start, Vec2 end) {
        return getAvailableMoves(start, currentPlayer).stream().filter(x -> x.getFinalTile().equals(end)).findFirst().orElseThrow();
    }

    public HashMap<Piece.Color, PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }


    public Piece pieceAt(Vec2 tile) {
        return board.pieceAt(tile);
    }

    public Board getBoard() {
        return board;
    }
}