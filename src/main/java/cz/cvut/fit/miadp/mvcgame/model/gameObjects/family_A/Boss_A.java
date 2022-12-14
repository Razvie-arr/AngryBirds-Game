package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;

public class Boss_A extends AbsEnemy {
    public Boss_A(Position position) {
        this.position = position;
        this.lifeCounter = 5;
    }
    @Override
    public String getType() {
        return "bossA";
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
