package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.model.GameModel;

import java.util.List;

public abstract class GameController {
    public abstract void processPressedKeys(List<String> pressedKeysCodes);

}