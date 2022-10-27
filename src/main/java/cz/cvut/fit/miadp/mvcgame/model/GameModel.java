package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.config.GameObjectsFactory_A;
import cz.cvut.fit.miadp.mvcgame.config.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements IObservable {

    private final AbsCannon cannon;
    private List<AbsMissile> missiles;
    private List<IObserver> observers;
    private IGameObjectFactory goFact;


    public GameModel() {
        this.observers = new ArrayList<>();
        this.goFact = new GameObjectsFactory_A();
        this.cannon = this.goFact.createCannon();
        this.missiles = new ArrayList<>();
    }

    public void update() {
        this.moveMissiles();
    }

    private void moveMissiles() {
        for (AbsMissile missile : this.missiles) {
            missile.move(new Vector(MvcGameConfig.MOVE_STEP, 0));
        }
        this.destroyMissiles();
        this.notifyObservers();
    }

    private void destroyMissiles() {
        List<AbsMissile> missilesToRemove = new ArrayList<>();
        for (AbsMissile missile : this.missiles) {
            if (missile.getPosition().getX() > MvcGameConfig.MAX_X) {
                missilesToRemove.add(missile);
            }
        }
        this.missiles.removeAll(missilesToRemove);
    }

    public Position getCannonPosition() { return this.cannon.getPosition(); }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObservers();
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObservers();
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
    public void notifyObservers() {
        for (IObserver obs : observers) {
            obs.update();
        }
    }

    public void cannonShoot() {
        this.missiles.add(this.cannon.shoot());
        this.notifyObservers();
    }

    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> go = new ArrayList<>();
        go.add(this.cannon);
        go.addAll(this.missiles);
        return go;
    }
}
