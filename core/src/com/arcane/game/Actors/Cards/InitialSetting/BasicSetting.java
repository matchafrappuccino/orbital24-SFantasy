package com.arcane.game.Actors.Cards.InitialSetting;

import com.arcane.game.Actors.Cards.Card;
import com.arcane.game.Actors.Cards.Cards.BasicAttack;
import com.arcane.game.Actors.Cards.HandCards;
import com.arcane.game.Actors.Cards.Cards.TestCard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class BasicSetting extends ArrayList<Card> {
    HashMap<String, Integer> map = new HashMap<>();
    public BasicSetting(HandCards handCards) {
        super();
        getSetting();
        set(handCards);
    }

    public void getSetting() {
        map.put("com.arcane.game.Actors.Cards.Cards.TestCard", 10);
    }

    private void set(HandCards handCards) {
        map.forEach((name, num) -> {
            try {
                Class<?> clazz = Class.forName(name);
                Constructor<?> constructor = clazz.getConstructor(HandCards.class);
                Object instance = constructor.newInstance(handCards);
                if (instance instanceof Card) {
                    for (int i = 0; i < num; i++) {
                        this.add((Card) constructor.newInstance(handCards));
                    }
                }
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
