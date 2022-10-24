package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameView implements IObserver {
    private GameModel model;
    private GameController controller;
    private GraphicsContext gr;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        this.gr = null;

        this.model.registerObserver(this);
    }

    public GameController getController() {
        return this.controller;
    }

    public void render()
    {
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_Y, MvcGameConfig.MAX_Y);
        this.drawCannon();
    }

    private void drawCannon() {
        gr.drawImage(new Image("images/cannon.png"), model.getCannonPosition().getX(), model.getCannonPosition().getY());
    }

    public void setGraphicContext(GraphicsContext gr) {
        this.gr = gr;
        this.update();
    }

    @Override
    public void update() {
        this.render();
    }
}
