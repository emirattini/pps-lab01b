package e2;

public interface Logics{

    /**
     * Make the knight try to hit the pawn
     * @param target - pair indicating target position
     * @return true if pawn was at target and knight could reach it,
     *          false otherwise
     */
    boolean hit(Pair<Integer, Integer> target);

    /**
     * @return the position of the pawn
     */
    Pair<Integer, Integer> getPawnPosition();

    /**
     * @return the position of the knight
     */
    Pair<Integer, Integer> getKnightPosition();
}
