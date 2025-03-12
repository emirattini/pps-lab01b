package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    private static final Pair<Integer, Integer> KNIGHT_POSITION = new Pair<>(0, 0);
    private static final Pair<Integer, Integer> NOT_REACHABLE_TARGET = new Pair<>(0, 0);
    private static final Pair<Integer, Integer> REACHABLE_TARGET = new Pair<>(1, 2);
    private static final Pair<Integer, Integer> OUT_OF_BOUND_TARGET = new Pair<>(-1, -1);

    private Knight knight;

    @BeforeEach
    void setUp() {
        this.knight = new KnightImpl(KNIGHT_POSITION);
    }

    @Test
    void testOutOfBoundTarget() {
        assertThrows(IndexOutOfBoundsException.class, () -> knight.hit(OUT_OF_BOUND_TARGET));
    }

    @Test
    void testNotReachableAttack() {
        assertFalse(knight.hit(NOT_REACHABLE_TARGET));
    }

    @Test
    void testReachableAttack() {
        assertTrue(knight.hit(REACHABLE_TARGET));
    }
}
