package e2;

public class KnightImpl implements Knight {

    private Pair<Integer, Integer> position;

    public KnightImpl(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public boolean move(Pair<Integer, Integer> target) {
        if (target.getX() < 0 || target.getY() < 0) {
            throw new IndexOutOfBoundsException("Point coordinates must be positive");
        }
        int xDiff = position.getX() - target.getX();
        int yDiff = position.getY() - target.getY();
        boolean isReachable = xDiff != 0 && yDiff != 0
                && Math.abs(xDiff) + Math.abs(yDiff) == 3;
        if (isReachable) {
            this.position = target;
        }
        return isReachable;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }
}
