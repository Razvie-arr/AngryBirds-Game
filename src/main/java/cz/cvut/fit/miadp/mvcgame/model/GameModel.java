package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements IObservable {

    private final Cannon cannon;
    private List<IObserver> observers;

    public GameModel() {
        this.cannon = new Cannon(new Position(MvcGameConfig.CANNON_POSITION_X, MvcGameConfig.CANNON_POSITION_Y));
        this.observers = new ArrayList<>();
    }

    public Position getCannonPosition() { return this.cannon.getPosition(); }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObserver();
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObserver();
    }

    public void moveCannonLeft() {
        this.cannon.moveLeft();
        this.notifyObserver();
    }

    public void moveCannonRight() {
        this.cannon.moveRight();
        this.notifyObserver();
    }

    public void update() {
//        this.moveMissiles();
//        this.destroyMissiles();
//        this.destroyDeathEnemies();
    }

    @Override
    public void registerObserver(IObserver obs) {
        if (!this.observers.contains(obs)) {
            this.observers.add(obs);
        }
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        if (this.observers.contains(obs)) {
            observers.remove(obs);
        }
    }

    @Override
    public void notifyObserver() {
        for (IObserver obs : observers) {
            obs.update();
        }
    }
}
