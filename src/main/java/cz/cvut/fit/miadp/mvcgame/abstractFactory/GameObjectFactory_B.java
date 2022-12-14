package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Enemy_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_B.Enemy_B;

public class GameObjectFactory_B implements IGameObjectFactory{

    private IGameModel model;

    public GameObjectFactory_B( IGameModel model ){
        this.model = model;
    }
    @Override
    public AbsCannon createCannon() {
        return null;
    }

    @Override
    public AbsMissile createMissile(Position cannonPosition, double initAngle, int initVelocity) {
        return null;
    }

    @Override
    public AbsGameInfo createGameInfo() {
        return null;
    }

    @Override
    public AbsEnemy createEnemy() {
        return new Enemy_B(MvcGameConfig.createEnemyPosition());
    }
    public AbsEnemy createEnemy_Test() {
//        return new Enemy_B(MvcGameConfig.createEnemyPosition());
        return null;
    }
    @Override
    public AbsBarrier createBarrier(Position position) {
        return null;
    }
}
