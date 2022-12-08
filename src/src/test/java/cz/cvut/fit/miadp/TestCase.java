package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.command.CannonPowerUpCmd;
import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.command.ToggleMovingStrategyCmd;
import cz.cvut.fit.miadp.mvcgame.command.ToggleShootingModeCmd;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.GameInfo_A;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestCase {
    @Test
    public void undoLastCommandTest() {
        IGameModel model = new GameModel( );
        int positionBeforeUndoX = model.getCannonPosition( ).getX( );
        int positionBeforeUndoY = model.getCannonPosition( ).getY( );
        model.registerCommand( new MoveCannonUpCmd( model ) );
        model.update( );
        int positionAfterExcecution = model.getCannonPosition( ).getY( );
        model.undoLastCommand( );
        int positionAfterUndoX = model.getCannonPosition( ).getX( );
        int positionAfterUndoY = model.getCannonPosition( ).getY( );
        assertEquals( positionBeforeUndoY, positionAfterExcecution + MvcGameConfig.MOVE_STEP );
        assertEquals( positionBeforeUndoX, positionAfterUndoX );
        assertEquals( positionBeforeUndoY, positionAfterUndoY );
    }

    @Test
    public void initialGameInfoTest() {
        IGameModel model = new GameModel();
        AbsGameInfo gameInfo = new GameInfo_A(new Position(MvcGameConfig.GAMEINFO_POS_X, MvcGameConfig.GAMEINFO_POS_Y), model);
        assertEquals("Cannon angle: 0.0°\n" +
                "Cannon power: 10\n" +
                "Shooting mode: SingleShootingMode\n" +
                "Moving strategy: SimpleMovingStrategy\n" +
                "Active missiles: 0\n", gameInfo.getText());
    }

    @Test
    public void gameInfoTextChangingTest() {
        IGameModel model = new GameModel();
        AbsGameInfo gameInfo = new GameInfo_A(new Position(MvcGameConfig.GAMEINFO_POS_X, MvcGameConfig.GAMEINFO_POS_Y), model);
        int previousCannonPower = model.getCannonPower();
        String previousShootingModeName = model.getShootingMode().getName();
        String previousMovingStrategyName = model.getMovingStrategy().getName();

        assertEquals(Integer.toString(previousCannonPower), getGameInfoParameterFromText(gameInfo.getText(), "Cannon power"));
        assertEquals(previousShootingModeName, getGameInfoParameterFromText(gameInfo.getText(), "Shooting mode"));
        assertEquals(previousMovingStrategyName, getGameInfoParameterFromText(gameInfo.getText(), "Moving strategy"));

        model.registerCommand(new CannonPowerUpCmd(model));
        model.registerCommand(new ToggleShootingModeCmd(model));
        model.registerCommand(new ToggleMovingStrategyCmd(model));
        model.update();

        assertEquals(Integer.toString(previousCannonPower + 1), getGameInfoParameterFromText(gameInfo.getText(), "Cannon power"));
        assertNotEquals(previousShootingModeName, getGameInfoParameterFromText(gameInfo.getText(), "Shooting mode"));
        assertNotEquals(previousMovingStrategyName, getGameInfoParameterFromText(gameInfo.getText(), "Moving strategy"));

    }

    private String getGameInfoParameterFromText(String text, String parameter) {
        String regex = parameter + ": (.*)\n";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
