package cz.cvut.fit.miadp.mvcgame.observer;

import cz.cvut.fit.miadp.mvcgame.model.Aspect;

public interface IObservable {
    public void registerObserver(IObserver obs, Aspect interest);
    public void unregisterObserver(IObserver obs, Aspect interest);
    public void notifyObservers(Aspect interest);
}
