package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsGameInfo extends GameObject{
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }
    public abstract String getText();
    public abstract Position getPosition();
}
