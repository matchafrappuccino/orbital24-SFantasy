package com.arcane.game.States.ToActions.CommonStates;

import com.arcane.game.Actions.Attack;
import com.arcane.game.Actions.Performance;
import com.arcane.game.States.State;
import com.arcane.game.States.ToActions.StateToAction;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Defence extends State implements StateToAction {
    private final int amount;

    public Defence(int amount, Actor owner, int rounds) {
        super(owner, rounds);        this.amount = amount;
    }

    @Override
    public void tryToAffectAction(Performance performance) {
        if (performance instanceof Attack) {
            Attack attack = (Attack) performance;
            if (attack.getTarget() == getOwner()) {
                attack.affectNextAttack(attack.getCurAmount() - this.amount);
            }
        }
    }
}
