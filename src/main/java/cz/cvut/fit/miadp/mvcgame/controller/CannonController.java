package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.model.CannonModel;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;

import java.util.List;

public class CannonController extends GameController{

    private final CannonModel model;

    public CannonController(CannonModel model) {
        this.model = model;
    }

    @Override
    public void processPressedKeys(List<String> pressedKeysCodes)
    {
        for(String code : pressedKeysCodes)
        {
            switch(code) {
                case "UP":
                    model.moveCannonUp();
                    break;
                case "DOWN":
                    model.moveCannonDown();
                    break;
                case "LEFT":
                    model.moveCannonLeft();
                    break;
                case "RIGHT":
                    model.moveCannonRight();
                    break;
                default:
                    //nothing
            }
        }
    }
}
