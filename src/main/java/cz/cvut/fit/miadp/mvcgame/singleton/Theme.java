package cz.cvut.fit.miadp.mvcgame.singleton;

public class Theme {
    private static Theme theme_instance = null;

    public String theme;

    private Theme() {
        theme = "classic";
    }

    public static Theme getInstance()
    {
        if (theme_instance == null)
            theme_instance = new Theme();

        return theme_instance;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
