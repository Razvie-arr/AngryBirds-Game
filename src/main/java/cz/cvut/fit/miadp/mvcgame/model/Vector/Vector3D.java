package cz.cvut.fit.miadp.mvcgame.model.Vector;

public class Vector3D extends Vector
{
    private int dZ;
    public Vector3D() {}

    public Vector3D(int dX, int dY, int dZ)
    {
        this.setDx(dX);
        this.setDy(dY);
        this.setDz(dZ);

    }

    public void setDz(int dZ) {
        this.dZ = dZ;
    }

    public int getDz() {
        return dZ;
    }
}
