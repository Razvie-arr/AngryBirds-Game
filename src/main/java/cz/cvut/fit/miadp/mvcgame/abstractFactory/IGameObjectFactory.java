package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

import java.util.List;

public interface IGameObjectFactory {

    public AbsCannon createCannon( );
    public AbsMissile createMissile( Position cannonPosition, double initAngle, int initVelocity );
    public AbsGameInfo createGameInfo();
    public AbsEnemy createEnemy();
    public AbsEnemy createEnemy_Test();
}
