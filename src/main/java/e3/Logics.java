package e3;

import e3.grid.Cell;

public interface Logics {

    /**
     * Returns true if a mine was found, otherwise digs the cell
     * @param cell the cell to dig
     * @return true if the cell is a mine, false otherwise
     */
    boolean dig(Cell cell);

    /**
     * @param cell the cell to check
     * @return true if the cell is dug, false otherwise
     */
    boolean isDug(Cell cell);

    /**
     * @param cell a cell
     * @return the number of adjacent mines
     */
    int getAdjacentMines(Cell cell);

    /**
     * If the cell is flagged it removes the flag, otherwise it
     * flags it, but only if the number of flagged cells is less than
     * the number of total mines
     * @param cell target
     */
    void flagClick(Cell cell);

    /**
     * @param cell a cell
     * @return true if the cell is flagged, false otherwise
     */
    boolean isFlagged(Cell cell);

    /**
     * @return true if it is a victory, false otherwise
     */
    boolean isItAVictory();
}
