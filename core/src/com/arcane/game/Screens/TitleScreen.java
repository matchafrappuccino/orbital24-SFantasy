package com.arcane.game.Screens;

import com.arcane.game.ArcaneOdyssey;
import com.arcane.game.UI.startButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TitleScreen extends ArcaneScreen{

    public final static int StatusCode = ArcaneOdyssey.NotInBattle;
    private Viewport viewport;
    private Stage mainStage;
    private float width;
    private float height;

    public TitleScreen(ArcaneOdyssey mainGame) {
        this.mainGame = mainGame;
        width = mainGame.getTotalWidth();
        height = mainGame.getTotalHeight();
        this.viewport = new FillViewport(width, height);
        this.mainStage = new Stage(viewport);
    }
    @Override
    public void show() {
        startButton button = new startButton(this, new BattleScreen(mainGame));
        Gdx.input.setInputProcessor(mainStage);
        button.setScale(1F, 1F);
        button.setPosition(width / 2 - button.getWidth() / 2, height /2 - button.getHeight() / 2);
        mainStage.addActor(button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4F, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.act();
        mainStage.draw();
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
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public int StatusCode() {
        return StatusCode;
    }
}
