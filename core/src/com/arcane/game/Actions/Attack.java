package com.arcane.game.Actions;

import com.arcane.game.Actors.Character;
import com.badlogic.gdx.scenes.scene2d.Actor;

//All attack actions, either from Charlotte or dracula, follow this interface.
public abstract class Attack {
    protected final int originalAmount;
    protected int curAmount;
    public Attack(int value) {
        originalAmount = value;
        curAmount = value;
    }
    public int getCurAmount() {
        return curAmount;
    }
    public abstract Character getInitiator();
    public abstract Character getTarget();

    public void affectNextAttack(int amount) {
        curAmount = amount;
    }
}
