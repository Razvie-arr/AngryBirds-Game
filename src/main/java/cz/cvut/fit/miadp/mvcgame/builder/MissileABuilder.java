package cz.cvut.fit.miadp.mvcgame.builder;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Missile_A;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public class MissileABuilder implements IMissileBuilder {
    private Position position;
    private double angle;
    private int velocity;
    private IMovingStrategy strategy;

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public void setMovingStrategy(IMovingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Missile_A getResult() {
        return new Missile_A(position, angle, velocity, strategy);
    }
}
