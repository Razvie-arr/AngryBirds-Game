package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.model.Position.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector.IVector;

public abstract class GameObject {
    protected Position position;

    public void move(IVector vector) {
        this.position.add(vector);
    }

    public Position getPosition() {return position;}
}
