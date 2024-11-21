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

    @GetMapping("/test")
    public String testService() {
        return gameService.testMethod();
    }

    @PostMapping("/start")
    public void gameStart(){
        gameService.initPlayers();
        gameService.initDecks();
        gameService.shuffleDecks();
        gameService.distributeCardsToPlayer();

        System.out.println("Game start test"); // remove later
        new Thread(() -> {
            System.out.println("Game thread created test"); // remove later
            gameService.playGame();
        }).start();
    }
//
//    @GetMapping("/playerPrompt")
//    public String getInput(){
//        if (gameService.isWaitingForInput()){
//            return gameService.getCurrentInput();
//        }
//        return "proceed";
//    }
//
//    @PostMapping("/input")
//    public String returnPlayerInput(String input){
//        gameService.submitPlayerInput(input);
//        return "input submitted";
//    }

    @GetMapping("/messageLogs")
    public List<String> getLogs(){
        return gameService.getMessageLogs();
    }
}

