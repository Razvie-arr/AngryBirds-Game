package cz.cvut.fit.miadp.mvcgame.config;

import cz.cvut.fit.miadp.mvcgame.model.*;

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
