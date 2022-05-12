package magbeth.core;

import javafx.scene.image.Image;

import java.util.Objects;

public class Piece {
    public enum Color {
        BLACK,
        WHITE;

        public Color opposite() {
            if (this == BLACK)
                return WHITE;
            else
                return BLACK;
        }
    };

    private final PieceType type;
    private final Color color;

    Piece(PieceType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    private String getImageID() {
        return type.getImageID() + "_" + color.toString().toLowerCase();
    }

    public Vec2 move(Vec2 start, Vec2 diff) {
        if (color == Color.WHITE)
            diff = new Vec2(-diff.row, diff.col);
        return start.add(diff);
    }

    public Image getImage() {
        String path = "/images/" + getImageID() + ".png";
        return new Image(Objects.requireNonNull(getClass().getResource(path)).toString());
    }
}
