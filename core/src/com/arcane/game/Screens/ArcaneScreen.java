package com.arcane.game.Screens;

import com.arcane.game.ArcaneOdyssey;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;


/*
    The father class of all screens in this game.
    Feature is that it records the main game as a field.
 */

public abstract class ArcaneScreen extends ScreenAdapter{
    ArcaneOdyssey mainGame;
    public ArcaneOdyssey getMainGame() {
        return this.mainGame;
    }

}
