package magbeth.core;

import java.util.List;
import java.util.Objects;

public class Vec2 {
    public int row;
    public int col;

    public Vec2(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(row + other.row, col + other.col);
    }

    public Vec2 mult(int i) {
        return new Vec2(row * i, col * i);
    }

    public boolean light() {
        return (row + col) % 2 == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2 vec2 = (Vec2) o;
        return row == vec2.row && col == vec2.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return List.of("a", "b", "c", "d", "e", "f", "g", "h").get(col) + (8 - row);
    }
}
