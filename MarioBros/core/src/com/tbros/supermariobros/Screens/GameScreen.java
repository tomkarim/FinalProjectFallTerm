package com.tbros.supermariobros.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.tbros.supermariobros.MarioBros;

public class GameScreen implements Screen {

    MarioBros game;
    //vars for camera view
    private OrthographicCamera mariocam; //can be adjusted to show what view the current game displays in the GameScreen
    private Viewport gameport; //size of the view in the world, width/height is adjustable in GameScreen
    //Box2D
    private World world;
    private Box2DDebugRenderer dbugger; //allows user to physically see bodies in the world
   //Tiled
    private TiledMap map; //sets this as the tmx map to be loaded
    private TmxMapLoader loader; //loads tmx map
    private OrthogonalTiledMapRenderer renderer; //renders tmx map


    public GameScreen(MarioBros game){
        this.game = game;

        mariocam = new OrthographicCamera();
        gameport = new FitViewport(MarioBros.wwidth, MarioBros.wheight, mariocam); //maintains aspect ratio
        mariocam.position.set(gameport.getWorldWidth() /2, gameport.getWorldHeight()/2, 0); //centers map in the middle of viewport, maximizing use of the screen

        loader = new TmxMapLoader();
        map = loader.load("Map.tmx"); //loads specified tmx map found in assets file
        renderer = new OrthogonalTiledMapRenderer(map); //sets up map to render

        world = new World(new Vector2(0,-10), true); //this is for grav, doSleep = true so doesn't calculate physics for objects at rest (no unnecessary calculations)
        dbugger = new Box2DDebugRenderer();

        BodyDef bodydef = new BodyDef(); //need to define body and its contents before creating it
        FixtureDef fixdef = new FixtureDef(); //need to define fixture first before you can add it to the body
        PolygonShape shape  = new PolygonShape(); //shape for fixture
        Body body; //finally

        for(MapObject obj: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){ //2nd layer is for the ground
            Rectangle rec = ((RectangleMapObject)obj).getRectangle(); //typecasts the objects in layer 2 to a RectangleMapObject
            bodydef.type = BodyDef.BodyType.StaticBody; //static bodies don't move, immune to forces
            bodydef.position.set(rec.getX() + rec.getWidth() /2, rec.getY() + rec.getHeight() /2);
            body = world.createBody(bodydef); //adding body to the world
            shape.setAsBox(rec.getWidth()/2, rec.getHeight()/2); //divided by 2 bc it starts at center
            fixdef.shape = shape;
            body.createFixture(fixdef); //adding fixture to the body

        }



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

    public void update(float t){
        getInput(t);
        mariocam.update();
        renderer.setView(mariocam); //only renders what the camera can see at that instant
    }

    public void getInput(float t){
        if(Gdx.input.isTouched()){ //knows if screen is being clicked
            mariocam.position.x += 100*t; //temp, lets us check out the entire world for now
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
