package cz.cvut.fit.miadp.mvcgame.model.Vector;

public class Vector3D extends Vector2D
{
    public Vector3D() {}

    public Vector3D(int dX, int dY, int dZ)
    {
§§§        axis.add(dX);
        axis.add(dY);
        axis.add(dZ);

    }

    public void setDz(int dZ) {
        this.axis.set(2, dZ);
    }

    public int getDz() {
        return this.axis.get(2);
    }
}
