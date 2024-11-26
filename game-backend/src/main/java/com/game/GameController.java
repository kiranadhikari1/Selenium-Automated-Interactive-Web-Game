package com.game;
import org.springframework.web.bind.annotation.*;
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

//        gameService.getDeck().getEventDeck().add("Q2");

        System.out.println("Game start test"); // remove later
        new Thread(() -> {
            System.out.println("Game thread created test"); // remove later
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

        System.out.println("Game start test"); // remove later
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

    @PostMapping("/reset")
    public void resetGame() throws InterruptedException {
        gameService.stopGame();
//        Thread.sleep(1000);
        gameService = new GameService();
    }
}

