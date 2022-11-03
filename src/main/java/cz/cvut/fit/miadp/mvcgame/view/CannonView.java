package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.controller.CannonController;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.Aspect;
import cz.cvut.fit.miadp.mvcgame.model.CannonModel;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import javafx.scene.image.Image;

public class CannonView extends GameView{

    private final CannonModel model;
    private final CannonController controller;

    public CannonView(CannonModel model) {
        super();
        this.model = model;
        this.controller = new CannonController(model);

        this.model.registerObserver(this, Aspect.CANNON_UP);
        this.model.registerObserver(this, Aspect.CANNON_DOWN);
        this.model.registerObserver(this, Aspect.CANNON_RIGHT);
        this.model.registerObserver(this, Aspect.CANNON_LEFT);
        this.model.registerObserver(this, Aspect.SHOOT);

        this.model.unregisterObserver(this, Aspect.CANNON_UP);
    }

    @Override
    public CannonController getController() {
        return controller;
    }

    @Override
    protected void draw() {
        gr.drawImage(new Image("images/cannon.png"), model.getCannonPosition().getX(), model.getCannonPosition().getY());
    }
}
