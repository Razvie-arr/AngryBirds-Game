package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;

import java.util.*;

public abstract class GameModel implements IObservable {
    private EnumMap<Aspect, Set<IObserver>> observers;

    public GameModel() {
        this.observers = new EnumMap<>(Aspect.class);
    }

    public void update() {
//        this.moveMissiles();
//        this.destroyMissiles();
//        this.destroyDeathEnemies();
    }

    @Override
    public void registerObserver(IObserver obs, Aspect interest) {
        if (observers.containsKey(interest)) {
            Set<IObserver> observersWithInterest = observers.get(interest);
            observersWithInterest.add(obs);
        } else {
            Set<IObserver> newObserversWithInterest = new HashSet<>();
            newObserversWithInterest.add(obs);
            observers.put(interest, newObserversWithInterest);
        }
    }

    @Override
    public void unregisterObserver(IObserver obs, Aspect interest) {
        if (observers.containsKey(interest)) {
            Set<IObserver> observersWithInterest = observers.get(interest);
            observersWithInterest.remove(obs);
        }
    }

    @Override
    public void notifyObservers(Aspect interest) {
        if (observers.containsKey(interest)) {
            for (IObserver obs : observers.get(interest)) {
                obs.update(this, interest);
            }
        }
    }
}
