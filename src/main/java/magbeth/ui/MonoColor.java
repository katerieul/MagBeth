package magbeth.ui;

import javafx.scene.paint.Color;

public class MonoColor {
    private final Color[] colors;

    public MonoColor(Color[] colors) {
        this.colors = colors;
    }

    public Color getColor(boolean light) {
        return colors[light ? 1 : 0];
    }
}
