package e2;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private final Knight knight;
	private final Random random = new Random();
	private final int size;

	public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition) {
		this.size = size;
		this.pawn = pawnPosition;
		this.knight = new KnightImpl(knightPosition);
	}

    public LogicsImpl(int size){
    	this.size = size;
        this.pawn = this.randomEmptyPosition();
        this.knight = new KnightImpl(this.randomEmptyPosition());
    }

	private Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		return knight.move(new Pair<>(row, col)) && hasPawn(row, col);
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.getPosition().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}
}
