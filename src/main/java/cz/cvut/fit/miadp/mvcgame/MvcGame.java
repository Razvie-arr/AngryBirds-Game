package cz.cvut.fit.miadp.mvcgame;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.CannonController;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.Cannon;
import cz.cvut.fit.miadp.mvcgame.model.CannonModel;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.view.CannonView;
import cz.cvut.fit.miadp.mvcgame.view.GameView;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class MvcGame
{
    private CannonModel cannonModel;
    private CannonView cannonView;
    private CannonController cannonController;

    public void init()
    {
        this.cannonModel = new CannonModel();
        this.cannonView = new CannonView(cannonModel);
        this.cannonController = this.cannonView.getController();
    }

    public void processPressedKeys(List<String> pressedKeysCodes)
    {
        this.cannonController.processPressedKeys(pressedKeysCodes);
    }

    public void update()
    {
        this.cannonModel.update();
    }

    public void render(GraphicsContext gr)
    {
        this.cannonView.setGraphicContext(gr);
    }

    public String getWindowTitle()
    {
        return "The NI-ADP MvcGame";
    }

    public int getWindowWidth()
    {
        return MvcGameConfig.MAX_X;
    }

    public int getWindowHeight()
    {
        return  MvcGameConfig.MAX_Y;
    }
}