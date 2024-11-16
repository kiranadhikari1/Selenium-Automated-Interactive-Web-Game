package com.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<String> adventureDeck;
    private List<String> eventDeck;
    private List<String> adventureDeckDiscardPile = new ArrayList<>();
    private List<String> eventDeckDiscardPile = new ArrayList<>();

    public void initAdventureDeck(){
        adventureDeck = new ArrayList<>();

        // Add foe cards
        for (int i=0; i<8; i++){
            adventureDeck.add("F5");
        }
        for (int i=0; i<7; i++){
            adventureDeck.add("F10");
        }
        for (int i=0; i<8; i++){
            adventureDeck.add("F15");
        }
        for (int i=0; i<7; i++){
            adventureDeck.add("F20");
        }
        for (int i=0; i<7; i++){
            adventureDeck.add("F25");
        }
        for (int i=0; i<4; i++){
            adventureDeck.add("F30");
        }
        for (int i=0; i<4; i++){
            adventureDeck.add("F35");
        }
        for (int i=0; i<2; i++){
            adventureDeck.add("F40");
        }
        for (int i=0; i<2; i++){
            adventureDeck.add("F50");
        }
        adventureDeck.add("F70");

        // Add weapon cards
        for (int i=0; i<6; i++){
            adventureDeck.add("D5");
        }
        for (int i=0; i<12; i++){
            adventureDeck.add("H10");
        }
        for (int i=0; i<16; i++){
            adventureDeck.add("S10");
        }
        for (int i=0; i<8; i++){
            adventureDeck.add("B15");
        }
        for (int i=0; i<6; i++){
            adventureDeck.add("L20");
        }
        for (int i=0; i<2; i++){
            adventureDeck.add("E30");
        }
    }

    public void initEventDeck(){
        eventDeck = new ArrayList<>();

        // Add quest cards
        for (int i=0; i<3; i++){
            eventDeck.add("Q2");
        }
        for (int i=0; i<4; i++){
            eventDeck.add("Q3");
        }
        for (int i=0; i<3; i++){
            eventDeck.add("Q4");
        }
        for (int i=0; i<2; i++){
            eventDeck.add("Q5");
        }

        // Add event cards
        for (int i=0; i<2; i++){
            eventDeck.add("Prosperity");
        }
        for (int i=0; i<2; i++){
            eventDeck.add("Queen’s favor");
        }
        eventDeck.add("Plague");
    }

    public int getDeckCount(){
        return getAdventureDeckCount() + getEventDeckCount();
    }

    public int getAdventureDeckCount() {
        return adventureDeck.size();
    }

    public int getEventDeckCount(){
        return eventDeck.size();
    }

    public List<String> getAdventureDeck(){
        return adventureDeck;
    }

    public List<String> getAdventureDeckDiscardPile(){
        return adventureDeckDiscardPile;
    }

    public List<String> getEventDeck(){
        return eventDeck;
    }

    public List<String> getEventDeckDiscardPile(){
        return eventDeckDiscardPile;
    }

    public String drawAdventureCard(){
        replenishAdventureDeck();
        return adventureDeck.remove(0);
    }

    public void replenishAdventureDeck(){
        if (adventureDeck.isEmpty()){
            adventureDeck.addAll(adventureDeckDiscardPile);
            Collections.shuffle(adventureDeck);
            adventureDeckDiscardPile.clear();
        }
    }

    public String drawEventCard(){
        replenishEventDeck();
        return eventDeck.remove(0);
    }

    public void replenishEventDeck(){
        if (eventDeck.isEmpty()){
            eventDeck.addAll(eventDeckDiscardPile);
            Collections.shuffle(adventureDeck);
            eventDeckDiscardPile.clear();
        }
    }

    public void displayEventCard(){
        System.out.println(drawEventCard());
    }
}
