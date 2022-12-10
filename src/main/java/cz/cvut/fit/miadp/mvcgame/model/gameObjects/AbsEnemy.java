package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject {
    protected int lifeCounter;
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
    public abstract String getType();
    public abstract int getLifeCounter();
    public abstract void decrementLifeCounter();
}
