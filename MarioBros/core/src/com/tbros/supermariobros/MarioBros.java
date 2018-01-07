package com.tbros.supermariobros;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Input;
import java.util.Timer;
import java.util.TimerTask;
import com.tbros.supermariobros.dependencies.*;

public class MarioBros extends ApplicationAdapter {
	public SpriteBatch batch;
	public Texture background;
	public ArrayList<Goompa> goompas;
	public Player player;
	public Music backgroundMusic;
	public InputManager inputWatcher;
	public Timer timer;

	@Override
	public void create () {
		background = new Texture("background.png");
		batch = new SpriteBatch();
		background.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
		goompas = new ArrayList<Goompa>();
		timer = new Timer();

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background-music.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.play();

		player = new Player();
		goompas.add(new Goompa());

		inputWatcher = new InputManager();
		Gdx.input.setInputProcessor(inputWatcher);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (inputWatcher.KeyPressed()) {
				player.update(inputWatcher.getKey());
			}

			timer.scheduleAtFixedRate(new TimerTask() {
          public void run() {
						if (player.y > 65) {
				      if (player.y - 65 <= 5) {
				        player.y -= (player.y - 65);
				        player.canJump = true;
				      } else {
				        player.y -= 5;
				      }
				    }
          }
      }, 0, 100000);

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(player, player.x, player.y, 80, 80);
		batch.draw(goompas.get(0), goompas.get(0).x, goompas.get(0).y, 80, 80);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		backgroundMusic.dispose();
	}
}
