package e3.grid;

import java.util.HashSet;
import java.util.Set;

public class GridImpl implements Grid {

    private final Set<Cell> mines = new HashSet<>();
    private final Set<Cell> flags = new HashSet<>();
    private final int size;

    public GridImpl(int size) {
        this.size = size;
    }

    @Override
    public void addMine(Cell mine) {
        checkIfOutOfBound(mine);
        mines.add(mine);
    }

    private void checkIfOutOfBound(Cell mine) {
        if (mine.getX() < 0 || mine.getY() < 0
                || mine.getX() > size || mine.getY() > size) {
            throw new IndexOutOfBoundsException("Point coordinates out of bound");
        }
    }

    @Override
    public boolean isAMine(Cell cell) {
        return mines.contains(cell);
    }

    @Override
    public void addFlag(Cell flag) {
        checkIfOutOfBound(flag);
        flags.add(flag);
    }

    @Override
    public boolean isFlagged(Cell cell) {
        return flags.contains(cell);
    }

    @Override
    public int getAdjacentMines(Cell cell) {
        return (int) mines.stream()
                .filter(cell::isAdjacent)
                .count();
    }
}
