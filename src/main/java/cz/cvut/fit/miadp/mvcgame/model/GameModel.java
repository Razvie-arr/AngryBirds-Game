package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public abstract class GameModel implements IObservable {
    private List<IObserver> observers;

    public GameModel() {
        this.observers = new ArrayList<>();
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
    public void notifyObservers() {
        for (IObserver obs : observers) {
            obs.update();
        }
    }

    @Override
    public void notifyObserver(Class observerClass) {
        for (IObserver obs : observers) {
            if (obs.getClass() == observerClass) {
                obs.update();
            }
        }
    }
}
