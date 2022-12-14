package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;

import java.util.List;

public interface IGameObjectFactory {

    public AbsCannon createCannon( );
    public AbsMissile createMissile( Position cannonPosition, double initAngle, int initVelocity );
    public AbsGameInfo createGameInfo();
    public AbsEnemy createEnemy();
    public AbsEnemy createEnemy_Test();
    public AbsBarrier createBarrier(Position enemyPosition);
}
