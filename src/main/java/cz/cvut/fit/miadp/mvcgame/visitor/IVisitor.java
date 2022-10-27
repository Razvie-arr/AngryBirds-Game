package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.model.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.AbsMissile;

public interface IVisitor {
    public void visitCannon(AbsCannon cannon);
    public void visitMissile(AbsMissile missile);

    //TODO: enemies, gameinfo, collisions...
}
