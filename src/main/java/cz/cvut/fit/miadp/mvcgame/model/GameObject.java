package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.visitor.IVisitable;

public abstract class GameObject implements IVisitable {
    protected Position position;

    public void move(Vector vector) {
        this.position.add(vector);
    }

    public Position getPosition() {return position;}

}
