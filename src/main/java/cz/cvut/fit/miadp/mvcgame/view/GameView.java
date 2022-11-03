package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.Aspect;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameView implements IObserver {

    protected GameController controller;
    protected GraphicsContext gr;

    public GameView() {
        this.gr = null;
    }

    public abstract GameController getController();

    public void render()
    {
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_Y, MvcGameConfig.MAX_Y);
        this.draw();
    }

    protected abstract void draw();

    public void setGraphicContext(GraphicsContext gr) {
        this.gr = gr;
        this.render();
    }

    @Override
    public void update(IObservable obs, Aspect interest) {
        //does same thing for cannon_up, cannon_down and shoot, but can be modified
        this.render();
    }
}
