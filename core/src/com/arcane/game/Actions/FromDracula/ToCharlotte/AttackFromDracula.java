package com.arcane.game.Actions.FromDracula.ToCharlotte;

import com.arcane.game.Actions.Attack;
import com.arcane.game.Actions.Performance;
import com.arcane.game.Actors.Character;
import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Actors.Characters.Dracula.Dracula;


public class AttackFromDracula extends Attack implements Performance {
    Dracula initiator;
    Charlotte target;
    public AttackFromDracula(int value, Dracula initiator, Charlotte target) {
        super(value);
        this.initiator = initiator;
        this.target = target;
    }

    @Override
    public boolean perform() {
        target.affectHP(-curAmount);
        super.curAmount = super.originalAmount;
        return true;
    }

    @Override
    public Character getInitiator() {
        return initiator;
    }

    @Override
    public Character getTarget() {
        return target;
    }
}
