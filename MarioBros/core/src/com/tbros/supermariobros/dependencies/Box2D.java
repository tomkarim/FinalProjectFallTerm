package com.tbros.supermariobros.Manager;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.tbros.supermariobros.MarioBros;
import com.tbros.supermariobros.Screens.GameScreen;
import com.tbros.supermariobros.dependencies.Goompa;

public class Box2D {

    public Box2D(GameScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bodydef = new BodyDef(); //need to define body and its contents before creating it
        FixtureDef fixdef = new FixtureDef(); //need to define fixture first before you can add it to the body
        PolygonShape shape  = new PolygonShape(); //shape for fixture
        Body body; //finally

        for(MapObject obj : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){ //2nd layer is for the ground
            Rectangle rec = ((RectangleMapObject)obj).getRectangle(); //typecasts the objects in layer 2 to a RectangleMapObject
            bodydef.type = BodyDef.BodyType.StaticBody; //static bodies don't move, immune to forces
            bodydef.position.set((rec.getX() + rec.getWidth() /2)/ 1, (rec.getY() + rec.getHeight() /2)/1);
            body = world.createBody(bodydef); //adding body to the world
            shape.setAsBox(rec.getWidth()/2, rec.getHeight()/2); //divided by 2 bc it starts at center
            fixdef.shape = shape;
            body.createFixture(fixdef); //adding fixture to the body
        }

        for(MapObject obj : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){ //3rd layer is for the pipes
            Rectangle rec = ((RectangleMapObject)obj).getRectangle(); //typecasts the objects in layer 3 to a RectangleMapObject
            bodydef.type = BodyDef.BodyType.StaticBody; //static bodies don't move, immune to forces
            bodydef.position.set((rec.getX() + rec.getWidth() /2)/1, (rec.getY() + rec.getHeight() /2)/1);
            body = world.createBody(bodydef); //adding body to the world
            shape.setAsBox(rec.getWidth()/2, rec.getHeight()/2); //divided by 2 bc it starts at center
            fixdef.shape = shape;
            body.createFixture(fixdef); //adding fixture to the body
        }

        //temp, will make bricks interactable later
        for(MapObject obj : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){ //5th layer is for the bricks
            Rectangle rec = ((RectangleMapObject)obj).getRectangle(); //typecasts the objects in layer 3 to a RectangleMapObject
            bodydef.type = BodyDef.BodyType.StaticBody; //static bodies don't move, immune to forces
            bodydef.position.set((rec.getX() + rec.getWidth() /2)/1, (rec.getY() + rec.getHeight() /2)/1);
            body = world.createBody(bodydef); //adding body to the world
            shape.setAsBox(rec.getWidth()/2, rec.getHeight()/2); //divided by 2 bc it starts at center
            fixdef.shape = shape;
            body.createFixture(fixdef); //adding fixture to the body
        }




    }
}
