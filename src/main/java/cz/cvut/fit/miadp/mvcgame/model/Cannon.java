package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector.IVector;
import cz.cvut.fit.miadp.mvcgame.model.Vector.Vector2D;

public class Cannon extends GameObject {
//    private double angle;
//    private int power;
    public Cannon(Position initPosition) {
        this.position = initPosition;
    }


    public void moveUp() {
        this.move(new Vector2D(0, -1 * MvcGameConfig.MOVE_STEP));
    }

    public void moveDown() {
        this.move(new Vector2D(0, MvcGameConfig.MOVE_STEP));
    }
    public void moveLeft() {
        this.move(new Vector2D(-1 * MvcGameConfig.MOVE_STEP, 0));
    }
    public void moveRight() {
        this.move(new Vector2D(MvcGameConfig.MOVE_STEP, 0));
    }
}
