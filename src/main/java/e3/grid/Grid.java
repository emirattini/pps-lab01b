package e3.grid;

public interface Grid {

    void addMine(Cell mine);

    boolean isAMine(Cell cell);

    void addFlag(Cell flag);

    boolean isFlagged(Cell cell);

    int getAdjacentMines(Cell cell);
}
