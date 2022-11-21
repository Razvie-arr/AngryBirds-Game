package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public class MagnetToBlackHoleMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition( AbsMissile missile ) {
        double initAngle = missile.getInitAngle( );
        int initVelocity = missile.getInitVelocity( );
        long time = missile.getAge( ) / 100;

        int dX = ( int )( initVelocity * time * Math.cos( initAngle ) );
        int dY = ( int )( initVelocity * time * Math.sin( initAngle ) + ( -1 * MvcGameConfig.GRAVITY_OF_BLACK_HOLE * time * time / 2) );

        missile.move( new Vector( dX, dY ) );

    }
    
}
