package e3.grid;

import e3.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GridImpl implements Grid {

    private final Set<Cell> allCells = new HashSet<>();
    private final Set<Cell> mines = new HashSet<>();
    private final Set<Cell> flags = new HashSet<>();
    private final Set<Cell> disabled = new HashSet<>();
    private final int size;

    public GridImpl(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                allCells.add(new Cell(new Pair<>(i, j)));
            }
        }
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
    public boolean isMined(Cell cell) {
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

    @Override
    public void removeFlag(Cell flag) {
        flags.remove(flag);
    }

    @Override
    public int getFlaggedNumber() {
        return flags.size();
    }

    @Override
    public Set<Cell> getAdjacentCells(Cell cell) {
        return allCells.stream()
                .filter(cell::isAdjacent)
                .filter(Predicate.not(cell::equals))
                .collect(Collectors.toSet());
    }

    @Override
    public void dig(Cell cell) {
        checkIfOutOfBound(cell);
        disabled.add(cell);
    }

    @Override
    public boolean isDug(Cell cell) {
        return disabled.contains(cell);
    }
}
