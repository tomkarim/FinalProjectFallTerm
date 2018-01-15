package com.tbros.supermariobros.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tbros.supermariobros.MarioBros;

public class GameScreen implements Screen {

    private MarioBros game;
    Texture texture;
    private OrthographicCamera mariocam; //can be adjusted to show what view the current game displays in the GameScreen
    private Viewport gameport; //size of the view in the world, width/height is adjustable in GameScreen
    private TiledMap map; //sets this as the tmx map to be loaded
    private TmxMapLoader loader; //loads tmx map
    private OrthogonalTiledMapRenderer renderer; //renders tmx map

    public GameScreen(MarioBros game){
        this.game = game;
        texture = new Texture("background.png"); //sets this image as the new texture that can be viewed when the app is run
        mariocam = new OrthographicCamera();
        gameport = new FitViewport(800, 480, mariocam); //maintains aspect ratio
        loader = new TmxMapLoader();
        map = loader.load("background.tmx"); //loads specified tmx map found in assets file
        renderer = new OrthogonalTiledMapRenderer(map); //renders map
        mariocam.position.set(gameport.getWorldWidth() /2, gameport.getWorldHeight()/2, 0); //centers map in the middle of viewport, maximizing use of the screen

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
        update(delta);


    }

    public void update(float t){
        getInput(t);
        mariocam.update();
        renderer.setView(mariocam); //only renders what the camera can see at that instant
    }

    public void getInput(float t){
        if(Gdx.input.isTouched()){ //knows if screen is being clicked
            mariocam.position.x += 100*t;
        }

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
