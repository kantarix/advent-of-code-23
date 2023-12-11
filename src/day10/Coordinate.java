package day10;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate left() {
        return new Coordinate(x, y - 1);
    }

    public Coordinate right() {
        return new Coordinate(x, y + 1);
    }

    public Coordinate up() {
        return new Coordinate(x - 1, y);
    }

    public Coordinate down() {
        return new Coordinate(x + 1, y);
    }

    public Coordinate getFirstWay(Character symbol) {
        return switch (symbol) {
            case '|', 'L', 'J' -> new Coordinate(x - 1, y);
            case '-', '7' -> new Coordinate(x, y - 1);
            case 'F' -> new Coordinate(x, y + 1);
            default -> null;
        };
    }

    public Coordinate getSecondWay(Character symbol) {
        return switch (symbol) {
            case '|', '7', 'F' -> new Coordinate(x + 1, y);
            case '-', 'L' -> new Coordinate(x, y + 1);
            case 'J' -> new Coordinate(x, y - 1);
            default -> null;
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return 10000 * x + 100 * y;
    }
}
