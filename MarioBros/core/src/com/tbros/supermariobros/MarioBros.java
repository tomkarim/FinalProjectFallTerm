package com.tbros.supermariobros;

import java.util.ArrayList; // Used to store goompas.
import com.badlogic.gdx.ApplicationAdapter; // Used for the game itself.
import com.badlogic.gdx.Gdx; // Used for background.
import com.badlogic.gdx.graphics.GL20; // Used for background.
import com.badlogic.gdx.graphics.Texture; // Used to display sprites.
import com.badlogic.gdx.graphics.g2d.SpriteBatch; // All sprites are contained here.
import com.badlogic.gdx.audio.Music; // Used for background music.
import com.badlogic.gdx.Input; // Used so we can specify our InputManager. This holds the function that'll set the InputManager of our game.
import java.util.Timer; // Used so that we can make the game account for gravity every accouple frames.
import java.util.TimerTask; //Same reason as above.
import com.tbros.supermariobros.dependencies.*; //All of the code we wrote is in this package, so we simply import it all at once.

public class MarioBros extends ApplicationAdapter {
	public SpriteBatch batch; // Sprites are held here and displayed.
	public Texture background; // Planning to implement a 2d tile "world".
	public ArrayList<Goompa> goompas; // Used to store goompa data.
	public Player player; // "Mario". This is the player you get to control.
	public Music backgroundMusic; // This is where we tell the the program what to play in the background.
	public InputManager inputWatcher; // InputManager.
	public Timer timer; // Used to tell the game to account for gravity ever accouple frames. Work in progress.

	@Override
	public void create () {
		background = new Texture("background.png"); // Background itself.
		batch = new SpriteBatch(); // All sprites / textures are stored here.
		background.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge); // Changes world size whenever you change directions
		goompas = new ArrayList<Goompa>(); // Store Goompas
		timer = new Timer(); // Initialize timer.

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background-music.mp3")); //Tell the backgroundMusic the source.
		backgroundMusic.setLooping(true); // Tell it to play indefinetely
		backgroundMusic.play(); // Actually Play the music.

		player = new Player(); // Create a player.
		goompas.add(new Goompa()); // make a goompa. This is for debugging. Will be commented out later.

		inputWatcher = new InputManager(); // Initialize InputManager
		Gdx.input.setInputProcessor(inputWatcher); //Tell the game what InputManager to use.

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0); // Black background
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (inputWatcher.KeyPressed()) { // If there was a key press..
				player.update(inputWatcher.getKey()); // Update the player's location.
			}

			timer.scheduleAtFixedRate(new TimerTask() {
          public void run() {
						if (player.y > 65) { // If player is above ground
				      if (player.y - 65 <= 5) { // If the player is less than 5 pixels away from the background ...
				        player.y -= (player.y - 65); // Then move them down that many units down. This prevents the player from falling under the world.
				        player.canJump = true; // Now they can jump.
				      } else { // they are still gonna keep falling.
				        player.y -= 5; // Move them down. They are still in the air so dont let them jump.
				      }
				    }
          }
      }, 0, 100000);

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Add background
		batch.draw(player, player.x, player.y, 80, 80); // Spawn player
		batch.draw(goompas.get(0), goompas.get(0).x, goompas.get(0).y, 80, 80); // Spawn one goompa. This is for debugging and will commented out for the final version.
		batch.end(); // Stop drawing
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		backgroundMusic.dispose();
		player.dispose();

		//cleans up everything after the game is over.
		
	}
}
