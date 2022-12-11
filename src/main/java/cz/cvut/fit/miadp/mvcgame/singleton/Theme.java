package cz.cvut.fit.miadp.mvcgame.singleton;

public class Theme {
    //Singleton
    private static Theme theme_instance;

    public String theme;

    private Theme() {
    }

    public static synchronized Theme getInstance()
    {
        if (theme_instance == null)
            theme_instance = new Theme();

        return theme_instance;
    }
}
