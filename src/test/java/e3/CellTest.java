package e3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
