package e3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final Random rand = new Random();
    private final Set<Cell> mines;

    public LogicsImpl(Set<Cell> mines) {
        this.mines = mines;
    }

    public LogicsImpl(int size) {
        mines = new HashSet<>();
        for (int i = 0; i < size; i++) {
            mines.add(getNewRandomMine(size));
        }
    }

    private Cell getNewRandomMine(int bound) {
        Pair<Integer, Integer> randomPosition = new Pair<>(rand.nextInt(bound), rand.nextInt(bound));
        Cell newMine = new Cell(randomPosition);
        return !mines.contains(newMine) ? newMine : getNewRandomMine(bound);
    }

    @Override
    public boolean isAMine(Cell cell) {
        return mines.contains(cell);
    }

    @Override
    public int getAdjacentMines(Cell cell) {
        return (int) mines.stream()
                .filter(cell::isAdjacent)
                .count();
    }
}
