package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.*;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.Cannon_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.Missile_A;

public class GameObjectsFactory_A implements IGameObjectFactory{
    @Override
    public AbsCannon createCannon() {
        return new Cannon_A(new Position(MvcGameConfig.CANNON_POSITION_X, MvcGameConfig.CANNON_POSITION_Y), this);
    }

    @Override
    public AbsMissile createMissile(Position position) {
        return new Missile_A(new Position(position.getX(), position.getY()));
    }
}
