package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Cannon_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Enemy_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.GameInfo_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Missile_A;

import java.util.ArrayList;
import java.util.List;

public class GameObjectFactory_A implements IGameObjectFactory {

    private IGameModel model;

    public GameObjectFactory_A( IGameModel model ){
        this.model = model;
    }

    @Override
    public Cannon_A createCannon() {
        return new Cannon_A( new Position( MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y ), this );
    }

    @Override
    public Missile_A createMissile( Position cannonPosition, double initAngle, int initVelocity ) {
        return new Missile_A(model.getCannonPosition(), initAngle, initVelocity, this.model.getMovingStrategy( ) );
    }

    @Override
    public GameInfo_A createGameInfo() {
        return new GameInfo_A(new Position(MvcGameConfig.GAMEINFO_POS_X, MvcGameConfig.GAMEINFO_POS_Y), model);
    }

    @Override
    public Enemy_A createEnemy() {
        return new Enemy_A(MvcGameConfig.createEnemyPosition());
    }

    @Override
    public Enemy_A createEnemy_Test() {
        return new Enemy_A(new Position(500, 360));
    }
}
