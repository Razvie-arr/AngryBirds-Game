package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;

public class Enemy_A extends AbsEnemy {
    public Enemy_A(Position initialPosition) {
        this.position = initialPosition;
        this.lifeCounter = 2;
    }

    @Override
    public String getType() {
        return "enemyA";
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
