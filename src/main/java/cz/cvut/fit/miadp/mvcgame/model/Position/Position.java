package cz.cvut.fit.miadp.mvcgame.model.Position;

import cz.cvut.fit.miadp.mvcgame.model.Vector.IVector;

public abstract class Position
{
	private int dimX = 0;
	private int dimY = 0;

	public int getX() {
		return dimX;
	}

	public int getY() {
		return dimY;
	}

	public void setY(int y) {
		this.dimY = y;
	}

	public void setX(int x) {
		this.dimX = x;
	}

	public abstract void add(IVector vector);
}