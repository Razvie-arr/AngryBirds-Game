package cz.cvut.fit.miadp.mvcgame.model.Vector;

public abstract class Vector {
    private int dX = 0;
    private int dY = 0;

    public int getDx() {
        return dX;
    }

    public int getDy() {
        return dY;
    }

    public void setDx(int x) {
        this.dX = x;
    }

    public void setDy(int y) {
        this.dY = y;
    }

}
