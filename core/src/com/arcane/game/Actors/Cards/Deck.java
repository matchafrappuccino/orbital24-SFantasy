package com.arcane.game.Actors.Cards;

import com.arcane.game.Actors.Characters.Charlotte;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cards = new ArrayList<>();
    private final ArrayList<Card> drawPile = new ArrayList<>();
    private final ArrayList<Card> discardPile = new ArrayList<>();
    private int numEachRound = 3;
    private final Charlotte charlotte;
    public Deck(Charlotte charlotte) {
        this.charlotte = charlotte;
        initialize();
    }
    public void initialize() {
        this.cards.addAll(charlotte.getSetting());
    }
    public void newBattle() {
        drawPile.addAll(cards);
    }
    public ArrayList<Card> cardsNextRound() {

        if (drawPile.size() < numEachRound) {
            Collections.shuffle(discardPile);
            drawPile.addAll(discardPile);
            discardPile.clear();
        }

        ArrayList<Card> temp = new ArrayList<>(drawPile.subList(0, numEachRound));
        drawPile.subList(0, numEachRound).clear();
        System.out.println(temp.size());
        return temp;
    }

    public void discardCards(ArrayList<Card> cards) {
        discardPile.addAll(cards);
    }

    public void discardCard(Card card) {
        discardPile.add(card);
    }
}
