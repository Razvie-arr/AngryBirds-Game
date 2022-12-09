package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;

public class UltraSpeedMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition( AbsMissile missile ) {
        double initAngle = missile.getInitAngle( );
        int velocity = missile.getInitVelocity( ) * 5;
        long time = missile.getAge( );

        int dX = ( int )(velocity * time * Math.cos( initAngle ) );
        int dY = ( int )(velocity * time * Math.sin( initAngle ) );

        missile.move( new Vector( dX, dY ) );

    }
    
}
