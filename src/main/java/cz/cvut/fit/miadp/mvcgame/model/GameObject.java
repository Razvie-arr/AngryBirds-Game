package cz.cvut.fit.miadp.mvcgame.model;

public abstract class GameObject {
    protected Position position;

    public void move(Vector vector) {
        this.position.add(vector);
    }

    public Position getPosition() {return position;}
}
