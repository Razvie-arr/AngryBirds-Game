package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.model.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.AbsMissile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameRenderer implements IVisitor {

    private GraphicsContext gr;

    public GameRenderer() {
    }

    public void setGraphicContext(GraphicsContext gr) {
        this.gr = gr;
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        gr.drawImage(new Image("images/cannon.png"), cannon.getPosition().getX(), cannon.getPosition().getY());

    }

    @Override
    public void visitMissile(AbsMissile missile) {
        gr.drawImage(new Image("images/missile.png"), missile.getPosition().getX(), missile.getPosition().getY());

    }
}