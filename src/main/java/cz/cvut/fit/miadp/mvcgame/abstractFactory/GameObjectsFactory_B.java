package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.Family_A.Cannon_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.Family_A.Missile_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.Family_B.Cannon_B;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.Family_B.Missile_B;

public class GameObjectsFactory_B implements IGameObjectFactory{
    @Override
    public AbsCannon createCannon() {
        return new Cannon_B(new Position(MvcGameConfig.CANNON_POSITION_X, MvcGameConfig.CANNON_POSITION_Y), this);
    }

    @Override
    public AbsMissile createMissile(Position position) {
        return new Missile_B(new Position(position.getX(), position.getY()));
    }
}
