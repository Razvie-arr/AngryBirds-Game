package cz.cvut.fit.miadp.mvcgame.config;

import cz.cvut.fit.miadp.mvcgame.model.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.model.Position;

public interface IGameObjectFactory {

    AbsCannon createCannon();
    AbsMissile createMissile(Position position);

    //createEnemy();
}
