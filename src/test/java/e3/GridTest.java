package e3;

import e3.grid.Cell;
import e3.grid.Grid;
import e3.grid.GridImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    private static final int GRID_SIZE = 5;
    private static final Pair<Integer, Integer> OUT_OF_BOUND = new Pair<>(GRID_SIZE + 1, GRID_SIZE + 1);
    private static final Pair<Integer, Integer> ON_THE_CORNER = new Pair<>(0, 0);
    private static final Pair<Integer, Integer> MINE_POSITION = new Pair<>(1, 1);
    private static final Pair<Integer, Integer> FLAG_POSITION = new Pair<>(1, 1);

    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new GridImpl(GRID_SIZE);
    }

    @Test
    void testAddMineOutOfBound() {
        Cell outOfBoundCell = new Cell(OUT_OF_BOUND);
        assertThrows(IndexOutOfBoundsException.class, () -> grid.addMine(outOfBoundCell));
    }

    @Test
    void testAddMineAndCheck() {
        Cell mine = new Cell(MINE_POSITION);
        grid.addMine(mine);
        assertTrue(grid.isMined(mine));
    }

    @Test
    void testGetAdjacentMines() {
        grid.addMine(new Cell(MINE_POSITION));
        int numberOfMines = 1;
        assertEquals(numberOfMines, grid.getAdjacentMines(new Cell(ON_THE_CORNER)));
    }

    @Test
    void testAddFlagOutOfBound() {
        Cell outOfBoundCell = new Cell(OUT_OF_BOUND);
        assertThrows(IndexOutOfBoundsException.class, () -> grid.addFlag(outOfBoundCell));
    }

    @Test
    void testAddFlagAndCheck() {
        Cell flag = new Cell(FLAG_POSITION);
        grid.addFlag(flag);
        assertTrue(grid.isFlagged(flag));
    }

    @Test
    void testRemoveFlagAndCheck() {
        Cell flag = new Cell(FLAG_POSITION);
        grid.addFlag(flag);
        grid.removeFlag(flag);
        assertFalse(grid.isFlagged(flag));
    }

    @Test
    void testAddFlagAndGetFlaggedNumber() {
        grid.addFlag(new Cell(FLAG_POSITION));
        int flaggedNumber = 1;
        assertEquals(flaggedNumber, grid.getFlaggedNumber());
    }

    @Test
    void testDigOutOfBound() {
        Cell outOfBoundCell = new Cell(OUT_OF_BOUND);
        assertThrows(IndexOutOfBoundsException.class, () -> grid.dig(outOfBoundCell));
    }

    @Test
    void testDigAndCheck() {
        grid.dig(new Cell(ON_THE_CORNER));
        assertTrue(grid.isDug(new Cell(ON_THE_CORNER)));
    }

    @Test
    void testGetAdjacentCells() {
        Cell cell = new Cell(ON_THE_CORNER);
        Cell adjacentCell1 = new Cell(new Pair<>(ON_THE_CORNER.getX() + 1, ON_THE_CORNER.getY()));
        Cell adjacentCell2 = new Cell(new Pair<>(ON_THE_CORNER.getX(), ON_THE_CORNER.getY() + 1));
        Cell adjacentCell3 = new Cell(new Pair<>(ON_THE_CORNER.getX() + 1, ON_THE_CORNER.getY() + 1));
        Cell notAdjacentCell = new Cell(new Pair<>(ON_THE_CORNER.getX() + 2, ON_THE_CORNER.getY() + 2));

        Set<Cell> adjacentCells = grid.getAdjacentCells(cell);
        assertTrue(adjacentCells.containsAll(Set.of(adjacentCell1, adjacentCell2, adjacentCell3)));
        assertFalse(adjacentCells.contains(notAdjacentCell));
    }
}
