package cz.cvut.fit.miadp.mvcgame.model.Position;

import cz.cvut.fit.miadp.mvcgame.model.Vector.Vector2D;

public class Position2D extends Position<Vector2D> {
	public Position2D() {}

	public Position2D(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	@Override
	public void add(Vector2D vector) {
		this.setX(this.getX() + vector.getDx());
		this.setY(this.getY() + vector.getDy());
	}
}