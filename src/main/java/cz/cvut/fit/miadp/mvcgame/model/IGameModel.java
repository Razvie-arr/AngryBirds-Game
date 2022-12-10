package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

public interface IGameModel extends IObservable {
    public void update( );
    public Position getCannonPosition( );
    public Position getGameInfoPosition();
    public void moveCannonUp( );
    public void moveCannonDown( );
    public void aimCannonUp( );
    public void aimCannonDown( );
    public void cannonPowerUp( );
    public void cannonPowerDown( );
    public void cannonShoot( );
    public double getCannonAimAngle();
    public int getCannonPower();
    public List<AbsMissile> getMissiles( );
    public List<AbsEnemy> getEnemies();
    public List<GameObject> getGameObjects( );
    public IMovingStrategy getMovingStrategy( );
    public IShootingMode getShootingMode();
    public void toggleMovingStrategy( );
    public void toggleShootingMode( );
    public void toggleTheme();
    public void incrementMissileCounter();
    public void decrementMissileCounter( );
    public int getMissileCounter();
    public Object createMemento( );
    public void setMemento( Object memento );

    public void registerCommand(AbstractGameCommand cmd);
    public void undoLastCommand();
}
