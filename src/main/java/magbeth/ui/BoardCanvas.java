package magbeth.ui;

import javafx.beans.binding.NumberBinding;
import javafx.scene.canvas.Canvas;
import magbeth.core.Board;
import magbeth.core.Piece;
import magbeth.core.Vec2;

public class BoardCanvas extends Canvas {
    public interface OnClickListener {
        void click(Vec2 tile);
    }

    private NumberBinding sizeBinding;
    private OnClickListener onClickListener;
    private final Tile[][] tiles = new Tile[Board.SIZE][Board.SIZE];

    public BoardCanvas() {
        for (int row = 0; row < Board.SIZE; row++)
            for (int col = 0; col < Board.SIZE; col++)
                tiles[row][col] = new Tile(new Vec2(row, col), getGraphicsContext2D());
        setOnMouseClicked(mouseEvent -> onClickListener.click(getTile(mouseEvent.getX(), mouseEvent.getY())));
         //TODO: implement drag and drop behavior
        //setOnDragDetected();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }

    public void setSizeBinding(NumberBinding sizeBinding) {
        this.sizeBinding = sizeBinding;
        for (int row = 0; row < Board.SIZE; row++)
            for (int col = 0; col < Board.SIZE; col++)
                tiles[row][col].setRect(getTileRect(new Vec2(row, col)));
        sizeBinding.addListener((observableValue, number, t1) -> updateSize());
    }

    private float getTileSize() {
        return sizeBinding.floatValue() / Board.SIZE;
    }

    public Vec2 getTile(double x, double y) {
        return new Vec2((int) ((y * 8) / sizeBinding.doubleValue()), (int) ((x * 8) / sizeBinding.doubleValue()));
    }

    private Rect getTileRect(Vec2 tile) {
        return new Rect(
                tile.col * getTileSize(),
                tile.row * getTileSize(),
                getTileSize(),
                getTileSize());
    }

    public void setPiece(Vec2 tile, Piece piece) {
        tiles[tile.row][tile.col].setPiece(piece);
    }

    public void setTileState(Vec2 tile, Tile.State state) {
        tiles[tile.row][tile.col].setState(state);
    }

    private void updateSize() {
        for (int row = 0; row < Board.SIZE; row++)
            for (int col = 0; col < Board.SIZE; col++)
                tiles[row][col].setRect(getTileRect(new Vec2(row, col)));
    }
}
