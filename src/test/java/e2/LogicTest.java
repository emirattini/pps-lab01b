package e2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  private static final int GRID_SIZE = 5;
  private static final Pair<Integer, Integer> PAWN_POSITION = new Pair<>(0,0);
  private static final Pair<Integer, Integer> KNIGHT_POSITION = new Pair<>(2, 1);
  private static final Pair<Integer, Integer> OUT_OF_BOUNDS_TARGET = new Pair<>(GRID_SIZE + 1, GRID_SIZE + 1);

  private Logics logics;

  @BeforeEach
  void setUp() {
    logics = new LogicsImpl(GRID_SIZE, PAWN_POSITION, KNIGHT_POSITION);
  }

  @Test
  void testIsPawnPresent() {
    assertEquals(logics.getPawnPosition(), PAWN_POSITION);
  }

  @Test
  void testIsKnightPresent() {
    assertEquals(logics.getKnightPosition(), KNIGHT_POSITION);
  }

  @Test
  void testAttackOutOfBound() {
    assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(OUT_OF_BOUNDS_TARGET));
  }

  @Test
  void testNotReachableAttack() {
    assertFalse(logics.hit(KNIGHT_POSITION));
  }

  @Test
  void testRightAttack() {
    assertTrue(logics.hit(PAWN_POSITION));
    assertEquals(logics.getKnightPosition(), PAWN_POSITION);
  }
}
