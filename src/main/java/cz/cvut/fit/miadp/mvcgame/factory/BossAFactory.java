package cz.cvut.fit.miadp.mvcgame.factory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Boss_A;

public class BossAFactory implements IBossFactory {
    @Override
    public AbsEnemy createBoss() {
        return new Boss_A(MvcGameConfig.createEnemyPosition());
    }
}
