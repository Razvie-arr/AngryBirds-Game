package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class AbsMissile extends GameObject {
    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }
}
