package com.tbros.supermariobros.dependencies;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.tbros.supermariobros.Screens.GameScreen;

public abstract class InteractableObj {
        private World world;
        private TiledMap map;
        private TiledMapTile tiles;
        private Body body;
        private Fixture fixture;
        private Rectangle border;
        private GameScreen screen;
        private MapObject obj;

        public InteractableObj(GameScreen screen, MapObject obj){
            this.obj = obj;
            this.screen = screen;
            this.world = screen.getWorld();
            this.map = screen.getMap();
            this.border = ((RectangleMapObject) obj).getRectangle();

            //creating the body and fixtures for the interactable block

            BodyDef block = new BodyDef();
            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape blockshape = new PolygonShape();
            block.type = BodyDef.BodyType.StaticBody;
            block.position.set((border.getX() + border.getWidth() / 2), border.getY() + border.getHeight() /2 );

            body = world.createBody(block);
            blockshape.setAsBox(border.getWidth() / 2 , border.getHeight() / 2);
            fixtureDef.shape = blockshape;
            fixture = body.createFixture(fixtureDef);
            
        }

        public TiledMapTileLayer.Cell getCell() {
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
            return layer.getCell((int) (body.getPosition().x / 16), (int) body.getPosition().y / 16);
        }




    }

