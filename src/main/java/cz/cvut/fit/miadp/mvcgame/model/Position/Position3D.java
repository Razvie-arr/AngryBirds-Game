package cz.cvut.fit.miadp.mvcgame.model.Position;

import cz.cvut.fit.miadp.mvcgame.model.Vector.Vector3D;

public class Position3D extends Position<Vector3D>
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

	@Override
	public void add(Vector3D vector) {
		this.setX(this.getX() + vector.getDx());
		this.setY(this.getY() + vector.getDy());
		this.setZ(this.getZ() + vector.getDz());
	}
}