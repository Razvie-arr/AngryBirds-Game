package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
        this.update();
    }

    @Override
    public void update() {
        this.render();
    }
}
