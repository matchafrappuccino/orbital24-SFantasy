package com.arcane.game.Actors.Characters.Ordi_Dracula;

import com.arcane.game.Actors.Characters.Dracula;
import com.arcane.game.Actors.Characters.Draculas;
import com.badlogic.gdx.graphics.Texture;

import java.util.LinkedList;

public class TestDracula extends Dracula {
    public TestDracula(LinkedList<Texture> textures, Draculas draculas) {
        super(textures, "enemy1.png", draculas);
    }

    @Override
    public int getMaxHP() {
        return 100;
    }

    @Override
    public int getInitialHP() {
        return getMaxHP();
    }

    @Override
    public void noHP() {
        super.deleteDracula();
    }

    @Override
    public void act(float delta) {
        if (getCurHP() == 0) {
            noHP();
        }
    }


}
