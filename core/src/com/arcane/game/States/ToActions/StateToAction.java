package com.arcane.game.States.ToActions;

import com.arcane.game.Actions.Performance;
import com.arcane.game.States.State;

public interface StateToAction {
    public void tryToAffectAction(Performance performance);
}