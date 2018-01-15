package com.tbros.supermariobros;

import java.util.ArrayList; // Used to store goompas.
import com.badlogic.gdx.Game; //used for the game itself.
import com.badlogic.gdx.Gdx; // Used for background.
import com.badlogic.gdx.graphics.GL20; // Used for background.
import com.badlogic.gdx.graphics.Texture; // Used to display sprites.
import com.badlogic.gdx.graphics.g2d.SpriteBatch; // All sprites are contained here.
import com.badlogic.gdx.audio.Music; // Used for background music.
import com.badlogic.gdx.Input; // Used so we can specify our InputManager. This holds the function that'll set the InputManager of our game.
import java.util.Timer; // Used so that we can make the game account for gravity every accouple frames.
import java.util.TimerTask; //Same reason as above.

import com.tbros.supermariobros.Screens.GameScreen;
import com.tbros.supermariobros.dependencies.*; //All of the code we wrote is in this package, so we simply import it all at once.

public class MarioBros extends Game {
	public SpriteBatch batch; // Sprites are held here and displayed.
	public Texture background; // Planning to implement a 2d tile "world".
	public ArrayList<Goompa> goompas; // Used to store goompa data.
	public Player player; // "Mario". This is the player you get to control.
	public Music backgroundMusic; // This is where we tell the the program what to play in the background.
	public InputManager inputWatcher; // InputManager.
	public Timer timer; // Used to tell the game to account for gravity every couple of frames. Work in progress.

	@Override
	public void create () {
		batch = new SpriteBatch(); // All sprites / textures are stored here.
		setScreen(new GameScreen(this));
	//	background = new Texture("background.png"); // Background itself.
	//	background.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge); // Changes world size whenever you change directions
	//	goompas = new ArrayList<Goompa>(); // Store Goompas
	//	timer = new Timer(); // Initialize timer.

	//	backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background-music.mp3")); //Tell the backgroundMusic the source.
	//	backgroundMusic.setLooping(true); // Tell it to play indefinetely
	//	backgroundMusic.play(); // Actually Play the music.

	//	player = new Player(); // Create a player.
	//	goompas.add(new Goompa()); // make a goompa. This is for debugging. Will be commented out later.

	//	inputWatcher = new InputManager(); // Initialize InputManager
	//	Gdx.input.setInputProcessor(inputWatcher); //Tell the game what InputManager to use.

	}

	@Override
	public void render () {
		super.render(); //calls render in the currently active screen (usually PlayScreen)

	}

	//@Override
	//public void dispose () {
	//	batch.dispose();
	//	background.dispose();
	//	backgroundMusic.dispose();
	//	player.dispose();

		//cleans up everything after the game is over.
		
	//}
}
