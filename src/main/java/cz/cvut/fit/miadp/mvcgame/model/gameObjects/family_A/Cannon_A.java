package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import java.util.ArrayList;
import java.util.List;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.builder.IMissileBuilder;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;

import static cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig.INIT_ANGLE;

public class Cannon_A extends AbsCannon {

    private IGameObjectFactory goFact;

    private double angle;
    private int power;
    private List<AbsMissile> shootingBatch;

    public Cannon_A( Position initialPosition, IGameObjectFactory goFact ){
        this.position = initialPosition;
        this.goFact = goFact;
        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;
        this.shootingMode = AbsCannon.SINGLE_SHOOTING_MODE;
        this.shootingBatch = new ArrayList<>();
        this.missileCounter = 1;
    }

    public void moveUp( ) {
        this.move( new Vector( 0, -1 * MvcGameConfig.MOVE_STEP ) );
    }

    public void moveDown( ) {
        this.move( new Vector( 0, MvcGameConfig.MOVE_STEP ) );
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void backToInitialAngle( ) {
        this.angle = INIT_ANGLE;
    }

    @Override
    public List<AbsMissile> shoot( ) {
        this.shootingBatch.clear();
        this.shootingMode.shoot(this);
        return this.shootingBatch;
    }

    @Override
    public List<AbsMissile> ultraRageShoot() {
        this.shootingBatch.clear();
        this.setAngle(-3.14);
        for (int i = 0; i < 10; i++) {
            this.primitiveShoot();
            this.aimDown();
            this.aimDown();
            this.aimDown();
            this.aimDown();
        }
        this.backToInitialAngle();
        return this.shootingBatch;
    }

    @Override
    public void aimUp() {
        this.angle -= MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void aimDown() {
        this.angle += MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void powerUp() {
        this.power += MvcGameConfig.POWER_STEP;
    }

    @Override
    public void powerDown() {
        if ( this.power - MvcGameConfig.POWER_STEP > 0 ){
            this.power -= MvcGameConfig.POWER_STEP;
        }
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void primitiveShoot() {
        this.shootingBatch.add(
            this.goFact.createMissile(
                new Position( this.getPosition( ).getX( ), this.getPosition( ).getY( ) ),
                this.angle,
                this.power
            )
        );
    }

    @Override
    public void incrementMissileCounter() {
        if (this.shootingMode == DYNAMIC_SHOOTING_MODE) {
            this.missileCounter++;
        }
    }

    @Override
    public void decrementMissileCounter() {
        if (this.shootingMode == DYNAMIC_SHOOTING_MODE && this.missileCounter > 0) {
            this.missileCounter--;
        }
    }

    @Override
    public int getMissileCounter() {
        return this.missileCounter;
    }

    @Override
    public void setMissileCounter(int missileCounter) {
        this.missileCounter = missileCounter;
    }

    @Override
    public IShootingMode getShootingMode() {
        return shootingMode;
    }

    @Override
    public void setShootingMode(IShootingMode shootingMode) {
        this.shootingMode = shootingMode;
    }
}
