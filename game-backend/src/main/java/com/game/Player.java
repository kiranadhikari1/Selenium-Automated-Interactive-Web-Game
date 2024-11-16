package com.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerID;
    private List<String> hand;
    private int shieldCount;
    private boolean stageEligibility;

    public Player(String playerID) {
        this.playerID = playerID;
        this.hand = new ArrayList<>();
        this.stageEligibility = true;
    }

    public int handSize(){
        return hand.size();
    }

    public boolean getStageEligibility(){
        return stageEligibility;
    }

    public void setEligibility(boolean eligibility) {
        this.stageEligibility = eligibility;
    }

    public void addCardToHand(String card) {
        hand.add(card);
    }

    public int getShieldCount(){
        return shieldCount;
    }

    public void addShield(int shieldToAdd){
        this.shieldCount = this.shieldCount + shieldToAdd;
    }

    public void removeShield(int shieldToRemove){
        this.shieldCount = this.shieldCount - shieldToRemove; // handle dropping below 0 in REFAC if needed
    }

    public String getPlayerID(){
        return playerID;
    }

    public List<String> getHand(){
        return hand;
    }
}
