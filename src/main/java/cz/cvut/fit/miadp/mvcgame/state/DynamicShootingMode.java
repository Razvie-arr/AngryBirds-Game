package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;

public class DynamicShootingMode implements IShootingMode {

    @Override
    public void shoot( AbsCannon cannon ) {
        int missileCounter = cannon.getMissileCounter();
        int halfMissileCounter = missileCounter / 2;
        for (int i = 0; i < halfMissileCounter; i++) {
            cannon.aimUp( );
            cannon.primitiveShoot( );
        }

        cannon.backToInitialAngle( );

        for (int i = halfMissileCounter; i < missileCounter; i++) {
            cannon.primitiveShoot();
            cannon.aimDown();
        }

        cannon.backToInitialAngle();
    }

    @Override
    public String getName( ) {
        return "DynamicShootingMode";
    }

    
}
