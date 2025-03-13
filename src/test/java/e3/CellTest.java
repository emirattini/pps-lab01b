package e3;

import e3.grid.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

    public static final Pair<Integer, Integer> OUT_OF_BOUND_POSITION = new Pair<>(-1, -1);
    public static final Pair<Integer, Integer> CELL_POSITION = new Pair<>(0, 0);
    public static final Pair<Integer, Integer> ADJACENT_POSITION = new Pair<>(1, 1);
    public static final Pair<Integer, Integer> NOT_ADJACENT_POSITION = new Pair<>(2, 2);

    private Cell cell;

    @BeforeEach
    void setUp() {
        cell = new Cell(CELL_POSITION);
    }

    @Test
    void testNotTwoEqualCellsInTheSameSet() {
        Cell cell1 = new Cell(CELL_POSITION);
        Cell cell2 = new Cell(CELL_POSITION);
        HashSet<Cell> mines = new HashSet<>();
        mines.add(cell1);
        mines.add(cell2);
        int expectedSize = 1;
        assertEquals(expectedSize, mines.size());
    }

    @Test
    void testOutOfBoundCell() {
        assertThrows(IndexOutOfBoundsException.class, () -> cell = new Cell(OUT_OF_BOUND_POSITION));
    }

    @Test
    void testNotAdjacentCell() {
        assertFalse(cell.isAdjacent(new Cell(NOT_ADJACENT_POSITION)));
    }

    @Test
    void testAdjacentCell() {
        assertTrue(cell.isAdjacent(new Cell(ADJACENT_POSITION)));
    }
}
