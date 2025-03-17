package e3;

import e3.grid.Cell;
import e3.grid.Grid;
import e3.grid.GridImpl;

import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics {

    private final Grid grid;
    private final int numberOfMines;
    private final Random rand = new Random();

    public LogicsImpl(int size, Set<Cell> mines) {
        grid = new GridImpl(size);
        mines.forEach(grid::addMine);
        numberOfMines = mines.size();
    }

    public LogicsImpl(int size) {
        grid = new GridImpl(size);
        IntStream.range(0, size)
                .mapToObj(i -> getNewRandomMine(size))
                .forEach(grid::addMine);
        numberOfMines = size;
    }

    private Cell getNewRandomMine(int bound) {
        Pair<Integer, Integer> randomPosition = new Pair<>(rand.nextInt(bound), rand.nextInt(bound));
        Cell newMine = new Cell(randomPosition);
        return !grid.isMined(newMine) ? newMine : getNewRandomMine(bound);
    }

    @Override
    public boolean isMined(Cell cell) {
        return grid.isMined(cell);
    }

    @Override
    public int getAdjacentMines(Cell cell) {
        return grid.getAdjacentMines(cell);
    }

    @Override
    public void flagClick(Cell cell) {
        if (grid.isFlagged(cell)) {
            grid.removeFlag(cell);
        } else if (grid.getFlaggedNumber() < numberOfMines){
            grid.addFlag(cell);
        }
    }

    @Override
    public boolean isFlagged(Cell cell) {
        return grid.isFlagged(cell);
    }

    @Override
    public void dig(Cell cell) {
        grid.dig(cell);
        if (getAdjacentMines(cell) == 0) {
            Set<Cell> adjacentCells = grid.getAdjacentCells(cell);
            adjacentCells.stream()
                    .filter(Predicate.not(grid::isDug))
                    .forEach(this::dig);
        }
    }

    @Override
    public boolean isDug(Cell cell) {
        return grid.isDug(cell);
    }

    @Override
    public boolean isItAVictory() {
        return grid.isItAVictory();
    }
}
