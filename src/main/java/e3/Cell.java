package e3;

public class Cell {

    private final Pair<Integer, Integer> position;

    public Cell(Pair<Integer, Integer> position) {
        if (position.getX() < 0 || position.getY() < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.position = position;
    }

    public boolean isAdjacent(Cell cell) {
        return position.getX() >= cell.getX() - 1
                && position.getX() <= cell.getX() + 1
                && position.getY() >= cell.getY() - 1
                && position.getY() <= cell.getY() + 1;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }
}
