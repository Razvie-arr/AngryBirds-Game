package cz.cvut.fit.miadp.mvcgame.model.Vector;

public class Vector3D extends Vector2D
{
    public Vector3D() {}

    public Vector3D(int dX, int dY, int dZ)
    {
        this.axis.set(0, dX);
        this.axis.set(1, dY);
        this.axis.set(2, dZ);

    }

    public void setDz(int dZ) {
        this.axis.set(2, dZ);
    }

    public int getDz() {
        return this.axis.get(2);
    }
}
