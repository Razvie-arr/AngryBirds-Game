package cz.cvut.fit.miadp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Enemy_A;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_B.Enemy_B;
import cz.cvut.fit.miadp.mvcgame.proxy.GameModelProxy;
import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectFactory_A;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.ArrayList;
import java.util.List;

public class TestCaseMock {
    @Test
    public void createMissile( ){
        IGameModel model = mock( GameModelProxy.class );
        when( model.getCannonPosition( ) ).thenReturn( new Position( 555, 666 ) );
        when( model.getMovingStrategy( ) ).thenReturn( new SimpleMovingStrategy( ) );
        IGameObjectFactory goFact = new GameObjectFactory_A( model );
        AbsMissile missile = goFact.createMissile(new Position( 0,0 ), 0, 0 );
        Assert.assertEquals( missile.getPosition( ).getX( ), 555 );
        Assert.assertEquals( missile.getPosition( ).getY( ), 666 );
    }

    @Test
    public void createEnemy() {
        IGameModel model = mock(GameModelProxy.class);
        List<AbsEnemy> enemies = new ArrayList<>();
        enemies.add(new Enemy_A(new Position(0, 0)));
        enemies.add(new Enemy_B(new Position( 100, 100)));
        when(model.getEnemies()).thenReturn(enemies);

        Assert.assertEquals(model.getEnemies(), enemies);
    }

}
