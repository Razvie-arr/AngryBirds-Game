package cz.cvut.fit.miadp.mvcgame.model.Position;

import cz.cvut.fit.miadp.mvcgame.model.Vector.IVector;
import cz.cvut.fit.miadp.mvcgame.model.Vector.Vector2D;

public class Position2D extends Position
{
	public Position2D() {}

	public Position2D(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

    public void add(IVector vector) {
		Vector2D vector2D = (Vector2D) vector;
		this.setX(this.getX() + vector2D.getDx());
		this.setY(this.getY() + vector2D.getDy());
    }
}