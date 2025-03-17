package e3.grid;

import java.util.Set;

public interface Grid {

    /**
     * @param mine the cell where to add the mine
     */
    void addMine(Cell mine);

    /**
     * @param cell the cell to check
     * @return true if the cell is mined, false otherwise
     */
    boolean isMined(Cell cell);

    /**
     * @param flag the cell to flag
     */
    void addFlag(Cell flag);

    /**
     * @param flag the flag to remove
     */
    void removeFlag(Cell flag);

    /**
     * @param cell the cell to check
     * @return true if the cell is flagged, false otherwise
     */
    boolean isFlagged(Cell cell);

    /**
     * @param cell the cell to dig
     */
    void dig(Cell cell);

    /**
     * @param cell the cell to check
     * @return true if the cell is dug, false otherwise
     */
    boolean isDug(Cell cell);

    /**
     * @param cell the cell to check
     * @return the number of adjacent mines
     */
    int getAdjacentMines(Cell cell);

    /**
     * @return the number of flagged cells
     */
    int getFlaggedNumber();

    /**
     * @param cell the cell to get the neighbors from
     * @return a set with all the adjacent cells
     */
    Set<Cell> getAdjacentCells(Cell cell);
}
