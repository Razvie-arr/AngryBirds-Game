package cz.cvut.fit.miadp.mvcgame.model.gameObjects.Family_B;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public class Cannon_B extends AbsCannon {
    private final IGameObjectFactory goFact;

    public Cannon_B(Position initPosition, IGameObjectFactory goFact) {
        this.position = initPosition;
        this.goFact = goFact;
    }

    //3x faster than Cannon_A
    @Override
    public void moveUp() {
        this.move(new Vector(0, -3 * MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void moveDown() {
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP * 3));
    }

    @Override
    public AbsMissile shoot() {
        return this.goFact.createMissile(this.getPosition());
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
