package cz.cvut.fit.miadp.mvcgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectFactory_A;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;

public class GameModel implements IGameModel {

    private AbsCannon cannon;
    private List<AbsMissile> missiles;
    private List<IObserver> observers;
    private IGameObjectFactory goFact;
    private IMovingStrategy movingStrategy;
    private AbsGameInfo gameInfo;

    private int score;

    private Queue<AbstractGameCommand> unexecutedCmds;
    private Stack<AbstractGameCommand> executedCmds;

    public GameModel( ) {
        this.observers = new ArrayList<IObserver>( );
        this.goFact = new GameObjectFactory_A( this );
        this.cannon = this.goFact.createCannon( );
        this.gameInfo = this.goFact.createGameInfo();
        this.missiles = new ArrayList<AbsMissile>();
        this.movingStrategy = new SimpleMovingStrategy( );   
        this.score = 0;
        this.unexecutedCmds = new LinkedBlockingDeque<>();
        this.executedCmds = new Stack<>();
    }

    public void update( ) {
        this.executedCmds();
        this.moveMissiles( );
    }

    private void executedCmds() {
        while (!this.unexecutedCmds.isEmpty()) {
            AbstractGameCommand cmd = this.unexecutedCmds.poll();
            cmd.doExecute();
            this.executedCmds.push(cmd);
        }
    }

    private void moveMissiles( ) {
        for ( AbsMissile missile : this.missiles ) {
            missile.move(  );
        }
        this.destroyMissiles( );
        this.notifyObservers( );
    }

    private void destroyMissiles( ) {
        List<AbsMissile> missilesToRemove = new ArrayList<AbsMissile>();
        for ( AbsMissile missile : this.missiles ) {
            if( missile.getPosition( ).getX( ) > MvcGameConfig.MAX_X ) {
                missilesToRemove.add( missile );
            }
        }
        this.missiles.removeAll(missilesToRemove);
    }

    public Position getCannonPosition( ) {
        return this.cannon.getPosition( );
    }

    @Override
    public double getCannonAimAngle() {
        return this.cannon.getAngle();
    }

    @Override
    public int getCannonPower() {
        return this.cannon.getPower();
    }

    public void moveCannonUp( ) {
        this.cannon.moveUp( );
        this.notifyObservers( );
    }

    public void moveCannonDown( ) {
        this.cannon.moveDown( );
        this.notifyObservers( );
    }

    public void aimCannonUp( ) {
        this.cannon.aimUp( );
        this.notifyObservers( );
    }

    public void aimCannonDown( ) {
        this.cannon.aimDown( );
        this.notifyObservers( );
    }

    public void cannonPowerUp( ) {
        this.cannon.powerUp( );
        this.notifyObservers( );
    }

    public void cannonPowerDown( ) {
        this.cannon.powerDown( );
        this.notifyObservers( );
    }

    @Override
    public void registerObserver( IObserver obs ) {
        if( !this.observers.contains( obs ) ) {
            this.observers.add( obs );
        }
    }

    @Override
    public void unregisterObserver( IObserver obs ) {
        if( this.observers.contains( obs ) ) {
            this.observers.remove( obs );
        }
    }

    @Override
    public void notifyObservers( ) {
        for( IObserver obs : this.observers ){
            obs.update( );
        }
    }

    public void cannonShoot( ) {
        this.missiles.addAll( cannon.shoot( ) ) ;
        this.notifyObservers( );
    }

    public List<AbsMissile> getMissiles( ) {
        return this.missiles;
    }

    public List<GameObject> getGameObjects( ) {
        List<GameObject> go = new ArrayList<GameObject>();
        go.add( this.cannon );
        go.add(this.gameInfo);
        go.addAll( this.missiles );
        return go;
    }

    public IMovingStrategy getMovingStrategy( ){
        return this.movingStrategy;
    }

    private void setMovingStrategy(IMovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void toggleMovingStrategy( ) {
        if ( this.movingStrategy instanceof SimpleMovingStrategy ) {
            this.movingStrategy = new RealisticMovingStrategy( );
        }
        else if ( this.movingStrategy instanceof RealisticMovingStrategy ){
            this.movingStrategy = new SimpleMovingStrategy( );
        }
    }

    @Override
    public IShootingMode getShootingMode() { return this.cannon.getShootingMode(); }

    private void setShootingMode(IShootingMode shootingMode) { this.cannon.setShootingMode(shootingMode); }

    public void toggleShootingMode( ){
        this.cannon.toggleShootingMode( );
    }

    public Position getGameInfoPosition() {
        return this.gameInfo.getPosition();
    }


    private class Memento {
        private int score;
        private int cannonX;
        private int cannonY;
        private double angle;
        private IMovingStrategy movingStrategy;
        private IShootingMode shootingMode;
        // GO positions
    }

    public Object createMemento( ) {
        Memento m = new Memento( );
        m.score = this.score;
        m.cannonX = this.getCannonPosition().getX();
        m.cannonY = this.getCannonPosition().getY();
        m.angle = this.getCannonAimAngle();
        m.movingStrategy = this.getMovingStrategy();
        m.shootingMode = this.getShootingMode();
        return m;
    }

    public void setMemento( Object memento ) {
        Memento m = ( Memento ) memento;
        this.score = m.score;
        this.cannon.getPosition().setX(m.cannonX);
        this.cannon.getPosition().setY(m.cannonY);
        this.cannon.setAngle(m.angle);
        this.setMovingStrategy(m.movingStrategy);
        this.setShootingMode(m.shootingMode);
    }

    @Override
    public void registerCommand(AbstractGameCommand cmd) {
        unexecutedCmds.add(cmd);
    }

    @Override
    public void undoLastCommand() {
        if (!this.executedCmds.isEmpty()) {
            AbstractGameCommand cmd = this.executedCmds.pop();
            cmd.unExecute();
        }
        this.notifyObservers();
    }

}