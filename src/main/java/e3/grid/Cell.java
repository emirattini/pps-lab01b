package e3.grid;

import e3.Pair;

public record Cell(Pair<Integer, Integer> position) {

    public Cell {
        if (position.getX() < 0 || position.getY() < 0) {
            throw new IndexOutOfBoundsException();
        }
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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Cell
                && position.equals(((Cell) obj).position());
    }
}
