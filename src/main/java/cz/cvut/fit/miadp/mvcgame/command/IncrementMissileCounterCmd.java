package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class IncrementMissileCounterCmd extends AbstractGameCommand {

    public IncrementMissileCounterCmd(IGameModel subject) {
        this.subject = subject;
    }
    @Override
    protected void execute() {
        subject.incrementMissileCounter();
    }
}
