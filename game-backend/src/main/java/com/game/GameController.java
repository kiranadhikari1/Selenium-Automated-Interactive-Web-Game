package com.game;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:8081", "http://127.0.0.1:8082", "http://127.0.0.1:8083"})
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    public GameController() {
        this.gameService = new GameService();
    }

    @PostMapping("/start")
    public void gameStart(){
        gameService.initPlayers();
        gameService.initDecks();
        gameService.shuffleDecks();
        gameService.distributeCardsToPlayer();

        new Thread(() -> {
            System.out.println("Game thread created test");
            gameService.playGame();
        }).start();
    }

    @PostMapping("/input")
    public String returnPlayerInput(@RequestParam String input){
        if (input.isBlank()) {
            input = "\n";
        }
        gameService.submitPlayerInput(input);
        return "input submitted";
    }

    @GetMapping("/message")
    public String getNextMessage() {
        return gameService.waitForNewMessage();
    }

    @PostMapping("initFirstScenario")
    public void rigDeckForScenario1(){
        gameService.initPlayers();
        gameService.initDecks();
        gameService.shuffleDecks();
        gameService.distributeCardsToPlayer();

        gameService.initializeFirstScenario();

        new Thread(() -> {
            System.out.println("First scenario initialized. Starting hands and deck have been rigged. Q2 at top"); // remove later
            gameService.playGame();
        }).start();
    }

    @PostMapping("initSecondScenario")
    public void rigDeckForScenario2(){
        gameService.initPlayers();
        gameService.initDecks();
        gameService.shuffleDecks();
        gameService.distributeCardsToPlayer();

        gameService.initializeSecondScenario();

        new Thread(() -> {
            System.out.println("Second scenario initialized. Starting hands and deck have been rigged."); // remove later
            gameService.playGame();
        }).start();
    }

    @PostMapping("initThirdScenario")
    public void rigDeckForScenario3(){
        gameService.initPlayers();
        gameService.initDecks();
        gameService.shuffleDecks();
        gameService.distributeCardsToPlayer();

        gameService.initializeThirdScenario();

        new Thread(() -> {
            System.out.println("Third scenario initialized. Starting hands and deck have been rigged."); // remove later
            gameService.playGame();
        }).start();
    }

    @PostMapping("/initFourthScenario")
    public void rigDeckForScenario4(){
        gameService.initPlayers();
        gameService.initDecks();
        gameService.shuffleDecks();
        gameService.distributeCardsToPlayer();

        gameService.initializeFourthScenario();

        new Thread(() -> {
            System.out.println("Fourth scenario initialized. Starting hands and deck have been rigged."); // remove later
            gameService.playGame();
        }).start();
    }

    @PostMapping("/reset")
    public void resetGame() throws InterruptedException {
        gameService.stopGame();
//        Thread.sleep(1000);
        gameService = new GameService();
    }

    @GetMapping("/playerStats")
    public String getPlayerStats(){
        StringBuilder stats = new StringBuilder("Player|Shields|Cards\n");
        for (Player p: gameService.getPlayers()){
            stats.append(p.getPlayerID()).append("|").append(p.getShieldCount()).append("|").append(p.handSize()).append("\n");
        }
        return stats.toString().trim();
    }

    @GetMapping("/player-hand")
    public List<List<String>> getPlayerHands(){
        List<List<String>> playerHands = new ArrayList<>();
        for (Player player : gameService.getPlayers()){
            playerHands.add(new ArrayList<>(player.getHand()));
        }
        return playerHands;
    }

    @GetMapping("/winners")
    public String getWinners() {
        return gameService.displayWinners();
    }
}
