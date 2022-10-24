package cz.cvut.fit.miadp.mvcgame.model.Vector;

public class Vector2D implements IVector {

    public Vector2D() {}

    public Vector2D(int dX, int dY)
    {
        axis.add(dX);
        axis.add(dY);
    }

    public void setDx(int dX) {
        this.axis.set(0, dX);
    }

    public int getDx() {
        return this.axis.get(0);
    }

    public void setDy(int dY) {
        this.axis.set(1, dY);
    }

    public int getDy() {
        return this.axis.get(1);
    }
}
