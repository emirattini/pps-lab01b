package e3;

import e3.grid.Cell;
import e3.grid.Grid;
import e3.grid.GridImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    public static final int GRID_SIZE = 5;
    private static final Pair<Integer, Integer> CELL_POSITION = new Pair<>(0, 0);
    private static final Pair<Integer, Integer> MINE_POSITION = new Pair<>(1, 1);
    private static final Pair<Integer, Integer> FLAG_POSITION = new Pair<>(1, 1);

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new GridImpl(GRID_SIZE);
    }

    @Test
    void testAddMineAndCheck() {
        grid.addMine(new Cell(MINE_POSITION));
        assertTrue(grid.isAMine(new Cell(MINE_POSITION)));
    }

    @Test
    void testAddFlagAndCheck() {
        grid.addFlag(new Cell(FLAG_POSITION));
        assertTrue(grid.isFlagged(new Cell(FLAG_POSITION)));
    }

    @Test
    void testGetAdjacentMines() {
        grid.addMine(new Cell(MINE_POSITION));
        int numberOfMines = 1;
        assertEquals(numberOfMines, grid.getAdjacentMines(new Cell(CELL_POSITION)));
    }
}
