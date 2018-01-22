package com.tbros.supermariobros.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tbros.supermariobros.Manager.Box2D;
import com.tbros.supermariobros.MarioBros;
import com.tbros.supermariobros.dependencies.*;

public class GameScreen implements Screen {

    private MarioBros game;
    //vars for camera view
    private OrthographicCamera mariocam; //can be adjusted to show what view the current game displays in the GameScreen
    private Viewport gameport; //size of the view in the world, width/height is adjustable in GameScreen
    //Box2D
    private World world;
    private Box2DDebugRenderer dbugger; //allows user to physically see bodies in the world
    private com.tbros.supermariobros.Manager.Box2D bmaker; //uses Box2D class to make the box2d stuff
   //Tiled
    private TiledMap map; //sets this as the tmx map to be loaded
    private TmxMapLoader loader; //loads tmx map
    private OrthogonalTiledMapRenderer renderer; //renders tmx map
    private Box2D b2dcrt;

    private Mario mario;

    public GameScreen(MarioBros game){
        this.game = game;

        mariocam = new OrthographicCamera();
        gameport = new FitViewport(MarioBros.wwidth/ 1, MarioBros.wheight/1, mariocam); //maintains aspect ratio

        loader = new TmxMapLoader();
        map = loader.load("Map.tmx"); //loads specified tmx map found in assets file
        renderer = new OrthogonalTiledMapRenderer(map); //sets up map to render
        mariocam.position.set(gameport.getWorldWidth() /2, gameport.getWorldHeight()/2, 0); //centers map in the middle of viewport, maximizing use of the screen

        world = new World(new Vector2(0,-200), true); //this is for grav, doSleep = true so doesn't calculate physics for objects at rest (no unnecessary calculations)
        mario = new Mario(world); //adds mario to world we just created
        b2dcrt = new Box2D(this);
        bmaker = new Box2D(this);
        dbugger = new Box2DDebugRenderer(); //sets up bodies/fixtures to render

        world.setContactListener(new Listener());


    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta); //updates screen according to delta time

        Gdx.gl.glClearColor(0,0,0,1); //colors on screen to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears screen

        renderer.render(); //renders map
        dbugger.render(world, mariocam.combined); //renders box2d bodies/fixtures
        game.batch.setProjectionMatrix(mariocam.combined); //knows where the camera is currently showing, only renders what is in view



    }

    public void update(float deltat){
        getInput(deltat);
        world.step(1/60f, 6, 2); //tells Box2D how often it has to calculate the physics. The higher the velocityIteration and positionIteration, the more precise (gets less efficient)
        mariocam.position.x = mario.mbody.getPosition().x;
        mariocam.update();
        renderer.setView(mariocam); //only renders what the camera can see at that instant
    }

    public void getInput(float deltat){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && mario.canJump){ //knows if UP arrow is pressed
            mario.mbody.applyLinearImpulse(new Vector2(0,4000f), mario.mbody.getWorldCenter(), true); //applies LinearImpulse up, making mario jump
            //mario.canJump = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.mbody.getLinearVelocity().x <= 500){ //knows if RIGHT arrow is pressed
            mario.mbody.applyLinearImpulse(new Vector2(100f,0), mario.mbody.getWorldCenter(), true); //applies force to right
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && mario.mbody.getLinearVelocity().x >= -500){ //knows if LEFT arrow is pressed
            mario.mbody.applyLinearImpulse(new Vector2(-100f,0), mario.mbody.getWorldCenter(), true); //applies force to left
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

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }
}
