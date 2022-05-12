package magbeth.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magbeth.core.Piece;
import magbeth.core.Vec2;

public class Tile {
    public enum State {
        STANDARD(new MonoColor(new Color[]{
                Color.rgb(50, 80, 150),
                Color.rgb(240, 210, 0)
                })),
        HIGHLIGHT(new MonoColor(new Color[]{
                Color.rgb(160, 200, 250),
                Color.rgb(246, 246, 105),
                })),
        RED(new MonoColor(new Color[]{
                Color.rgb(50, 80, 150).interpolate(Color.RED, .5),
                Color.rgb(240, 210, 0).interpolate(Color.RED, .5)
                })),
        ACTION(new MonoColor(new Color[]{
                Color.rgb(50, 80, 150).interpolate(Color.DARKVIOLET, .5),
                Color.rgb(240, 210, 0).interpolate(Color.DARKVIOLET, .5)
                }));

        private final MonoColor color;

        State(MonoColor color) {
            this.color = color;
        }

        public Color getColor(boolean light) {
            return color.getColor(light);
        }
    }

    private Piece piece = null;
    private State state = State.STANDARD;
    private final GraphicsContext graphicsContext;
    private final Vec2 pos;
    private Rect rect;

    public Tile(Vec2 pos, GraphicsContext graphicsContext) {
        this.pos = pos;
        this.graphicsContext = graphicsContext;
    }

    private void draw() {
        graphicsContext.setFill(state.getColor(pos.light()));
        graphicsContext.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        if (piece != null)
            graphicsContext.drawImage(piece.getImage(), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public void setState(State state) {
        this.state = state;
        draw();
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        draw();
    }

    public void setRect(Rect rect) {
        this.rect = rect;
        draw();
    }
}
