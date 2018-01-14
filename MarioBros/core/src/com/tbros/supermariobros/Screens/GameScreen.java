package com.tbros.supermariobros.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tbros.supermariobros.MarioBros;

public class GameScreen implements Screen {

    private MarioBros game;
    Texture texture;
    private OrthographicCamera mariocam; //can be adjusted to show what view the current game displays in the GameScreen
    private Viewport gameport; //size of the view in the world, width/height is adjustable in GameScreen


    public GameScreen(MarioBros game){
        this.game = game;
        texture = new Texture("background.png"); //sets this image as the new texture that can be viewed when the app is run
        mariocam = new OrthographicCamera();
        gameport = new StretchViewport(800, 480, mariocam);



    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1); //colors on screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen
        game.batch.setProjectionMatrix(mariocam.combined); //knows where the camera is currently showing, only renders what is in view
        game.batch.begin(); //draws texture (spec. in GameScreen) to screen
        game.batch.draw(texture, 0, 0); //sets coordinates for where the texture is drawn
        game.batch.end();//closes box




    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height); //adjusts viewport based on monitor size

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
