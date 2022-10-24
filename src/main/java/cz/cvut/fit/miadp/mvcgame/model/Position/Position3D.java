package cz.cvut.fit.miadp.mvcgame.model.Position;

import cz.cvut.fit.miadp.mvcgame.model.Vector.IVector;
import cz.cvut.fit.miadp.mvcgame.model.Vector.Vector3D;

public class Position3D extends Position
{
    private int dimZ = 0;

	public Position3D() {}

	public Position3D(int x, int y, int z) {
		this.setX(x);
		this.setY(y);
		this.dimZ = z;
	}

	public int getZ() {
		return dimZ;
	}

	public void setZ(int z) {
		this.dimZ = z;
	}

	public void add(IVector vector) {
		Vector3D vector3D = (Vector3D) vector;
		this.setX(this.getX() + vector3D.getDx());
		this.setY(this.getY() + vector3D.getDy());
		this.setZ(this.getZ() + vector3D.getDz());
	}
}