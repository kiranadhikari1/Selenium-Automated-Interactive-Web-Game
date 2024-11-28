package com.game;
import java.util.*;

public class GameService {

    private Deck deck;
    private List<Player> players;
    public Scanner scanner;
    private List<List<String>> allQuestCards; // collection of cards of all the stages
    private List<String> totalStagedCards;
    private List<Player> eligibleParticipants;
    private Map<Player, List<String>> eligiblePlayersAttackCards;
    private String currentPlayerInput;
    private boolean waitingForPlayerInput = false;
    private Queue<String> messageQueue;
    private boolean gameIsRunning = true;

    public GameService(){
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.allQuestCards = new ArrayList<>();
        this.totalStagedCards = new ArrayList<>();
        this.eligibleParticipants = new ArrayList<>();
        this.eligiblePlayersAttackCards = new HashMap<>();
        this.currentPlayerInput = "";
        this.waitingForPlayerInput = false;
        this.messageQueue = new LinkedList<>();
    }

    // This is where the main game loop is played out and for testing.
    public static void main(String[] args) {
        GameService game = new GameService();
        game.initPlayers();
        game.initDecks();
        game.shuffleDecks();
        game.distributeCardsToPlayer();

        game.playGame();
    }

    // This is the main game loop function that is responsible for handing the events of the game.
    public void playGame() {
        gameIsRunning = true;
        System.out.println("playGame method is running!");
        logMessage("Game has started!");
        while (checkForWinners().isEmpty() && gameIsRunning) {
            for (Player player : players) {
                if (!gameIsRunning) break;
                System.out.println("Processing turn for: " + player.getPlayerID()); // Debugging
                logMessage(player.getPlayerID() + "'s turn.");
                String drawnEventCard = deck.drawEventCard();
//                System.out.println("Event Card Drawn: " + drawnEventCard);
                logMessage("Event Card Drawn: " + drawnEventCard);
                if (drawnEventCard.equals("Plague")) {
                    plagueCardDrawn(player);
                } else if (drawnEventCard.equals("Queen’s favor")) {
                    handleQueensFavorDrawn(player);
                } else if (drawnEventCard.equals("Prosperity")) {
                    handleProsperityCardDrawn();
                } else if (drawnEventCard.startsWith("Q")) {
                    int totalStages = Integer.parseInt(drawnEventCard.substring(1));
                    Player sponsor = promptPlayersForQuestSponsor(player);
                    if (sponsor != null) {
                        setupQuest(sponsor, totalStages);
                        handleQuest(sponsor, totalStages, drawnEventCard);
                    } else {
                        endQuest(drawnEventCard, player);
                    }
                }
                discardEventCard(drawnEventCard);
                endTurnAndClearDisplay(player);
            }
        }
    }

    // This method initializes the players of the gamem, assigning each player to a unique ID and empty hand.
    public void initPlayers(){
        for (int i=0; i<4; i++){
            String playerID = "P" + (i + 1);
            players.add(new Player(playerID));
        }
    }

    public List<String> getTotalStagedCards(){
        return totalStagedCards;
    }

    public List<List<String>> getAllQuestCards(){
        return allQuestCards;
    }

    // This method calls init functions from Deck class to initialize the game decks.
    public void initDecks(){
        deck.initAdventureDeck();
        deck.initEventDeck();
    }

    // Getter for deck. TO expose the decks to be used in testing.
    public Deck getDeck(){
        return deck;
    }

    // Getter for player list
    public List<Player> getPlayers(){
        return players;
    }

    public Map<Player, List<String>> getEligiblePlayersAttackCards() {
        return eligiblePlayersAttackCards;
    }

    public void distributeCardsToPlayer(){
        for (int i=0; i< players.size(); i++){
            for (int j = 0; j < 12; j++) {
                String drawnCard = deck.drawAdventureCard();
                players.get(i).addCardToHand(drawnCard);
            }
        }
    }

    // This method will check for one or more winners.
    public List<Player> checkForWinners(){
        List<Player> winnerList = new ArrayList<>();
        for (int i=0; i< players.size(); i++){
            if (players.get(i).getShieldCount() >=7){
                winnerList.add(players.get(i));
            }
        }
        return winnerList;
    }

    // This method displays the list of winners if there are any.
    public String displayWinners(){
        List<Player> winnerList = checkForWinners();
        StringBuilder winners = new StringBuilder();
        if (winnerList.isEmpty()){
            return "No winners yet.";
        }
        else{
            for (int i=0; i<winnerList.size(); i++){
                winners.append(winnerList.get(i).getPlayerID()).append(" ");
            }
        }
        return "Winners: " + winners;
    }

    public void shuffleDecks(){
        Collections.shuffle(deck.getAdventureDeck());
        Collections.shuffle(deck.getEventDeck());
    }

    public void plagueCardDrawn(Player currentPlayer){
        currentPlayer.removeShield(2);
    }

    public int computeTotalCardsToDiscard(Player player){
        if (player.handSize() > 12){
            return player.handSize() - 12;
        }
        else{
            return 0;
        }
    }

    public void displayPlayerHand(Player player){
//        System.out.printf("\n" + player.getPlayerID() + ": ");
        logMessage("\n" + player.getPlayerID() + ": ");
        sortHand(player);
    }

    // This methods sorts a given players hand in incrementing order and with Swords appearing before Horses
    public void sortHand(Player player) {
        List<String> fCards = new ArrayList<>();
        List<String> wCards = new ArrayList<>();

        for (String card : player.getHand()) {
            if (card.startsWith("F")) {
                fCards.add(card);
            } else {
                wCards.add(card);
            }
        }

        for (int i = 0; i < fCards.size() - 1; i++) {
            for (int j = 0; j < fCards.size() - i - 1; j++) {
                int currVal = Integer.parseInt(fCards.get(j).substring(1));
                int nextVal = Integer.parseInt(fCards.get(j + 1).substring(1));
                if (nextVal < currVal) {
                    // if curr is greater then we swap positions
                    String tempCardLocation = fCards.get(j);
                    fCards.set(j, fCards.get(j + 1));
                    fCards.set(j + 1, tempCardLocation);
                }
            }
        }

        // sort weapons using comparator | S before H and in increasing order.
        wCards.sort((firstCard, secondCard) -> {
            if (firstCard.startsWith("S") && secondCard.startsWith("H")) {
                return -1;
            } else if (firstCard.startsWith("H") && secondCard.startsWith("S")) {
                return 1;
            }
            // sort against value if same card type
            else {
                return Integer.compare(Integer.parseInt(firstCard.substring(1)), Integer.parseInt(secondCard.substring(1)));
            }
        });

        player.getHand().clear();
        player.getHand().addAll(fCards);
        player.getHand().addAll(wCards);

        // Print hand after sorted
        for (String card : player.getHand()) {
            System.out.printf("%-15s", card);
            logMessage(card);
        }
    }

    public void discardAdventureCard(String adventureCard){
        deck.getAdventureDeckDiscardPile().add(adventureCard);
    }

    public void discardEventCard(String eventCard){
        deck.getEventDeckDiscardPile().add(eventCard);
    }

    public void deleteCardFromHand(Player player, int position){
        String removedCard = player.getHand().remove(position);
        deck.getAdventureDeckDiscardPile().add(removedCard);
        displayPlayerHand(player);
    }

    public void promptPositionForCardRemoval(Player player, int discardCount){
        displayPlayerHand(player);
        for (int i=0; i<discardCount; i++){
//            System.out.print("\nEnter position of the next card to delete: ");
            logMessage("\nEnter position of the next card to delete: ");
//            String position = scanner.nextLine();
            String position = waitForPlayerInput();
            deleteCardFromHand(player, Integer.parseInt(position));
        }
    }

    // UC-03 (A player trims their hand)
    // This is a composite method that trims players hand by calling several implemented singular methods.
    public void trimHand(Player player){
        int discardCount = computeTotalCardsToDiscard(player);
        if (discardCount > 0){
            promptPositionForCardRemoval(player, discardCount);
        }
        else{
            return;
        }
    }

    public void handleQueensFavorDrawn(Player player){
        for (int i=0; i<2; i++){
            String drawnCard = deck.drawAdventureCard();
            player.addCardToHand(drawnCard);
        }
        if (computeTotalCardsToDiscard(player) > 0){
            promptPositionForCardRemoval(player, computeTotalCardsToDiscard(player));
        }
    }

    public void handleProsperityCardDrawn(){
        for (Player player : players){
            for (int i=0; i<2; i++){
                String drawnCard = deck.drawAdventureCard();
                player.addCardToHand(drawnCard);
            }

            if (computeTotalCardsToDiscard(player) > 0){
                promptPositionForCardRemoval(player, computeTotalCardsToDiscard(player));
            }
        }
    }

    public void endTurnAndClearDisplay(Player currentPlayer){
//        System.out.println("\n" + currentPlayer.getPlayerID() + "'s turn has ended, please press the <return> key to end your turn.\n");
        logMessage("\n" + currentPlayer.getPlayerID() + "'s turn has ended, please press the <return> key to end your turn.\n");
//        scanner.nextLine();
        waitForPlayerInput();
//        System.out.println("Clearing display for the next player's turn...");
        logMessage("Clearing display for the next player's turn...");
//        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        logMessage("\n\n\n\n\n\n\n\n\n\n");
    }

    public void displayNextPlayerTurn(Player player){
//        System.out.println("It is now " + player.getPlayerID() + "'s turn.");
        logMessage("It is now " + player.getPlayerID() + "'s turn.");
        displayPlayerHand(player);
    }

    public Player promptPlayersForQuestSponsor(Player currentPlayer) {
        logMessage(currentPlayer.getPlayerID() + ", would you like to sponsor this quest? (y/n)\n");
        String playerResponse = waitForPlayerInput();

        if (playerResponse.equals("y")) {
            logMessage(currentPlayer.getPlayerID() + " is sponsoring this quest YAAAA!\n");
            return currentPlayer; // Return current player if they sponsor
        } else {
            logMessage(currentPlayer.getPlayerID() + " has declined to sponsor this quest.\n");
        }

        int currentPlayerPosition = players.indexOf(currentPlayer); // Grab the current player's position

        for (int i = 1; i < players.size(); i++) {
            int nextPlayerPosition = (currentPlayerPosition + i) % players.size(); // Loop through remaining players
            Player nextPlayer = players.get(nextPlayerPosition);

            logMessage(nextPlayer.getPlayerID() + ", would you like to sponsor this quest? (y/n)\n");
            playerResponse = waitForPlayerInput();

            if (playerResponse.equals("y")) {
                logMessage(nextPlayer.getPlayerID() + " is sponsoring this quest!\n");
                return nextPlayer; // Return the next player if they sponsor
            } else {
                logMessage(nextPlayer.getPlayerID() + " has declined to sponsor this quest.\n");
            }
        }

        logMessage("No sponsors found, quest has ended!\n");
        return null;
    }

    public void endQuest(String qCard, Player currentPlayer){
        discardEventCard(qCard);
        endTurnAndClearDisplay(currentPlayer);
    }

    public String promptSponsorForCardToInclude(Player sponsor){
        displayPlayerHand(sponsor);
//        System.out.println("\nPlease enter the position of the next card to include in this stage or typw 'Quit' to end building this stage.\n");
        logMessage("\nPlease enter the position of the next card to include in this stage or typw 'Quit' to end building this stage.\n");
//        return scanner.nextLine();
        return waitForPlayerInput();
    }

    public boolean isNonEmptyStage(List<String> currentStageCards){
        return !currentStageCards.isEmpty();
    }

    public int determineStageValue(List<String> stageCards){
        int totalStageValue = 0;
        for (String card : stageCards){
            totalStageValue += Integer.parseInt(card.substring(1));
        }
        return totalStageValue;
    }

    public boolean isLargerStageValue(List<String> previousStage, List<String> currentStage){
        int currentStageValue = determineStageValue(currentStage);
        int previousStageValue = determineStageValue(previousStage);

        if (currentStageValue > previousStageValue){
            return true;
        }
        return false;
    }

    public boolean isValidCardForStage(String selectedCard, List<String> currentStageCards){
        if (selectedCard.startsWith("F")){
            for (String card : currentStageCards){
                if (card.startsWith("F")){
//                    System.out.println("\nMust select a sole foe card!\n");
                    logMessage("\nMust select a sole foe card!\n");
                    return false;
                }
            }
            return true;
        }

        if (!(selectedCard.startsWith("F"))){
            if (currentStageCards.contains(selectedCard)){
                System.out.println("Cannot add a " + selectedCard + ". Must select a non-repeated weapon card!\n");
                return false;
            }
            return true;
        }

        return false;
    }

    public boolean handleQuitEntered(String cardPosition, List<String> currentStageCards, List<String> previousStageCards){
        if (cardPosition.equals("Quit")){
            if (!isNonEmptyStage(currentStageCards)){
                System.out.println("\nThis stage cannot be empty!\n");
                logMessage("\nThis stage cannot be empty!\n");
                return false;
            }

            if (!isLargerStageValue(previousStageCards, currentStageCards)){
//                System.out.println("\nThis stage value needs to be greater than the previous stage.\n");
                logMessage("\nThis stage value needs to be greater than the previous stage.\n");
                return false;
            }

//            System.out.println("\nStage is valid and has been created.\n");
            logMessage("\nStage is valid and has been created.\n");
            return true;
        }
        return false;
    }

    public void addCardToStage(String validCard, List<String> currentStageCards){
        currentStageCards.add(validCard);
    }

    // This functions main responsibility is to re-prompt the player until a valid card is selected for that stage then return its position. It makes use of other functions calls as well.
    public int handleRepromptAndReturnValidCardPosition(Player sponsor, List<String> currentStageCards, List<String> previousStageCards){
        boolean isValid = false;
        int validCardPosition = 100; // set to a position that wont ever exist in the sponsors hand
        while (!isValid){
            String cardPosition = promptSponsorForCardToInclude(sponsor);

            if (cardPosition.equals("Quit")){
                if (handleQuitEntered(cardPosition, currentStageCards, previousStageCards)){
                    return -1; // valid stage so we quit and dont prompt again
                }
                else{
                    continue;
                }
            }
            validCardPosition = Integer.parseInt(cardPosition);
            String selectedCard = sponsor.getHand().get(validCardPosition);

            if (isValidCardForStage(selectedCard, currentStageCards)){ // Checks for non-repeated weapon or single foe
                isValid = true;
            }
            else{
//                System.out.println("\nInvalid card, please select a valid one for this stage.\n");
                logMessage("\nInvalid card, please select a valid one for this stage.\n");
            }
        }
        return validCardPosition;
    }

    // UC-06-1
    // This method displays the players hand and prompts the player for the position of the next attack card to use for the current stage or quit.
    public String promptPlayerForAttackCard(Player player){
        displayPlayerHand(player);
//        System.out.println("\nPlease enter the position of the next card to include in this attack or 'Quit' to end building this attack.\n");
        logMessage("\nPlease enter the position of the next card to include in this attack or 'Quit' to end building this attack.\n");
//        return scanner.nextLine();
        return waitForPlayerInput();
    }

    // UC-06-2a1
    // This method checks if the selected card is a valid non-repeated weapon card.
    public boolean isValidAttackCard(String selectedCard, List<String> currentAttackCards){
        if (!(selectedCard.startsWith("F"))){
            if (currentAttackCards.contains(selectedCard)){
                System.out.println("Cannot add a " + selectedCard + ". Must select a non-repeated weapon card!\n");
                return false;
            }
            System.out.println(selectedCard + " has been added.\n");
            return true;
        }
        if (selectedCard.startsWith("F")){
//            System.out.println("\nCannot add a foe card. Please select a valid weapon card!\n");
            logMessage("\nCannot add a foe card. Please select a valid weapon card!\n");
            return false;
        }
        return false;
    }

    // UC-06-2a2
    public void addCardInAttackAndDisplay(String cardToAdd, List<String> currentAttackCards){
        currentAttackCards.add(cardToAdd);
        for (String card : currentAttackCards){
            System.out.println(card + " ");
        }
    }

    // UC-06-sequence
    // This method checks if the current stage attack is empty or not.
    public boolean isEmptyAttack(List<String> currentAttackCards){
        // can be empty or non re-peated weapon cards
        return currentAttackCards.isEmpty();
    }

    // This method is responsible for displaying the different messages if a player enters 'Quit' throughout the attack setup stage.
    public boolean handleQuitEnteredForAttack(String cardPosition, List<String> currentAttackCards){
        if (cardPosition.equals("Quit")){
            if (isEmptyAttack(currentAttackCards)){
//                System.out.println("\nAttack is empty but valid!\n");
                logMessage("\nAttack is empty but valid!\n");
                return true;
            }

//            System.out.println("\nAdded selected card to list of cards for attack.\n");
            logMessage("\nAdded selected card to list of cards for attack.\n");
            return isValidAttackCard(cardPosition, currentAttackCards);
        }
        return false;
    }

    // This method is responsible for handling the re-prompt to the player until they enter a valid card position or a valid attack for the stage.
    public int handleRepromptAndReturnValidAttackCardForCurrentAttack(Player player, List<String> currentAttackCards){
        boolean isValid = false;
        int validCardPosition = 100; // set to a position that wont ever exist in the sponsors hand
        while (!isValid){
            String cardPosition = promptPlayerForAttackCard(player);

            if (cardPosition.equals("Quit")){
                if (handleQuitEnteredForAttack(cardPosition, currentAttackCards)){
                    return -1; // valid stage so we quit and dont prompt again
                }
                else{
                    continue; // re-prompt if invalid card (i.e foe or repeated weapon)
                }
            }
            validCardPosition = Integer.parseInt(cardPosition);
            String selectedCard = player.getHand().get(validCardPosition);

            if (isValidAttackCard(selectedCard, currentAttackCards)){ // Checks for non-repeated weapon or single foe
                isValid = true;
            }
            else{
//                System.out.println("\nInvalid card, please select a valid one for this stage.\n");
                logMessage("\nInvalid card, please select a valid one for this stage.\n");
            }
        }
        return validCardPosition;
    }

    // UC-04-4.1
    // The game determines and displays the set of eligible participants for that stage
    public List<Player> determineEligibleParticipants(){
        this.eligibleParticipants.clear();
        for (Player player : players){
            if (player.getStageEligibility()){ // update this later based on eligibility condition
                eligibleParticipants.add(player);
            }
        }
//        System.out.print("Eligible Players: ");
        logMessage("Eligible Players: ");
        for (Player player : eligibleParticipants){
            System.out.print(player.getPlayerID() + " ");
        }
        return eligibleParticipants;
    }

    // UC-04-4.3
    // Eligible players draw a card and possibly trim their hand.
    public void drawAndTrimHand(Player player){
        player.getHand().add(getDeck().drawAdventureCard());
        trimHand(player);
    }

    // UC-04-4.2
    // prompt player if they want to withdraw or tackle the current stage.
    public void promptPlayerForCurrentStageDecision(List<Player> eligiblePlayers){
        for (Player player : eligiblePlayers){
//            System.out.println(player.getPlayerID() + ", would you like to withdraw from the quest or tackle the current stage of the quest?");
            logMessage(player.getPlayerID() + ", would you like to withdraw from the quest or tackle the current stage of the quest?");
//            String playerResponse = scanner.nextLine();
            String playerResponse = waitForPlayerInput();

            if (playerResponse.equals("withdraw")){
                logMessage(player.getPlayerID() + " has withdrawn from this quest.");
                player.setEligibility(false);
            } else if (playerResponse.equals("tackle")) {
//                System.out.println(player.getPlayerID() + " remains in this quest and will tackle this stage!");
                logMessage(player.getPlayerID() + " remains in this quest and will tackle this stage!");
                drawAndTrimHand(player);
            }
        }
    }

    // Extracted from larger handleQuest method
    public void promptSinglePlayerForStageDecision(Player player) {
        if (!player.getStageEligibility()) {
            System.out.println(player.getPlayerID() + " is not eligible for this stage.");
            return;
        }

        System.out.println(player.getPlayerID() + ", would you like to withdraw from the quest or tackle the current stage of the quest?");
//        String playerResponse = scanner.nextLine();
        String playerResponse = waitForPlayerInput();

        if (playerResponse.equals("withdraw")){
            player.setEligibility(false);
            System.out.println(player.getPlayerID() + " has withdrawn from this quest.");
        } else if (playerResponse.equals("tackle")) {
            System.out.println(player.getPlayerID() + " remains in this quest and will tackle this stage!");
            drawAndTrimHand(player);
        }
    }

    // UC-04-4.8
    // This method checks if the current stage is the final stage of the quest.
    public boolean isFinalStage(int currentStage, int totalStages) {
        return currentStage == totalStages;
    }

    // UC-04-4.6 (UC-04-4.6.1 & UC-04-04.6.2)
    // This method handles player attack and rewards or eliminates them based on the result.
    public void resolveAttack(Player player, List<String> currentAttackCards, int currentStageValue, int currentStage, int totalStages){
        int totalAttackValue = determineStageValue(currentAttackCards);
        if (totalAttackValue < currentStageValue){
            player.setEligibility(false);
//            System.out.println(player.getPlayerID() + " has lost and is not eligible for the remainder of the quest.\n");
            logMessage(player.getPlayerID() + " has lost and is not eligible for the remainder of the quest.\n");
        } else {
//            System.out.println(player.getPlayerID() + " has beat this stage and is eligible for the next one.\n");
            logMessage(player.getPlayerID() + " has beat this stage and is eligible for the next one.\n");
            if (isFinalStage(currentStage, totalStages)){
                player.addShield(totalStages);
//                System.out.println(player.getPlayerID() + " has won and has been awarded ("+ totalStages + ") shields!\n");
                logMessage(player.getPlayerID() + " has won and has been awarded ("+ totalStages + ") shields!\n");
            } else {
                player.setEligibility(true);
            }
        }
    }

    // Added an override method to separate the junit vs cucumber 0-based and 1-based increment for stage count.
    public void resolveAttack(Player player, List<String> currentAttackCards, int currentStageValue, int currentStage, int totalStages, boolean isOneBasedIndexing) {
        if (isOneBasedIndexing) {
            currentStage++;  // Adjust to zero-based indexing
        }
        resolveAttack(player, currentAttackCards, currentStageValue, currentStage, totalStages);
    }

    // UC-04-4.7
    // This method discards the cards used for the most recent attack.
    public void discardStagedAttackCards(List<Player> eligibleParticipants, List<List<String>> attackCardsList){
        for (int i=0; i< eligibleParticipants.size(); i++){
            Player player = eligibleParticipants.get(i);
            List<String> playersAttackCards = attackCardsList.get(i);
            for (String card : playersAttackCards){
                player.getHand().remove(card);
                deck.getAdventureDeckDiscardPile().add(card);
            }
//            System.out.println("\nThe cards used for this attack have been discarded.\n");
            logMessage("\nThe cards used for this attack have been discarded.\n");
        }
    }

    // P1 Starts: F5 F5 F10 F10 F15 D5 H10 H10 B15 B15 L20     After quest 1 ends: P1:F5 F10 F15 F15 F15 F15 F20 F20 F20 F20 F25 F25 F30 L20

    // UC-04-5
    // This method draws new cards for sponsor and trims hand after the quest has finished.
    public void refreshSponsorHand(Player sponsor, int totalCardsToDraw){
        System.out.println("SPONSOR HAND B4 REFRESH:: ");
        displayPlayerHand(sponsor);
        System.out.println("SPONSOR HAND B4 REFRESH ABOVE!~!!!!!!");
        for (int i=0; i<totalCardsToDraw; i++){
            sponsor.addCardToHand(deck.drawAdventureCard());
        }
        trimHand(sponsor);
    }

    // UC-04-5
    // This method discards the cards used by the sponsor during a quest.
    public void discardSponsorStagedCards(Player sponsor, int totalStages){
        List<String> stagedCardsCopy = new ArrayList<>(this.totalStagedCards);
        System.out.println("sponsor hand before discardSponsorStagedCards removes stagedCardsCopy\n");
        displayPlayerHand(sponsor);
//        for (String card : stagedCardsCopy) {
//            sponsor.getHand().remove(card);
//            System.out.println("Removing card: " + card + " from " + sponsor.getPlayerID());
//
//            deck.getAdventureDeckDiscardPile().add(card);
//        }
        System.out.println("\nAll cards that were used to build this quest have been discarded.\n");
        logMessage("\nAll cards that were used to build this quest have been discarded.\n");
        int totalCardsToDraw = this.totalStagedCards.size() + totalStages;
        refreshSponsorHand(sponsor, totalCardsToDraw);
        this.allQuestCards.clear();
        this.totalStagedCards.clear();
    }

    // UC-04-4.4
    // This method checks if there are eligible participants or not for the quest
    public boolean noParticipantsLeft(List<Player> eligiblePlayers){
        return eligiblePlayers.isEmpty();
    }

    // UC-04-4.5 & UC-06
    // This is a small function that calls handleRepromptAndReturnValidAttackCardForCurrentAttack to set up a players attack. It is used in the main game loop
    public List<String> setUpPlayerAttack(Player player) {
        List<String> currentAttackCards = new ArrayList<>();

        while (true) {
            int cardPosition = handleRepromptAndReturnValidAttackCardForCurrentAttack(player, currentAttackCards);

            if (cardPosition == -1) { // quit entered
//                System.out.println("\nAttack for this stage has been set up.\n");
                logMessage("\nAttack for this stage has been set up.\n");
                break;
            } else {
                String selectedCard = player.getHand().get(cardPosition);
                addCardInAttackAndDisplay(selectedCard, currentAttackCards);
                player.getHand().remove(cardPosition);
                discardAdventureCard(selectedCard);
                System.out.println("Current Attack Cards: " + currentAttackCards);
            }
        }
        eligiblePlayersAttackCards.put(player, currentAttackCards);
        return currentAttackCards;
    }

    // This composite method is responsible for calling several methods to set up a valid quest and is part of the main game loop.
    public void setupQuest(Player sponsor, int totalStages) {
        this.allQuestCards.clear();
        this.totalStagedCards.clear();
        this.eligiblePlayersAttackCards.clear();
        for (Player player : players) {
            player.setEligibility(true);
        }
        List<String> previousStageCards = new ArrayList<>();

        for (int i = 1; i <= totalStages; i++) {
//            System.out.println("\nSetting up Stage " + i + " of " + totalStages);
            logMessage("\nSetting up Stage " + i + " of " + totalStages);
            List<String> currentStageCards = new ArrayList<>();

            while (true) {
                int cardPosition = handleRepromptAndReturnValidCardPosition(sponsor, currentStageCards, previousStageCards); // handle re-prompt + valid check until valid or quit is entered
                if (cardPosition == -1) { // catch 'Quit' entered which is return of -1 during any of the setup stages
                    this.allQuestCards.add(new ArrayList<>(currentStageCards));
                    previousStageCards = new ArrayList<>(currentStageCards); // assign to a copy of curr stage cards to keep track of prev cards once we get to next stage
//                    System.out.println("Stage " + i + " is ready.\n");
                    logMessage("Stage " + i + " is ready.\n");
                    break; // continue to next stage
                }
                else{
                    // valid card selected
                    String selectedCard = sponsor.getHand().get(cardPosition);
                    addCardToStage(selectedCard, currentStageCards);
                    sponsor.getHand().remove(cardPosition);
                    System.out.println("Current Stage Cards: " + currentStageCards);
                }
            }
        }
//        System.out.println("\nQuest has been set up and is ready to be played out.\n");
        logMessage("\nQuest has been set up and is ready to be played out.\n");

        // Store the quest cards per stage for quest resolution
        for (List<String> stage : this.allQuestCards) {
            this.totalStagedCards.addAll(stage);
        }
        for (int i = 0; i < this.allQuestCards.size(); i++) {
            List<String> stageCards = this.allQuestCards.get(i);
            System.out.println("Stage " + (i + 1) + ": " + stageCards);
        }
    }

    // This composite method is responsible for calling several small methods to handle a quest and is part of the main game loop.
    public void handleQuest(Player sponsor, int totalStages, String qCard) {
        this.eligibleParticipants = determineEligibleParticipants();
        eligibleParticipants.remove(sponsor); // rmeove sponsor as they wont be participating

        for (int i = 1; i <= totalStages; i++) {
//            System.out.println("Stage: " + i);
            logMessage("Stage: " + i);
            promptPlayerForCurrentStageDecision(eligibleParticipants);
            eligibleParticipants.removeIf(player -> !player.getStageEligibility()); // filter out ineligible participants

            // Check if any players are left
            if (noParticipantsLeft(eligibleParticipants)) {
//                System.out.println("No more eligible participants.");
                logMessage("No more eligible participants.");
                endQuest(qCard, sponsor); // end quest, discard event card and end player turn
                discardSponsorStagedCards(sponsor, totalStages);
                return;
            }

            // Setup attack for the stage
            this.eligiblePlayersAttackCards.clear();
            for (Player player : eligibleParticipants) {
//                System.out.println("\n" + player.getPlayerID() + " is setting up their attack against this stage!");
                logMessage("\n" + player.getPlayerID() + " is setting up their attack against this stage!");
                List<String> attackCards = setUpPlayerAttack(player);
                eligiblePlayersAttackCards.put(player, attackCards);
            }
            for (Map.Entry<Player, List<String>> entry : eligiblePlayersAttackCards.entrySet()) {
//                System.out.println(entry.getKey().getPlayerID() + "'s Attack Cards: " + entry.getValue());
                logMessage(entry.getKey().getPlayerID() + "'s Attack Cards: " + entry.getValue());
            }

            List<String> currentStageCards = allQuestCards.get(i - 1); //  (i stage - 1) for the index position to retrieve cards.
            int currentStageValue = determineStageValue(currentStageCards);
//            System.out.println("Current Stage Value: " + currentStageValue + "\n");
            logMessage("Current Stage Value: " + currentStageValue + "\n");

            resolveAllPlayersAttack(totalStages, eligiblePlayersAttackCards, currentStageValue, i);

            // Check again after resolveeAttack if any players are still left
            if (noParticipantsLeft(eligibleParticipants)) {
//                System.out.println("No more eligible participants.");
                logMessage("No more eligible participants.");
                endQuest(qCard, sponsor); // end quest, discard event card and end player turn
                discardSponsorStagedCards(sponsor, totalStages);
                return;
            }
        }
        discardSponsorStagedCards(sponsor, totalStages);
    }

    // Extracted from larger handleQuest method
    public void resolveAllPlayersAttack(int totalStages, Map<Player, List<String>> eligiblePlayersAttackCards, int currentStageValue, int currentStage) {
        // handle quest resolution (attack vs each stage value)
//        System.out.println("Resolving stage " + currentStage + " with total value: " + currentStageValue);
        logMessage("Resolving stage " + currentStage + " with total value: " + currentStageValue);

        for (Map.Entry<Player, List<String>> entry : eligiblePlayersAttackCards.entrySet()) {
            Player player = entry.getKey();
            List<String> attackCards = entry.getValue();
            resolveAttack(player, attackCards, currentStageValue, currentStage, totalStages);
        }
        eligibleParticipants.removeIf(player -> !player.getStageEligibility());
    }

    // Extracted method from larger handleQuest method
    public void resolveCurrentStage(int currentStage, int totalStages) {
        List<String> currentStageCards = allQuestCards.get(currentStage);
        int currentStageValue = determineStageValue(currentStageCards);

        System.out.println("\nResolving stage " + (currentStage + 1));
        for (Map.Entry<Player, List<String>> entry : eligiblePlayersAttackCards.entrySet()) {
            Player player = entry.getKey();
            List<String> currentAttackCards = entry.getValue();
            resolveAttack(player, currentAttackCards, currentStageValue, currentStage, totalStages, true);
        }
        eligibleParticipants.removeIf(player -> !player.getStageEligibility());
    }

    public void submitPlayerInput(String input) {
        synchronized (messageQueue) {
            currentPlayerInput = input;
            messageQueue.notifyAll();
        }
    }

    public String waitForNewMessage() {
        synchronized (messageQueue) {
            while (messageQueue.isEmpty()) {
                try {
                    messageQueue.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return "Error: Interrupted while waiting.";
                }
            }
            return messageQueue.poll();
        }
    }

    public void logMessage(String message) {
        synchronized (messageQueue) {
            System.out.println("Adding to queue: " + message);
            messageQueue.add(message);
            messageQueue.notifyAll();
        }
    }

    private String waitForPlayerInput() {
        while (currentPlayerInput.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "";
            }
        }
        String input = currentPlayerInput;
        currentPlayerInput = "";
        return input;
    }

    // This method riggs the starting deck + hand for the given scenario
    public void initializeFirstScenario() {
        Player p1 = getPlayers().get(0);
        Player p2 = getPlayers().get(1);
        Player p3 = getPlayers().get(2);
        Player p4 = getPlayers().get(3);

        p1.getHand().clear();
        p2.getHand().clear();
        p3.getHand().clear();
        p4.getHand().clear();

        // Rig each players hand
        p1.addCardToHand("F5");
        p1.addCardToHand("F5");
        p1.addCardToHand("F15");
        p1.addCardToHand("F15");
        p1.addCardToHand("D5");
        p1.addCardToHand("S10");
        p1.addCardToHand("S10");
        p1.addCardToHand("H10");
        p1.addCardToHand("H10");
        p1.addCardToHand("B15");
        p1.addCardToHand("B15");
        p1.addCardToHand("L20");

        p2.addCardToHand("F5");
        p2.addCardToHand("F5");
        p2.addCardToHand("F15");
        p2.addCardToHand("F15");
        p2.addCardToHand("F40");
        p2.addCardToHand("D5");
        p2.addCardToHand("S10");
        p2.addCardToHand("H10");
        p2.addCardToHand("H10");
        p2.addCardToHand("B15");
        p2.addCardToHand("B15");
        p2.addCardToHand("E30");

        p3.addCardToHand("F5");
        p3.addCardToHand("F5");
        p3.addCardToHand("F5");
        p3.addCardToHand("F15");
        p3.addCardToHand("D5");
        p3.addCardToHand("S10");
        p3.addCardToHand("S10");
        p3.addCardToHand("S10");
        p3.addCardToHand("H10");
        p3.addCardToHand("H10");
        p3.addCardToHand("B15");
        p3.addCardToHand("L20");

        p4.addCardToHand("F5");
        p4.addCardToHand("F15");
        p4.addCardToHand("F15");
        p4.addCardToHand("F40");
        p4.addCardToHand("D5");
        p4.addCardToHand("D5");
        p4.addCardToHand("S10");
        p4.addCardToHand("H10");
        p4.addCardToHand("H10");
        p4.addCardToHand("B15");
        p4.addCardToHand("L20");
        p4.addCardToHand("E30");

        deck.getEventDeck().remove("Q4");
        deck.getEventDeck().add(0, "Q4");

        // Rig the deck after initializing.
        deck.getAdventureDeck().add(0, "L20");
        deck.getAdventureDeck().add(0, "F30");
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "B15");
        deck.getAdventureDeck().add(0, "L20");
        deck.getAdventureDeck().add(0, "L20");
        deck.getAdventureDeck().add(0, "F10");
        deck.getAdventureDeck().add(0, "B15");
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "F30");
    }

    public void initializeSecondScenario() {
        Player p1 = getPlayers().get(0);
        Player p2 = getPlayers().get(1);
        Player p3 = getPlayers().get(2);
        Player p4 = getPlayers().get(3);

        p1.getHand().clear();
        p2.getHand().clear();
        p3.getHand().clear();
        p4.getHand().clear();

        p1.addCardToHand("F5");
        p1.addCardToHand("F5");
        p1.addCardToHand("F10");
        p1.addCardToHand("F10");
        p1.addCardToHand("F15");
        p1.addCardToHand("F15");
        p1.addCardToHand("D5");
        p1.addCardToHand("H10");
        p1.addCardToHand("H10");
        p1.addCardToHand("B15");
        p1.addCardToHand("B15");
        p1.addCardToHand("L20");

        p2.addCardToHand("F40");
        p2.addCardToHand("F50");
        p2.addCardToHand("H10");
        p2.addCardToHand("H10");
        p2.addCardToHand("S10");
        p2.addCardToHand("S10");
        p2.addCardToHand("S10");
        p2.addCardToHand("B15");
        p2.addCardToHand("B15");
        p2.addCardToHand("L20");
        p2.addCardToHand("L20");
        p2.addCardToHand("E30");

        p3.addCardToHand("F5");
        p3.addCardToHand("F5");
        p3.addCardToHand("F5");
        p3.addCardToHand("F5");
        p3.addCardToHand("D5");
        p3.addCardToHand("D5");
        p3.addCardToHand("D5");
        p3.addCardToHand("H10");
        p3.addCardToHand("H10");
        p3.addCardToHand("H10");
        p3.addCardToHand("H10");
        p3.addCardToHand("H10");

        p4.addCardToHand("F50");
        p4.addCardToHand("F70");
        p4.addCardToHand("H10");
        p4.addCardToHand("H10");
        p4.addCardToHand("S10");
        p4.addCardToHand("S10");
        p4.addCardToHand("S10");
        p4.addCardToHand("B15");
        p4.addCardToHand("B15");
        p4.addCardToHand("L20");
        p4.addCardToHand("L20");
        p4.addCardToHand("E30");

        deck.getEventDeck().remove("Q3");
        deck.getEventDeck().add(0, "Q3");
        deck.getEventDeck().remove("Q4");
        deck.getEventDeck().add(0, "Q4");

        // Rig the deck after initializing.

        deck.getAdventureDeck().add(0, "L20"); // sponsor draw q2 end
        deck.getAdventureDeck().add(0, "B15"); // sponsor draw q2 end
        deck.getAdventureDeck().add(0, "B15"); // sponsor draw q2 end
        deck.getAdventureDeck().add(0, "S10"); // sponsor draw q2 end
        deck.getAdventureDeck().add(0, "F30"); // sponsor draw q2 end
        deck.getAdventureDeck().add(0, "F25"); // sponsor draw q2 end
        deck.getAdventureDeck().add(0, "F20"); // sponsor draw q2 end
        deck.getAdventureDeck().add(0, "F20"); // sponsor draw q2 end

        deck.getAdventureDeck().add(0, "F25");// random for quest 2 stage 3
        deck.getAdventureDeck().add(0, "F25");// random for quest 2 stage 3
        deck.getAdventureDeck().add(0, "F15");// random for quest 2 stage 2
        deck.getAdventureDeck().add(0, "F15");// random for quest 2 stage 2
        deck.getAdventureDeck().add(0, "D5");// random for quest 2 stage 1
        deck.getAdventureDeck().add(0, "D5");// random for quest 2 stage 1

        deck.getAdventureDeck().add(0, "F30"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F25"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F25"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F20"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F20"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F20"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F20"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F15"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F15"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F10"); // Sponsor drawn card after quest 1
        deck.getAdventureDeck().add(0, "F5"); // Sponsor drawn card after quest 1

        deck.getAdventureDeck().add(0, "F20");
        deck.getAdventureDeck().add(0, "F15");
        deck.getAdventureDeck().add(0, "F15");
        deck.getAdventureDeck().add(0, "F30");
        deck.getAdventureDeck().add(0, "F30");
        deck.getAdventureDeck().add(0, "F10");
        deck.getAdventureDeck().add(0, "F10");
        deck.getAdventureDeck().add(0, "F40");
        deck.getAdventureDeck().add(0, "F5");
    }

    public void initializeThirdScenario(){
        Player p1 = getPlayers().get(0);
        Player p2 = getPlayers().get(1);
        Player p3 = getPlayers().get(2);
        Player p4 = getPlayers().get(3);

        p1.getHand().clear();
        p2.getHand().clear();
        p3.getHand().clear();
        p4.getHand().clear();

        p1.addCardToHand("F5");
        p1.addCardToHand("F5");
        p1.addCardToHand("F10");
        p1.addCardToHand("F10");
        p1.addCardToHand("F15");
        p1.addCardToHand("F15");
        p1.addCardToHand("F20");
        p1.addCardToHand("F20");
        p1.addCardToHand("D5");
        p1.addCardToHand("D5");
        p1.addCardToHand("D5");
        p1.addCardToHand("D5");

        p2.addCardToHand("F25");
        p2.addCardToHand("F30");
        p2.addCardToHand("H10");
        p2.addCardToHand("H10");
        p2.addCardToHand("S10");
        p2.addCardToHand("S10");
        p2.addCardToHand("S10");
        p2.addCardToHand("B15");
        p2.addCardToHand("B15");
        p2.addCardToHand("L20");
        p2.addCardToHand("L20");
        p2.addCardToHand("E30");

        p3.addCardToHand("F25");
        p3.addCardToHand("F30");
        p3.addCardToHand("H10");
        p3.addCardToHand("H10");
        p3.addCardToHand("S10");
        p3.addCardToHand("S10");
        p3.addCardToHand("S10");
        p3.addCardToHand("B15");
        p3.addCardToHand("B15");
        p3.addCardToHand("L20");
        p3.addCardToHand("L20");
        p3.addCardToHand("E30");

        p4.addCardToHand("F25");
        p4.addCardToHand("F30");
        p4.addCardToHand("F70");
        p4.addCardToHand("H10");
        p4.addCardToHand("H10");
        p4.addCardToHand("S10");
        p4.addCardToHand("S10");
        p4.addCardToHand("S10");
        p4.addCardToHand("B15");
        p4.addCardToHand("B15");
        p4.addCardToHand("L20");
        p4.addCardToHand("L20");

        // Event deck rigged cards
        deck.getEventDeck().remove("Q3");
        deck.getEventDeck().add(0, "Q3");
        deck.getEventDeck().remove("Queen’s favor");
        deck.getEventDeck().add(0, "Queen’s favor");
        deck.getEventDeck().remove("Prosperity");
        deck.getEventDeck().add(0, "Prosperity");
        deck.getEventDeck().remove("Plague");
        deck.getEventDeck().add(0, "Plague");
        deck.getEventDeck().remove("Q4");
        deck.getEventDeck().add(0, "Q4");

        // P1 refresh cards quest 2
        deck.getAdventureDeck().add(0, "F40");
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "H10");
        deck.getAdventureDeck().add(0, "H10");
        deck.getAdventureDeck().add(0, "H10");

        // Quest (2)- 3-stage quest
        deck.getAdventureDeck().add(0, "F50");
        deck.getAdventureDeck().add(0, "F40");

        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "S10");

        deck.getAdventureDeck().add(0, "F50");
        deck.getAdventureDeck().add(0, "H10");
        deck.getAdventureDeck().add(0, "B15");

        // P4 queens favor cards drawn
        deck.getAdventureDeck().add(0, "F25");
        deck.getAdventureDeck().add(0, "F30");

        // prosperity drawn cards
        deck.getAdventureDeck().add(0, "D5"); // p4 draws
        deck.getAdventureDeck().add(0, "D5"); // p4 draws
        deck.getAdventureDeck().add(0, "F40"); // p3 draws
        deck.getAdventureDeck().add(0, "B15"); // p3 draws
        deck.getAdventureDeck().add(0, "S10"); // p2 draws
        deck.getAdventureDeck().add(0, "H10"); // p2 draws
        deck.getAdventureDeck().add(0, "F25"); // p1 2 cards drawn
        deck.getAdventureDeck().add(0, "F25"); // p1 2 cards drawn

        deck.getAdventureDeck().add(0, "F15"); // p1 sponsor refresh cards
        deck.getAdventureDeck().add(0, "F15"); // p1 sponsor refresh cards
        deck.getAdventureDeck().add(0, "F15"); // p1 sponsor refresh cards
        deck.getAdventureDeck().add(0, "F15"); // p1 sponsor refresh cards
        deck.getAdventureDeck().add(0, "F10"); // p1 sponsor refresh cards
        deck.getAdventureDeck().add(0, "F10"); // p1 sponsor refresh cards
        deck.getAdventureDeck().add(0, "F5"); // p1 sponsor refresh cards
        deck.getAdventureDeck().add(0, "F5"); // p1 sponsor refresh cards

        deck.getAdventureDeck().add(0, "F20");
        deck.getAdventureDeck().add(0, "F10");
        deck.getAdventureDeck().add(0, "F5");
        deck.getAdventureDeck().add(0, "F20");
        deck.getAdventureDeck().add(0, "F10");
        deck.getAdventureDeck().add(0, "F5");
        deck.getAdventureDeck().add(0, "F25");
        deck.getAdventureDeck().add(0, "F5");
        deck.getAdventureDeck().add(0, "F15");
        deck.getAdventureDeck().add(0, "F20");
        deck.getAdventureDeck().add(0, "F10");
        deck.getAdventureDeck().add(0, "F5");
    }

    public void initializeFourthScenario(){
        Player p1 = getPlayers().get(0);
        Player p2 = getPlayers().get(1);
        Player p3 = getPlayers().get(2);
        Player p4 = getPlayers().get(3);

        p1.getHand().clear();
        p2.getHand().clear();
        p3.getHand().clear();
        p4.getHand().clear();

        p1.addCardToHand("F50");
        p1.addCardToHand("F70");
        p1.addCardToHand("D5");
        p1.addCardToHand("D5");
        p1.addCardToHand("H10");
        p1.addCardToHand("H10");
        p1.addCardToHand("S10");
        p1.addCardToHand("S10");
        p1.addCardToHand("B15");
        p1.addCardToHand("B15");
        p1.addCardToHand("L20");
        p1.addCardToHand("L20");

        p2.addCardToHand("F5");
        p2.addCardToHand("F5");
        p2.addCardToHand("F10");
        p2.addCardToHand("F15");
        p2.addCardToHand("F15");
        p2.addCardToHand("F20");
        p2.addCardToHand("F20");
        p2.addCardToHand("F25");
        p2.addCardToHand("F30");
        p2.addCardToHand("F30");
        p2.addCardToHand("F40");
        p2.addCardToHand("E30");

        p3.addCardToHand("F5");
        p3.addCardToHand("F5");
        p3.addCardToHand("F10");
        p3.addCardToHand("F15");
        p3.addCardToHand("F15");
        p3.addCardToHand("F20");
        p3.addCardToHand("F20");
        p3.addCardToHand("F25");
        p3.addCardToHand("F25");
        p3.addCardToHand("F30");
        p3.addCardToHand("F40");
        p3.addCardToHand("L20");

        p4.addCardToHand("F5");
        p4.addCardToHand("F5");
        p4.addCardToHand("F10");
        p4.addCardToHand("F15");
        p4.addCardToHand("F15");
        p4.addCardToHand("F20");
        p4.addCardToHand("F20");
        p4.addCardToHand("F25");
        p4.addCardToHand("F25");
        p4.addCardToHand("F30");
        p4.addCardToHand("F50");
        p4.addCardToHand("E30");

        deck.getEventDeck().remove("Q2");
        deck.getEventDeck().add(0, "Q2");


        // sponsor draws 14 cards after quest (P1)
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "S10");
        deck.getAdventureDeck().add(0, "H10");
        deck.getAdventureDeck().add(0, "H10");
        deck.getAdventureDeck().add(0, "H10");
        deck.getAdventureDeck().add(0, "H10");
        deck.getAdventureDeck().add(0, "D5");
        deck.getAdventureDeck().add(0, "D5");
        deck.getAdventureDeck().add(0, "D5");
        deck.getAdventureDeck().add(0, "D5");
        deck.getAdventureDeck().add(0, "F15");
        deck.getAdventureDeck().add(0, "F10");
        deck.getAdventureDeck().add(0, "F5");

        deck.getAdventureDeck().add(0, "F10");
        deck.getAdventureDeck().add(0, "F15");
        deck.getAdventureDeck().add(0, "F5");

    }

    public void stopGame() {
        gameIsRunning = false;
    }
}