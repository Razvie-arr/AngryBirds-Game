package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsCannon extends GameObject {
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract  AbsMissile shoot();
    public abstract Position getPosition();

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }
}
