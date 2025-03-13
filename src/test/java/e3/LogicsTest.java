package e3;

import e3.grid.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LogicsTest {

    private static final Pair<Integer, Integer> FIRST_MINE_POSITION = new Pair<>(1,1);
    private static final Pair<Integer, Integer> SECOND_MINE_POSITION = new Pair<>(0,1);
    private static final Pair<Integer, Integer> NOT_MINE_POSITION = new Pair<>(0,0);
    private static final Set<Cell> MINES = Set.of(new Cell(FIRST_MINE_POSITION), new Cell(SECOND_MINE_POSITION));

    private Logics logics;

    @BeforeEach
    void setUp() {
        this.logics = new LogicsImpl(MINES);
    }

    @Test
    void testIsAMine() {
        assertTrue(logics.isAMine(new Cell(FIRST_MINE_POSITION)));
    }

    @Test
    void testIsNotAMine() {
        assertFalse(logics.isAMine(new Cell(NOT_MINE_POSITION)));
    }

    @Test
    void testGetAdjacentMines() {
        assertEquals(MINES.size(), logics.getAdjacentMines(new Cell(NOT_MINE_POSITION)));
    }
}
