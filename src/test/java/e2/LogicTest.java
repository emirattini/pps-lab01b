package e2;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

  public static final int GRID_SIZE = 5;
  public static final int KNIGHT_JUMP = 3;
  private Logics logic;

  @BeforeEach
  void setUp() {
    logic = new LogicsImpl(GRID_SIZE);
  }

  @Test
  void testIsKnightPresent() {
    boolean isKnightPresent = getKnightPosition() != null;
    assertTrue(isKnightPresent);
  }

  @Test
  void testIsPawnPresent() {
    boolean isPawnPresent = getPawnPosition() != null;
    assertTrue(isPawnPresent);
  }

  @Test
  void testAttackOutOfBound() {
    assertThrows(IndexOutOfBoundsException.class, () -> logic.hit(GRID_SIZE+1, GRID_SIZE+1));
  }

  @Test
  void testRightKnightAttack() {
    var knightPosition = getKnightPosition();
    var pawnPosition = getPawnPosition();
    assertNotNull(knightPosition);
    assertNotNull(pawnPosition);
    int xDiff = Math.abs(pawnPosition.getX() - knightPosition.getX());
    int yDiff = Math.abs(pawnPosition.getY() - knightPosition.getY());
    boolean canAttack = xDiff > 0 && yDiff > 0 && xDiff + yDiff == KNIGHT_JUMP;
    assertEquals(canAttack, logic.hit(pawnPosition.getX(), pawnPosition.getY()));
  }

  private Pair<Integer, Integer> getKnightPosition() {
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        if (logic.hasKnight(i, j)) {
          return new Pair<>(i, j);
        }
      }
    }
    return null;
  }

  private Pair<Integer, Integer> getPawnPosition() {
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        if (logic.hasPawn(i, j)) {
          return new Pair<>(i, j);
        }
      }
    }
    return null;
  }
}
