package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.state.DynamicShootingMode;

public class GameInfo_A extends AbsGameInfo {

    private final IGameModel model;

    public GameInfo_A(Position initialPosition, IGameModel model) {
        this.position = initialPosition;
        this.model = model;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getText() {
        StringBuilder gameInfoText = new StringBuilder();
        gameInfoText.append("Cannon angle: ").append(this.model.getCannonAimAngle()).append("°").append("\n");
        gameInfoText.append("Cannon power: ").append(this.model.getCannonPower()).append("\n");
        gameInfoText.append("Shooting mode: ").append(this.model.getShootingMode().getName()).append("\n");
        if (this.model.getShootingMode() instanceof DynamicShootingMode) {
            gameInfoText.append("Missiles in Cannon: ").append(this.model.getMissileCounter()).append("\n");
        }
        gameInfoText.append("Moving strategy: ").append(this.model.getMovingStrategy().getName()).append("\n");
        gameInfoText.append("Active missiles: ").append(this.model.getMissiles().size()).append("\n");

        return gameInfoText.toString();
    }
}
