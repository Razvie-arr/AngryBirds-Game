package cz.cvut.fit.miadp.mvcgame.model.gameObjects.Family_B;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public class Missile_B extends AbsMissile {
    public Missile_B(Position initPosition) {
        initPosition.setX(initPosition.getX() * 2);
        this.position = initPosition;
    }

    //2x faster than Missile_A
    @Override
    public void move(Vector vector) {
        vector.setDX(vector.getDX() * 2);
        this.position.add(vector);
    }
}
