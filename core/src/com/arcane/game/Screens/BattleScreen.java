package com.arcane.game.Screens;

import com.arcane.game.Actors.Cards.Card;
import com.arcane.game.Actors.Cards.HandCards;
import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Actors.Characters.Dracula;
import com.arcane.game.Actors.Characters.Draculas;
import com.arcane.game.ArcaneOdyssey;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

public class BattleScreen extends ArcaneScreen{
    public int StatusCode = ArcaneOdyssey.SelectingCard;
    private Draculas draculas;
    private Charlotte charlotte;
    private HandCards handCards;
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

        handCards = new HandCards(width, height);
        handCards.addActor(new Card("Cards/EmptyCard.png", width, height));
        handCards.addActor(new Card("Cards/EmptyCard.png", width, height));

        stage.addActor(charlotte);
        stage.addActor(draculas);
        stage.addActor(handCards);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Texture background = new Texture(Gdx.files.internal("Backgrounds/Grass.png"));
        Batch bg = new SpriteBatch();
        bg.begin();
        bg.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.end();
        stage.act();
        stage.draw();
    }


    @Override
    public int StatusCode() {
        return this.StatusCode;
    }
}
