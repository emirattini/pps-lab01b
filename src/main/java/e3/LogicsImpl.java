package e3;

import e3.grid.Cell;
import e3.grid.Grid;
import e3.grid.GridImpl;

import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final Random rand = new Random();
    private final Grid grid;

    public LogicsImpl(Set<Cell> mines) {
        grid = new GridImpl(mines.size());
        mines.forEach(grid::addMine);
    }

    public LogicsImpl(int size) {
        grid = new GridImpl(size);
        for (int i = 0; i < size; i++) {
            grid.addMine(getNewRandomMine(size));
        }
    }

    private Cell getNewRandomMine(int bound) {
        Pair<Integer, Integer> randomPosition = new Pair<>(rand.nextInt(bound), rand.nextInt(bound));
        Cell newMine = new Cell(randomPosition);
        return !grid.isAMine(newMine) ? newMine : getNewRandomMine(bound);
    }

    @Override
    public boolean isAMine(Cell cell) {
        return grid.isAMine(cell);
    }

    @Override
    public int getAdjacentMines(Cell cell) {
        return grid.getAdjacentMines(cell);
    }
}
