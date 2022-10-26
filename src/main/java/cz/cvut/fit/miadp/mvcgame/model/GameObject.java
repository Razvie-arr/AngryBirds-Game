package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.model.Position.Position2D;
import cz.cvut.fit.miadp.mvcgame.model.Vector.Vector2D;

public abstract class GameObject {
    protected Position2D position;

    public void move(Vector2D vector) {
        this.position.add(vector);
    }

    public Position2D getPosition() { return position;}
}
