package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_B;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;

public class Enemy_B extends AbsEnemy {

    public Enemy_B(Position initialPosition) {
        this.position = initialPosition;
        this.lifeCounter = 2;
    }

    @Override
    public String getType() {
        return "enemyB";
    }
    @Override
    public int getLifeCounter() {
        return lifeCounter;
    }

    @Override
    public void decrementLifeCounter() {
        lifeCounter--;
    }
}
