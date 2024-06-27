package com.arcane.game.Screens;

import com.arcane.game.Actors.Cards.Cards.TestCard;
import com.arcane.game.Actors.Cards.HandCards;
import com.arcane.game.Actors.Characters.Dracula.Draculas;
import com.arcane.game.Actors.Characters.Dracula.Ordi_Dracula.TestDracula;
import com.arcane.game.Actors.Initializer;
import com.arcane.game.ArcaneOdyssey;
import com.arcane.game.UI.ConfirmButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private Initializer.CharloSys charlotte;
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

        handCards = new HandCards();
        charlotte = Initializer.initializeCharlotte(textures, "char.png", width, height, handCards);
        handCards.initialize(charlotte.getCharlotte(), draculas);
        ConfirmButton confirmButton = new ConfirmButton("UI/button.png"
                , "UI/buttonPressed.png"
                , charlotte.getCharlotte());

        draculas = new Draculas(width, height, handCards, charlotte.getCharlotte());
        draculas.addActor(Initializer.initializeDracuSys(new TestDracula(textures ,draculas)));
        draculas.addActor(Initializer.initializeDracuSys(new TestDracula(textures ,draculas)));

        stage.addActor(charlotte);
        stage.addActor(draculas);
        stage.addActor(handCards);
        stage.addActor(confirmButton);

        charlotte.getCharlotte().newBattle();
    }

//    public void newRound() {
//        this.handCards.newRound();
//        this.charlotte.getCharlotte().newRound();
//    }

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

        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            handCards.unSelectCard();
        }
    }

    @Override
    public void dispose() {
        this.textures.forEach(Texture::dispose);
    }


    @Override
    public int StatusCode() {
        return this.StatusCode;
    }
}
