package com.tbros.supermariobros;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tbros.supermariobros.dependencies.*;

public class MarioBros extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	ArrayList<Texture> goompas;
	Player player;
	Random spawnRate;

	@Override
	public void create () {
		background = new Texture("background.png");
		batch = new SpriteBatch();
		background.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
		goompas = new ArrayList<Texture>();
		spawnRate = new Random();
		player = new Player();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.setInputProcessor(new InputManager());
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(player.getSprite(), player.getXCor(), player.getYCor());
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
