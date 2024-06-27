package com.arcane.game.Actors.Characters.Dracula;

import com.arcane.game.Actions.Performance;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/*This is to maintain all the performances a dracula can have
    , and also decide the performance to take at each round.
 */
public abstract class DracuActions {
    public static interface Chooser {
        public Performance chooseFrom(ArrayList<Performance> skills);
    }

    //Basic frame
    ArrayList<Performance> skills;
    Chooser chooser;
    public DracuActions(ArrayList<Performance> skills, Chooser chooser) {
        this.skills = skills;
        this.chooser = chooser;
    }

    public Performance getNextAction() {
        return chooser.chooseFrom(skills);
    }


    //For convenience:

    //For cases where the dracula only has one skill
    public DracuActions(Performance skill) {
        this.skills = new ArrayList<>(Collections.singletonList(skill));
        this.chooser = new Chooser() {
            @Override
            public Performance chooseFrom(ArrayList<Performance> skills) {
                return skills.get(0);
            }
        };
    }

    //Chooser that chooses randomly.
    public static class StochasticChooser implements Chooser {
        @Override
        public Performance chooseFrom(ArrayList<Performance> skills) {
            Random random = new Random();
            int randomIndex = random.nextInt(skills.size());
            return skills.get(randomIndex);
        }
    }
}
