package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class ToggleThemeCmd extends AbstractGameCommand {
    public ToggleThemeCmd(IGameModel subject) {
        this.subject = subject;
    }
    @Override
    protected void execute() {
        subject.toggleTheme();
    }
}
