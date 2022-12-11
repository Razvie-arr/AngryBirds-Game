package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.bridge.GameGraphics;
import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.bridge.JavaFxGraphics;
import cz.cvut.fit.miadp.mvcgame.MvcGame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MvcGameJavaFxLauncher extends Application {

    private static final MvcGame theMvcGame = new MvcGame( );

    @Override
    public void init( ) {
        theMvcGame.init( );
    }

    @Override
    public void start( Stage stage ) {
        String winTitle = theMvcGame.getWindowTitle( );
        int winWidth = theMvcGame.getWindowWidth( );
        int winHeigth = theMvcGame.getWindowHeight( );
        stage.setTitle( winTitle );
        StackPane root = new StackPane();
        Scene theScene = new Scene(root);
        Image img = new Image(this.getClass().getResource("/images/back.jpg").toString());
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);
        stage.setScene(theScene);

        Canvas canvas = new Canvas( winWidth, winHeigth );
        root.getChildren( ).add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D( );
        IGameGraphics gr = new GameGraphics(new JavaFxGraphics(gc));
        theMvcGame.render( gr );

        ArrayList<String> pressedKeysCodes = new ArrayList<String>( );
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>( ) {
                public void handle( KeyEvent e ) {
                    String code = e.getCode( ).toString( );
                    // only add once... prevent duplicates
                    if ( !pressedKeysCodes.contains( code ) ) {
                        pressedKeysCodes.add( code );
                    }
                }
            }
        );

        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>( ) {
                public void handle( KeyEvent e ) {
                    String code = e.getCode( ).toString( );
                    pressedKeysCodes.remove( code );
                }
            }
        );
        
        // the game-loop
        new AnimationTimer( ){
            public void handle( long currentNanoTime ) {
                theMvcGame.processPressedKeys( pressedKeysCodes );
                pressedKeysCodes.clear();
                theMvcGame.update( );
            }
        }.start( );   
        stage.show( );
    }

    public static void main( String[] args ) {
        launch( );
    }

}