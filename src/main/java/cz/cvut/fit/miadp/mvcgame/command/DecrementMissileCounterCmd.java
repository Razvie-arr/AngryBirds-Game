package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class DecrementMissileCounterCmd extends AbstractGameCommand {

    public DecrementMissileCounterCmd(IGameModel subject) {
        this.subject = subject;
    }
    @Override
    protected void execute() {
        subject.decrementMissileCounter();
    }
}
