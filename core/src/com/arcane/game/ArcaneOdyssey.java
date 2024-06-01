package com.arcane.game;

import com.arcane.game.Actors.Charlotte;
import com.arcane.game.Actors.Dracula;
import com.arcane.game.Actors.Group.Draculas;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;
import java.awt.*;
import java.util.LinkedList;

public class ArcaneOdyssey extends InputAdapter implements ApplicationListener {
	OrthographicCamera camera;
	float totalHeight;
	float totalWidth;
	LinkedList<Texture> textures;
	private Charlotte charlotte;
	private Dracula dracula;
	private Texture tempTexture;
	private Draculas draculas;
	private Stage stage;
	private Graphics.DisplayMode currentDisplayMode;

	@Override
	public void create () {
		//Some initializations.
		Gdx.input.setInputProcessor(this); // Initialize input

		//initialize display mode
		currentDisplayMode = Gdx.graphics.getDisplayMode();
		Gdx.graphics.setFullscreenMode(currentDisplayMode);
		totalHeight = currentDisplayMode.height;
		totalWidth = currentDisplayMode.width;

		//Initialize stage and viewport
		Viewport fitViewport = new FillViewport(3200, 1800);
		stage = new Stage(fitViewport);
		//Initialize camera
		camera = new OrthographicCamera(totalWidth, totalHeight);
		//Initialize textures list, to record all the textures to be disposed in the end.
		textures = new LinkedList<>();
		//Initialize enemy group
		draculas = new Draculas();

		charlotte = new Charlotte(textures, "char.png");
		charlotte.setScale(5, 5);

		draculas.addActor(new Dracula(textures, "enemy1.png"));
		draculas.addActor(new Dracula(textures, "enemy1.png"));

		//Add the main actor and enemy group to the stage.
		stage.addActor(charlotte);
		stage.addActor(draculas);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		charlotte.act(Gdx.graphics.getDeltaTime());
		System.out.println(totalHeight);
		System.out.println(totalWidth);

//		batch.begin();
//		charlotte.draw(batch, 1.0F);
//		charlotte.setPosition(-width / 2 + 50, -height / 2 + 20);
//
//		draculas.draw(batch, 1.0F);
		draculas.setPosition(totalWidth - draculas.getWidth() - 10, 0);
//
//		batch.end();

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		//Prevent GPU resources from still being occupied.
		textures.forEach(Texture::dispose);
		//batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return true;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
