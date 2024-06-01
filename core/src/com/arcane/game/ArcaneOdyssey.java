package com.arcane.game;

import com.arcane.game.Actors.Charlotte;
import com.arcane.game.Actors.Dracula;
import com.arcane.game.Actors.Group.Draculas;
import com.arcane.game.Screens.ArcaneScreen;
import com.arcane.game.Screens.TitleScreen;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Graphics;
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

public class ArcaneOdyssey extends Game {
	public final static int NotInBattle = -1;
	public final static int SelectingCard = 0;
	public final static int CardSelected = 1;
	private float totalHeight;
	private float totalWidth;
	LinkedList<Texture> textures;
	private Charlotte charlotte;
	private Draculas draculas;
	private Graphics.DisplayMode currentDisplayMode;
	private ArcaneScreen curScreen;
	private int statusCode;

	@Override
	public void create () {
		//Some initializations.
		//initialize display mode
		currentDisplayMode = Gdx.graphics.getDisplayMode();
		Gdx.graphics.setFullscreenMode(currentDisplayMode);
		totalHeight = currentDisplayMode.height;
		totalWidth = currentDisplayMode.width;

		//Initialize start screen
		this.setScreen(new TitleScreen(this));

		//Initialize status
		statusCode = this.getScreen().StatusCode();

	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			dispose();
		}
		screen.render(Gdx.graphics.getDeltaTime());
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
		Gdx.app.exit();
	}


	public float getTotalWidth() {
		return totalWidth;
	}

	public float getTotalHeight() {
		return totalHeight;
	}

	@Override
	public ArcaneScreen getScreen() {
		return (ArcaneScreen) super.getScreen();
	}

	public void setScreen (ArcaneScreen screen) {
		super.setScreen(screen);
		this.statusCode = screen.StatusCode();
	}

}
