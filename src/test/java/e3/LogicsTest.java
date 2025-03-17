package e3;

import e3.grid.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class LogicsTest {

    private static final Pair<Integer, Integer> MINE_POSITION = new Pair<>(0,0);
    private static final Pair<Integer, Integer> NOT_MINE_POSITION = new Pair<>(1,1);
    private static final Pair<Integer, Integer> FLAG_POSITION = new Pair<>(0, 1);
    private static final Pair<Integer, Integer> NO_MINES_AROUND = new Pair<>(2, 2);
    private static final int SIZE = 4;

    private Logics logics;

    @BeforeEach
    void setUp() {
        this.logics = new LogicsImpl(SIZE, Set.of(new Cell(MINE_POSITION)));
    }

    @Test
    void testIsMined() {
        assertTrue(logics.isMined(new Cell(MINE_POSITION)));
    }

    @Test
    void testIsNotAMine() {
        assertFalse(logics.isMined(new Cell(NOT_MINE_POSITION)));
    }

    @Test
    void testGetAdjacentMines() {
        int expectedMines = 1;
        assertEquals(expectedMines, logics.getAdjacentMines(new Cell(NOT_MINE_POSITION)));
    }

    @Test
    void testFlagClick() {
        logics.flagClick(new Cell(FLAG_POSITION));
        assertTrue(logics.isFlagged(new Cell(FLAG_POSITION)));
    }

    @Test
    void testRemoveFlagAndCheck() {
        logics.flagClick(new Cell(FLAG_POSITION));
        logics.flagClick(new Cell(FLAG_POSITION));
        assertFalse(logics.isFlagged(new Cell(FLAG_POSITION)));
    }

    @Test
    void testDigCellWithMinesAround() {
        logics.dig(new Cell(NOT_MINE_POSITION));
        assertTrue(logics.isDug(new Cell(NOT_MINE_POSITION)));
        assertFalse(logics.isDug(new Cell(new Pair<>(NOT_MINE_POSITION.getX() + 1, NOT_MINE_POSITION.getY()))));
        assertFalse(logics.isDug(new Cell(new Pair<>(NOT_MINE_POSITION.getX(), NOT_MINE_POSITION.getY() + 1))));
    }

    @Test
    void testDigCellWithNoMinesAround() {
        Cell cellWithNoMinesAround = new Cell(NO_MINES_AROUND);
        Set<Cell> expectedAdjacentDugCells = Set.of(
                new Cell(new Pair<>(NO_MINES_AROUND.getX() - 1, NO_MINES_AROUND.getY() - 1)),
                new Cell(new Pair<>(NO_MINES_AROUND.getX() - 1, NO_MINES_AROUND.getY())),
                new Cell(new Pair<>(NO_MINES_AROUND.getX() - 1, NO_MINES_AROUND.getY() + 1)),
                new Cell(new Pair<>(NO_MINES_AROUND.getX(), NO_MINES_AROUND.getY() - 1)),
                new Cell(new Pair<>(NO_MINES_AROUND.getX(), NO_MINES_AROUND.getY())),
                new Cell(new Pair<>(NO_MINES_AROUND.getX(), NO_MINES_AROUND.getY() + 1)),
                new Cell(new Pair<>(NO_MINES_AROUND.getX() + 1, NO_MINES_AROUND.getY() - 1)),
                new Cell(new Pair<>(NO_MINES_AROUND.getX() + 1, NO_MINES_AROUND.getY())),
                new Cell(new Pair<>(NO_MINES_AROUND.getX() + 1, NO_MINES_AROUND.getY() + 1)));

        logics.dig(cellWithNoMinesAround);
        assertTrue(expectedAdjacentDugCells.stream()
                .allMatch(logics::isDug));
    }

    @Test
    void voidIsItAVictory() {
        assertFalse(logics.isItAVictory());
        Set<Cell> allCells = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                allCells.add(new Cell(new Pair<>(i, j)));
            }
        }
        allCells.stream()
                .filter(Predicate.not(logics::isMined))
                .forEach(logics::dig);
        assertTrue(logics.isItAVictory());
    }
}
