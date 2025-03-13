package e3;

import e3.grid.Cell;

public interface Logics {

    /**
     * @param cell a cell
     * @return true if the cell is a mine, false otherwise
     */
    boolean isAMine(Cell cell);

    /**
     * @param cell a cell
     * @return the number of adjacent mines
     */
    int getAdjacentMines(Cell cell);
}
