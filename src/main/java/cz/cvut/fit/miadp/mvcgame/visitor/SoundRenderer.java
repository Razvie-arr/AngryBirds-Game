package cz.cvut.fit.miadp.mvcgame.visitor;
import cz.cvut.fit.miadp.mvcgame.SoundPlayer;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsMissile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.IOException;

public class SoundRenderer implements IVisitor {
    private GraphicsContext gr;
    private final SoundPlayer missileSound = new SoundPlayer("missile");
    private final SoundPlayer cannonSound = new SoundPlayer("cannon");

    public void setGraphicContext(GraphicsContext gr) {
        this.gr = gr;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        cannonSound.playSound();
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        missileSound.playSound();
    }
}
