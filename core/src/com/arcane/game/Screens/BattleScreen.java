package com.arcane.game.Screens;

import com.arcane.game.Actors.Charlotte;
import com.arcane.game.Actors.Dracula;
import com.arcane.game.Actors.Group.Draculas;
import com.arcane.game.ArcaneOdyssey;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

public class BattleScreen extends ArcaneScreen{
    public int StatusCode = ArcaneOdyssey.SelectingCard;
    private Draculas draculas;
    private Charlotte charlotte;
    private Stage stage;
    private Viewport viewport;
    private float width;
    private float height;
    LinkedList<Texture> textures;
    public BattleScreen(ArcaneOdyssey game) {
        this.mainGame = game;
        textures = new LinkedList<>();
        width = mainGame.getTotalWidth();
        height = mainGame.getTotalHeight();
        this.viewport = new FillViewport(width, height);
        this.stage = new Stage(viewport);

        charlotte = new Charlotte(textures, "char.png", width, height);

        draculas = new Draculas(width, height);
        draculas.addActor(new Dracula(textures, "enemy1.png"));
        draculas.addActor(new Dracula(textures, "enemy1.png"));

        stage.addActor(charlotte);
        stage.addActor(draculas);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Texture background = new Texture(Gdx.files.internal("Backgrounds/Grass.png"));
        Batch bg = new SpriteBatch();
        bg.begin();
        bg.draw(background, 0, 0, width, height);
        bg.end();
        stage.act();
        stage.draw();
    }


    @Override
    public int StatusCode() {
        return this.StatusCode;
    }
}
