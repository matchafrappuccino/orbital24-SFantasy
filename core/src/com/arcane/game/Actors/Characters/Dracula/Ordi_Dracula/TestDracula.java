package com.arcane.game.Actors.Characters.Dracula.Ordi_Dracula;

import com.arcane.game.Actions.FromDracula.ToCharlotte.AttackFromDracula;
import com.arcane.game.Actions.Performance;
import com.arcane.game.Actors.Characters.Dracula.DracuActions;
import com.arcane.game.Actors.Characters.Dracula.Dracula;
import com.arcane.game.Actors.Characters.Dracula.Draculas;
import com.arcane.game.Actors.Initializer;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
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
    public void setActions() {
        setActions(new DracuActions(new AttackFromDracula(10,this, getCharlotte())) {
            @Override
            public Performance getNextAction() {
                return super.getNextAction();
            }
        });
    }

    @Override
    public void perform() {
        super.getNextAction().perform();
    }

    @Override
    public void act(float delta) {
        //System.out.println(this.getCurHP());
        if (getCurHP() == 0) {
            System.out.println("delete");
            noHP();
        }
    }

}
