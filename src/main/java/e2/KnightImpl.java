package e2;

public class KnightImpl implements Knight {

    private final Pair<Integer, Integer> position;

    public KnightImpl(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public boolean hit(Pair<Integer, Integer> target) {
        if (target.getX() < 0 || target.getY() < 0) {
            throw new IndexOutOfBoundsException("Point coordinates must be positive");
        }
        int xDiff = position.getY() - target.getX();
        int yDiff = position.getY() - target.getY();
        return xDiff != 0 && yDiff != 0
                && Math.abs(xDiff) + Math.abs(yDiff) == 3;
    }
}
