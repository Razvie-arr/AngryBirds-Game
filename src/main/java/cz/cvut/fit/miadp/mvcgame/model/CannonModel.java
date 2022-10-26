package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.view.CannonView;

public class CannonModel extends GameModel {

    private final Cannon cannon;

    public CannonModel() {
        super();
        this.cannon = new Cannon(new Position(MvcGameConfig.CANNON_POSITION_X, MvcGameConfig.CANNON_POSITION_Y));
    }

    public Position getCannonPosition() { return this.cannon.getPosition(); }

    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObserver(CannonView.class);
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObserver(CannonView.class);
    }

    public void moveCannonLeft() {
        this.cannon.moveLeft();
        this.notifyObserver(CannonView.class);
    }

    public void moveCannonRight() {
        this.cannon.moveRight();
        this.notifyObserver(CannonView.class);
    }

}
