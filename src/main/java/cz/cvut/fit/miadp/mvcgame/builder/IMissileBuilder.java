package cz.cvut.fit.miadp.mvcgame.builder;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public interface IMissileBuilder {
    void setPosition(Position position);
    void setAngle(double angle);
    void setVelocity(int velocity);
    void setMovingStrategy(IMovingStrategy strategy);
    AbsMissile getResult();
}
