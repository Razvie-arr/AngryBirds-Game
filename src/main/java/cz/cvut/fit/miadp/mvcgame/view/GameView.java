package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.visitor.GameRenderer;
import javafx.scene.canvas.GraphicsContext;

public class GameView implements IObserver {
    private GameModel model;
    private GameController controller;
    private GraphicsContext gr;
    private GameRenderer renderer;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(this.model);
        this.gr = null;
        this.model.registerObserver(this);
        this.renderer = new GameRenderer();
    }

    public GameController getController() {
        return this.controller;
    }

    public void render()
    {
        this.gr.clearRect(0, 0, MvcGameConfig.MAX_Y, MvcGameConfig.MAX_Y);
        for (GameObject go : model.getGameObjects()) {
            go.acceptVisitor(this.renderer);
        }
    }


    public void setGraphicContext(GraphicsContext gr) {
        this.gr = gr;
        this.renderer.setGraphicContext(gr);
        this.update();
    }

    @Override
    public void update() {
        this.render();
    }
}
