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
	public boolean hit(Pair<Integer, Integer> target) {
		if (target.getX() >= this.size || target.getY() >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		return knight.move(target) && pawn.equals(target);
	}

	@Override
	public Pair<Integer, Integer> getKnightPosition() {
		return knight.getPosition();
	}

	@Override
	public Pair<Integer, Integer> getPawnPosition() {
		return pawn;
	}
}
