package e2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  private static final int GRID_SIZE = 5;
  private static final Pair<Integer, Integer> PAWN_POSITION = new Pair<>(0,0);
  private static final Pair<Integer, Integer> KNIGHT_POSITION = new Pair<>(2, 1);

  private Logics logics;

  @BeforeEach
  void setUp() {
    logics = new LogicsImpl(GRID_SIZE, PAWN_POSITION, KNIGHT_POSITION);
  }

  @Test
  void testIsPawnPresent() {
    assertTrue(logics.hasPawn(PAWN_POSITION.getX(), PAWN_POSITION.getY()));
  }

  @Test
  void testIsKnightPresent() {
    assertTrue(logics.hasKnight(KNIGHT_POSITION.getX(), KNIGHT_POSITION.getY()));
  }

  @Test
  void testAttackOutOfBound() {
    assertThrows(IndexOutOfBoundsException.class, () -> logics.hit(GRID_SIZE+1, GRID_SIZE+1));
  }

  @Test
  void testRightAttack() {
    assertTrue(logics.hit(PAWN_POSITION.getX(), PAWN_POSITION.getY()));
  }

  @Test
  void testNotReachableAttack() {
    assertFalse(logics.hit(KNIGHT_POSITION.getX(), KNIGHT_POSITION.getY()));
  }
}
