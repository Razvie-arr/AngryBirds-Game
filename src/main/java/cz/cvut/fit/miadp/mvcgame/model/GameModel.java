package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectFactory_A;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectFactory_B;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.factory.BossAFactory;
import cz.cvut.fit.miadp.mvcgame.factory.IBossFactory;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Barrier_A;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.singleton.Theme;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.UltraSpeedMovingStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class GameModel implements IGameModel {

    private AbsCannon cannon;
    private List<AbsMissile> missiles;
    private List<AbsEnemy> enemies;
    private List<IObserver> observers;
    private IGameObjectFactory goFact_A;
    private IGameObjectFactory goFact_B;
    private IMovingStrategy movingStrategy;
    private AbsGameInfo gameInfo;
    private IBossFactory bossFactory;
    private List<AbsBarrier> barriers;
    private int score;

    private Queue<AbstractGameCommand> unexecutedCmds;
    private Stack<AbstractGameCommand> executedCmds;

    public GameModel( ) {
        this.observers = new ArrayList<IObserver>( );
        this.goFact_A = new GameObjectFactory_A( this );
        this.goFact_B = new GameObjectFactory_B(this);
        this.bossFactory = new BossAFactory();
        this.cannon = this.goFact_A.createCannon( );
        this.gameInfo = this.goFact_A.createGameInfo();
        this.missiles = new ArrayList<AbsMissile>();
        this.enemies = this.createEnemies();
        this.movingStrategy = new SimpleMovingStrategy( );   
        this.score = 0;
        this.unexecutedCmds = new LinkedBlockingDeque<>();
        this.executedCmds = new Stack<>();
        this.barriers = this.createBarriers();
    }

    private List<AbsEnemy> createEnemies() {
        List<AbsEnemy> enemies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            enemies.add(this.goFact_A.createEnemy());
            enemies.add(this.goFact_B.createEnemy());
        }
        enemies.add(this.bossFactory.createBoss());
        return enemies;
    }

    private List<AbsBarrier> createBarriers() {
        List<AbsBarrier> barriers = new ArrayList<>();
        int barriersSize = this.enemies.size() / 5;
        for (int i = 0; i < barriersSize; i++) {
            barriers.add(this.goFact_A.createBarrier(enemies.get(i).getPosition()));
        }
        return barriers;
    }

    public void update( ) {
        this.executedCmds();
        this.moveMissiles( );
    }

    private void executedCmds() {
        while (!this.unexecutedCmds.isEmpty()) {
            AbstractGameCommand cmd = this.unexecutedCmds.poll();
            cmd.doExecute();
            this.executedCmds.push(cmd);
        }
    }

    private void moveMissiles( ) {
        for ( AbsMissile missile : this.missiles ) {
            missile.move(  );
            checkCollisionWithBarrier(missile);
            decrementEnemiesLifeCounter(missile);
        }
        this.destroyMissiles( );
        this.notifyObservers( );
    }

    private void destroyMissiles( ) {
        List<AbsMissile> missilesToRemove = new ArrayList<AbsMissile>();
        for ( AbsMissile missile : this.missiles ) {
            if( missile.getPosition( ).getX( ) > MvcGameConfig.MAX_X || missile.getPosition().getX() < 0 || missile.getPosition().getY() < 0) {
                missilesToRemove.add( missile );
            }
        }
        this.missiles.removeAll(missilesToRemove);
    }

    private void checkCollisionWithBarrier(AbsMissile missile) {
        for (AbsBarrier barrier : this.barriers) {
            int missileX = missile.getPosition().getX();
            int missileY = missile.getPosition().getY();
            if (missileY >= barrier.getPosition().getY() - 30 && missileY <= barrier.getPosition().getY() + 20) {
                if (missileX >= barrier.getPosition().getX() - 20 && missileX <= barrier.getPosition().getX() + 20) {
                    this.missiles.remove(missile);
                }
            }
        }
    }

    private void decrementEnemiesLifeCounter(AbsMissile missile) {
        List<AbsEnemy> enemiesToDecrement = new ArrayList<>();

        for (AbsEnemy enemy : this.enemies) {
            int missileX = missile.getPosition().getX();
            int missileY = missile.getPosition().getY();

            if (missileY >= enemy.getPosition().getY() - 30 && missileY <= enemy.getPosition().getY() + 20) {
                if (missileX >= enemy.getPosition().getX() - 20 && missileX <= enemy.getPosition().getX() + 20) {
                    enemiesToDecrement.add(enemy);
                    this.missiles.remove(missile);
                }
            }
        }

        for (AbsEnemy enemy : enemiesToDecrement) {
            if (enemy.getLifeCounter() == 1) {
                this.enemies.remove(enemy);
                this.score++;
            } else {
                enemy.decrementLifeCounter();
            }
        }

        this.notifyObservers();
    }

    public Position getCannonPosition( ) {
        return this.cannon.getPosition( );
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public double getCannonAimAngle() {
        return this.cannon.getAngle();
    }

    @Override
    public int getCannonPower() {
        return this.cannon.getPower();
    }

    public void moveCannonUp( ) {
        this.cannon.moveUp( );
        this.notifyObservers( );
    }

    public void moveCannonDown( ) {
        this.cannon.moveDown( );
        this.notifyObservers( );
    }

    public void aimCannonUp( ) {
        this.cannon.aimUp( );
        this.notifyObservers( );
    }

    public void aimCannonDown( ) {
        this.cannon.aimDown( );
        this.notifyObservers( );
    }

    public void cannonPowerUp( ) {
        this.cannon.powerUp( );
        this.notifyObservers( );
    }

    public void cannonPowerDown( ) {
        this.cannon.powerDown( );
        this.notifyObservers( );
    }

    @Override
    public void registerObserver( IObserver obs ) {
        if( !this.observers.contains( obs ) ) {
            this.observers.add( obs );
        }
    }

    @Override
    public void unregisterObserver( IObserver obs ) {
        if( this.observers.contains( obs ) ) {
            this.observers.remove( obs );
        }
    }

    @Override
    public void notifyObservers( ) {
        for( IObserver obs : this.observers ){
            obs.update( );
        }
    }

    public void cannonShoot( ) {
        this.missiles.addAll( cannon.shoot( ) ) ;
        this.notifyObservers( );
    }

    public List<AbsMissile> getMissiles( ) {
        return this.missiles;
    }

    public List<AbsEnemy> getEnemies() { return this.enemies; }

    public List<GameObject> getGameObjects( ) {
        List<GameObject> go = new ArrayList<GameObject>();
        go.add( this.cannon );
        go.add(this.gameInfo);
        go.addAll( this.missiles );
        go.addAll(this.enemies);
        go.addAll(this.barriers);
        return go;
    }

    public IMovingStrategy getMovingStrategy( ){
        return this.movingStrategy;
    }

    private void setMovingStrategy(IMovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void toggleMovingStrategy( ) {
        if ( this.movingStrategy instanceof SimpleMovingStrategy ) {
            this.movingStrategy = new RealisticMovingStrategy( );
        }
        else if ( this.movingStrategy instanceof RealisticMovingStrategy ){
            this.movingStrategy = new UltraSpeedMovingStrategy( );
        }
        else {
            this.movingStrategy = new SimpleMovingStrategy();
        }
    }

    public void toggleTheme() {
        if (Theme.getInstance().theme.equals("classic")) {
             Theme.getInstance().theme = "star-wars";
        } else {
            Theme.getInstance().theme = "classic";
        }
    }

    @Override
    public IShootingMode getShootingMode() { return this.cannon.getShootingMode(); }

    private void setShootingMode(IShootingMode shootingMode) { this.cannon.setShootingMode(shootingMode); }

    public void toggleShootingMode( ){
        this.cannon.toggleShootingMode( );
    }

    @Override
    public void incrementMissileCounter() {
        this.cannon.incrementMissileCounter();
    }

    @Override
    public void decrementMissileCounter() {
        this.cannon.decrementMissileCounter();
    }

    private void setMissileCounter(int missileCounter) {
        this.cannon.setMissileCounter(missileCounter);
    }

    @Override
    public int getMissileCounter() { return this.cannon.getMissileCounter(); }

    public Position getGameInfoPosition() {
        return this.gameInfo.getPosition();
    }

    @Override
    public void cannonUltraRageShoot() {
        this.missiles.addAll(cannon.ultraRageShoot( )) ;
        this.notifyObservers();;
    }


    private class Memento {
        private int cannonX;
        private int cannonY;
        private double angle;
        private IMovingStrategy movingStrategy;
        private IShootingMode shootingMode;
        private int missileCounter;
        private String theme;
    }

    public Object createMemento( ) {
        Memento m = new Memento( );
        m.cannonX = this.getCannonPosition().getX();
        m.cannonY = this.getCannonPosition().getY();
        m.angle = this.getCannonAimAngle();
        m.movingStrategy = this.getMovingStrategy();
        m.shootingMode = this.getShootingMode();
        m.missileCounter = this.getMissileCounter();
        m.theme = Theme.getInstance().theme;
        return m;
    }

    public void setMemento( Object memento ) {
        Memento m = ( Memento ) memento;
        this.cannon.getPosition().setX(m.cannonX);
        this.cannon.getPosition().setY(m.cannonY);
        this.cannon.setAngle(m.angle);
        this.setMovingStrategy(m.movingStrategy);
        this.setShootingMode(m.shootingMode);
        this.setMissileCounter(m.missileCounter);
        Theme.getInstance().theme = m.theme;
    }

    @Override
    public void registerCommand(AbstractGameCommand cmd) {
        unexecutedCmds.add(cmd);
    }

    @Override
    public void undoLastCommand() {
        if (!this.executedCmds.isEmpty()) {
            AbstractGameCommand cmd = this.executedCmds.pop();
            cmd.unExecute();
        }
        this.notifyObservers();
    }

}