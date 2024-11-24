package com.game;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

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
}

