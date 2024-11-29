package com.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest{

    @Test
    @DisplayName("A-TEST JP-Scenario")
    void aTest() throws InterruptedException, IOException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://127.0.0.1:8081");

        // Initialize all buttons using their ID's
        WebElement resetButton = webDriver.findElement(By.id("reset-button"));
        WebElement firstScenarioButton = webDriver.findElement(By.id("rig-1"));

        WebElement yesButton = webDriver.findElement(By.id("y-button"));
        WebElement noButton = webDriver.findElement(By.id("n-button"));
        WebElement quitButton = webDriver.findElement(By.id("quit-button"));
        WebElement tackleButton = webDriver.findElement(By.id("tackle-button"));
        WebElement withdrawButton = webDriver.findElement(By.id("withdraw-button"));
        WebElement enterButton = webDriver.findElement(By.id("enter-button"));

        WebElement cardPos0 = webDriver.findElement(By.id("pos-0"));
        WebElement cardPos1 = webDriver.findElement(By.id("pos-1"));
        WebElement cardPos2 = webDriver.findElement(By.id("pos-2"));
        WebElement cardPos3 = webDriver.findElement(By.id("pos-3"));
        WebElement cardPos4 = webDriver.findElement(By.id("pos-4"));
        WebElement cardPos5 = webDriver.findElement(By.id("pos-5"));
        WebElement cardPos6 = webDriver.findElement(By.id("pos-6"));
        WebElement cardPos7 = webDriver.findElement(By.id("pos-7"));
        WebElement cardPos8 = webDriver.findElement(By.id("pos-8"));
        WebElement cardPos9 = webDriver.findElement(By.id("pos-9"));
        WebElement cardPos10 = webDriver.findElement(By.id("pos-10"));
        WebElement cardPos11 = webDriver.findElement(By.id("pos-11"));

        WebElement p1ShieldElement = webDriver.findElement(By.id("p1-shieldCount"));
        WebElement p2ShieldElement = webDriver.findElement(By.id("p2-shieldCount"));
        WebElement p3ShieldElement = webDriver.findElement(By.id("p3-shieldCount"));
        WebElement p4ShieldElement = webDriver.findElement(By.id("p4-shieldCount"));

        // Start clicking simulation
        resetButton.click();
        Thread.sleep(1000);
        firstScenarioButton.click();
        Thread.sleep(1000);
        // assert game begins/initialized, etc.

        noButton.click();
        Thread.sleep(1000);
        yesButton.click();
        Thread.sleep(1000);
        cardPos7.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos1.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos1.click();
        Thread.sleep(1000);
        cardPos2.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos1.click();
        Thread.sleep(1000);
        cardPos2.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos8.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos8.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);

        // Asserts Below

        List<String> p1ExpectedFinalHand = new ArrayList<>();
        p1ExpectedFinalHand.add("F5");
        p1ExpectedFinalHand.add("F10");
        p1ExpectedFinalHand.add("F15");
        p1ExpectedFinalHand.add("F15");
        p1ExpectedFinalHand.add("F30");
        p1ExpectedFinalHand.add("H10");
        p1ExpectedFinalHand.add("B15");
        p1ExpectedFinalHand.add("B15");
        p1ExpectedFinalHand.add("L20");

        List<String> p3ExpectedFinalHand = new ArrayList<>();
        p3ExpectedFinalHand.add("F5");
        p3ExpectedFinalHand.add("F5");
        p3ExpectedFinalHand.add("F15");
        p3ExpectedFinalHand.add("F30");
        p3ExpectedFinalHand.add("S10");

        List<String> p4ExpectedFinalHand = new ArrayList<>();
        p4ExpectedFinalHand.add("F15");
        p4ExpectedFinalHand.add("F15");
        p4ExpectedFinalHand.add("F40");
        p4ExpectedFinalHand.add("L20");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:8080/game/player-hand")).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<String>> playerHands = objectMapper.readValue(response.body(), List.class);

        List<String> p1Hand = playerHands.get(0);
        List<String> p2Hand = playerHands.get(1);
        List<String> p3Hand = playerHands.get(2);
        List<String> p4Hand = playerHands.get(3);

        String p1ShieldCount = p1ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p2ShieldCount = p2ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p3ShieldCount = p3ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p4ShieldCount = p4ShieldElement.getText().split(":")[1].split("\\|")[0].trim();

        assertEquals(0, Integer.parseInt(p1ShieldCount));
        assertEquals(0, Integer.parseInt(p2ShieldCount));
        assertEquals(0, Integer.parseInt(p3ShieldCount));
        assertEquals(4, Integer.parseInt(p4ShieldCount));

        assertEquals(p1ExpectedFinalHand, p1Hand);
        assertEquals(9, p1Hand.size());

        assertEquals(12, p2Hand.size());

        assertEquals(p3ExpectedFinalHand, p3Hand);
        assertEquals(5, p3Hand.size());

        assertEquals(p4ExpectedFinalHand, p4Hand);
        assertEquals(4, p4Hand.size());

        Thread.sleep(20000); // wait 20s to review game
        webDriver.quit();
    }

    @Test
    @DisplayName("2winner_game_2winner_quest")
    void scenario2Test() throws InterruptedException, IOException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://127.0.0.1:8081");

        // Initialize all buttons using their ID's
        WebElement resetButton = webDriver.findElement(By.id("reset-button"));
        WebElement secondScenarioButton = webDriver.findElement(By.id("rig-2"));

        WebElement yesButton = webDriver.findElement(By.id("y-button"));
        WebElement noButton = webDriver.findElement(By.id("n-button"));
        WebElement quitButton = webDriver.findElement(By.id("quit-button"));
        WebElement tackleButton = webDriver.findElement(By.id("tackle-button"));
        WebElement withdrawButton = webDriver.findElement(By.id("withdraw-button"));
        WebElement enterButton = webDriver.findElement(By.id("enter-button"));

        WebElement cardPos0 = webDriver.findElement(By.id("pos-0"));
        WebElement cardPos1 = webDriver.findElement(By.id("pos-1"));
        WebElement cardPos2 = webDriver.findElement(By.id("pos-2"));
        WebElement cardPos3 = webDriver.findElement(By.id("pos-3"));
        WebElement cardPos4 = webDriver.findElement(By.id("pos-4"));
        WebElement cardPos5 = webDriver.findElement(By.id("pos-5"));
        WebElement cardPos6 = webDriver.findElement(By.id("pos-6"));
        WebElement cardPos7 = webDriver.findElement(By.id("pos-7"));
        WebElement cardPos8 = webDriver.findElement(By.id("pos-8"));
        WebElement cardPos9 = webDriver.findElement(By.id("pos-9"));
        WebElement cardPos10 = webDriver.findElement(By.id("pos-10"));
        WebElement cardPos11 = webDriver.findElement(By.id("pos-11"));

        WebElement p1ShieldElement = webDriver.findElement(By.id("p1-shieldCount"));
        WebElement p2ShieldElement = webDriver.findElement(By.id("p2-shieldCount"));
        WebElement p3ShieldElement = webDriver.findElement(By.id("p3-shieldCount"));
        WebElement p4ShieldElement = webDriver.findElement(By.id("p4-shieldCount"));

        // Start clicking simulation
        resetButton.click();
        Thread.sleep(1000);
        secondScenarioButton.click();
        Thread.sleep(1000);
        // assert game begins/initialized, etc.

        // y 0 quit 0 4 quit 0 3 quit 0 3 quit | Stage setup
        yesButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // Stage 1
        // stage 1: tackle 0 tackle 0 tackle 0 2 quit quit
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos2.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos2.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // Stage 2: 4 quit 4 quit
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // stage 3: 4 4 quit 4 4 quit
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // Stage 4: 5 5 quit 5 5 quit
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(30000);

        // p1 refreshes hand and dsicards some cards
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);

        // Quest 2 begins:
        // press enter if needed?
        enterButton.click();
        Thread.sleep(1000);
        noButton.click();
        Thread.sleep(1000);
        yesButton.click();
        Thread.sleep(1000);

        // p3 builds stage: 0 quit 0 2 quit 0 3 quit
        cardPos0.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos2.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // q2 stage 1
        withdrawButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        // q2 stage 2
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        // q2 stage 3
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos9.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos9.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        // p3 sponsor trims f20 f25 f30
        cardPos0.click();
        Thread.sleep(1000);
        cardPos1.click();
        Thread.sleep(1000);
        cardPos1.click();
        Thread.sleep(1000);
        // end hand p3: //  f20 f40 d5 d5 s10 h10 h10 h10 h10 b15 b15 l20

        List<String> p1ExpectedFinalHand = new ArrayList<>();
        p1ExpectedFinalHand.add("F15");
        p1ExpectedFinalHand.add("F15");
        p1ExpectedFinalHand.add("F20");
        p1ExpectedFinalHand.add("F20");
        p1ExpectedFinalHand.add("F20");
        p1ExpectedFinalHand.add("F20");
        p1ExpectedFinalHand.add("F25");
        p1ExpectedFinalHand.add("F25");
        p1ExpectedFinalHand.add("F30");
        p1ExpectedFinalHand.add("H10");
        p1ExpectedFinalHand.add("B15");
        p1ExpectedFinalHand.add("L20");

        List<String> p2ExpectedFinalHand = new ArrayList<>();
        p2ExpectedFinalHand.add("F10");
        p2ExpectedFinalHand.add("F15");
        p2ExpectedFinalHand.add("F15");
        p2ExpectedFinalHand.add("F25");
        p2ExpectedFinalHand.add("F30");
        p2ExpectedFinalHand.add("F40");
        p2ExpectedFinalHand.add("F50");
        p2ExpectedFinalHand.add("L20");
        p2ExpectedFinalHand.add("L20");

        List<String> p3ExpectedFinalHand = new ArrayList<>();
        p3ExpectedFinalHand.add("F20");
        p3ExpectedFinalHand.add("F40");
        p3ExpectedFinalHand.add("D5");
        p3ExpectedFinalHand.add("D5");
        p3ExpectedFinalHand.add("S10");
        p3ExpectedFinalHand.add("H10");
        p3ExpectedFinalHand.add("H10");
        p3ExpectedFinalHand.add("H10");
        p3ExpectedFinalHand.add("H10");
        p3ExpectedFinalHand.add("B15");
        p3ExpectedFinalHand.add("B15");
        p3ExpectedFinalHand.add("L20");

        List<String> p4ExpectedFinalHand = new ArrayList<>();
        p4ExpectedFinalHand.add("F15");
        p4ExpectedFinalHand.add("F15");
        p4ExpectedFinalHand.add("F20");
        p4ExpectedFinalHand.add("F25");
        p4ExpectedFinalHand.add("F30");
        p4ExpectedFinalHand.add("F50");
        p4ExpectedFinalHand.add("F70");
        p4ExpectedFinalHand.add("L20");
        p4ExpectedFinalHand.add("L20");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:8080/game/player-hand")).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<String>> playerHands = objectMapper.readValue(response.body(), List.class);

        List<String> p1Hand = playerHands.get(0);
        List<String> p2Hand = playerHands.get(1);
        List<String> p3Hand = playerHands.get(2);
        List<String> p4Hand = playerHands.get(3);

        String p1ShieldCount = p1ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p2ShieldCount = p2ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p3ShieldCount = p3ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p4ShieldCount = p4ShieldElement.getText().split(":")[1].split("\\|")[0].trim();

        assertEquals(0, Integer.parseInt(p1ShieldCount));
        assertEquals(7, Integer.parseInt(p2ShieldCount));
        assertEquals(0, Integer.parseInt(p3ShieldCount));
        assertEquals(7, Integer.parseInt(p4ShieldCount));

        assertEquals(p1ExpectedFinalHand, p1Hand);
        assertEquals(12, p1Hand.size());

        assertEquals(p2ExpectedFinalHand, p2Hand);
        assertEquals(9, p2Hand.size());

        assertEquals(p3ExpectedFinalHand, p3Hand);
        assertEquals(12, p3Hand.size());

        assertEquals(p4ExpectedFinalHand, p4Hand);
        assertEquals(9, p4Hand.size());

        Thread.sleep(20000); // wait 20s to review game
        webDriver.quit();
    }

    @Test
    @DisplayName("1winner_game_with_events")
    public void scenario3Test() throws InterruptedException, IOException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://127.0.0.1:8081");

        // Initialize all buttons using their ID's
        WebElement resetButton = webDriver.findElement(By.id("reset-button"));
        WebElement thirdScenarioButton = webDriver.findElement(By.id("rig-3"));

        WebElement yesButton = webDriver.findElement(By.id("y-button"));
        WebElement noButton = webDriver.findElement(By.id("n-button"));
        WebElement quitButton = webDriver.findElement(By.id("quit-button"));
        WebElement tackleButton = webDriver.findElement(By.id("tackle-button"));
        WebElement withdrawButton = webDriver.findElement(By.id("withdraw-button"));
        WebElement enterButton = webDriver.findElement(By.id("enter-button"));

        WebElement cardPos0 = webDriver.findElement(By.id("pos-0"));
        WebElement cardPos1 = webDriver.findElement(By.id("pos-1"));
        WebElement cardPos2 = webDriver.findElement(By.id("pos-2"));
        WebElement cardPos3 = webDriver.findElement(By.id("pos-3"));
        WebElement cardPos4 = webDriver.findElement(By.id("pos-4"));
        WebElement cardPos5 = webDriver.findElement(By.id("pos-5"));
        WebElement cardPos6 = webDriver.findElement(By.id("pos-6"));
        WebElement cardPos7 = webDriver.findElement(By.id("pos-7"));
        WebElement cardPos8 = webDriver.findElement(By.id("pos-8"));
        WebElement cardPos9 = webDriver.findElement(By.id("pos-9"));
        WebElement cardPos10 = webDriver.findElement(By.id("pos-10"));
        WebElement cardPos11 = webDriver.findElement(By.id("pos-11"));

        WebElement p1ShieldElement = webDriver.findElement(By.id("p1-shieldCount"));
        WebElement p2ShieldElement = webDriver.findElement(By.id("p2-shieldCount"));
        WebElement p3ShieldElement = webDriver.findElement(By.id("p3-shieldCount"));
        WebElement p4ShieldElement = webDriver.findElement(By.id("p4-shieldCount"));

        // Start clicking simulation
        resetButton.click();
        Thread.sleep(1000);
        thirdScenarioButton.click();
        Thread.sleep(1000);
        // assert game begins/initialized, etc.

        // build quest:  y 0 quit 1 quit 2 quit 3 quit
        yesButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos1.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos2.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // stage 1
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);

        // stage 1 attack: 4 quit 4 quit 5 quit
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos4.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);

        // stage 2 attack: 3 quit 3 quit 4 quit | CORRECTED: 5 5 6
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);

        // stage 3 attack: 7 quit 7 quit 8 quit
        cardPos7.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos7.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos8.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);

        // stage 4 attack: 9 quit 9 quit 10 quit
        cardPos9.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos9.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos10.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // p1 refresh and discards: // p1 discards: 0 0 1 1
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos1.click();
        Thread.sleep(1000);
        cardPos1.click();
        Thread.sleep(1000);

        enterButton.click(); // check ??
        Thread.sleep(1000);

        // Event cards
        // enter after plague?

        enterButton.click();
        Thread.sleep(1000);

        // prosperity: all draw 2 cards: // // // // prosp discard: 0 0 0 0 0
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);

        // need enter turne end?
        enterButton.click();
        Thread.sleep(1000);

        // p4 draws queens favor: + 2 cards:  1 3
        cardPos1.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        enterButton.click();
        Thread.sleep(1000);

        // Quest 2:
        yesButton.click();
        Thread.sleep(1000);
        // quest 2 setup p1: 1 quit 1 7 quit 4 6 quit
        // ?? // 0 quit 0 6 quit 3 5 quit
        cardPos0.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos3.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // stage 1: // // // stage 1 decision: tackle 0 tackle 0 tackle 0
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);

        // stage 1 attack: // // // stage 1 attack: 8 quit 8 quit 9 quit ||
        cardPos8.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos8.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos9.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // stage 2: // // stage 2: 9 7 quit 9 6 quit |!! updated
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);

        cardPos9.click();
        Thread.sleep(1000);
        cardPos7.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        cardPos9.click();
        Thread.sleep(1000);
        cardPos6.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // stage 3: // // stage 3: 9 5 quit 10 quit
        tackleButton.click();
        Thread.sleep(1000);
        tackleButton.click();
        Thread.sleep(1000);
        cardPos9.click();
        Thread.sleep(1000);
        cardPos5.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);
        cardPos10.click();
        Thread.sleep(1000);
        quitButton.click();
        Thread.sleep(1000);

        // p1 refreshes hand and discards some cards:
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);
        cardPos0.click();
        Thread.sleep(1000);

        List<String> p1ExpectedFinalHand = new ArrayList<>();
        // p1: d5 d5 h10 h10 h10 s10 s10 s10 s10 f25 f25 f35
        p1ExpectedFinalHand.add("F25");
        p1ExpectedFinalHand.add("F25");
        p1ExpectedFinalHand.add("F35");
        p1ExpectedFinalHand.add("D5");
        p1ExpectedFinalHand.add("D5");
        p1ExpectedFinalHand.add("S10");
        p1ExpectedFinalHand.add("S10");
        p1ExpectedFinalHand.add("S10");
        p1ExpectedFinalHand.add("S10");
        p1ExpectedFinalHand.add("H10");
        p1ExpectedFinalHand.add("H10");
        p1ExpectedFinalHand.add("H10");

        List<String> p2ExpectedFinalHand = new ArrayList<>();
        p2ExpectedFinalHand.add("F15");
        p2ExpectedFinalHand.add("F25");
        p2ExpectedFinalHand.add("F30");
        p2ExpectedFinalHand.add("F40");
        p2ExpectedFinalHand.add("S10");
        p2ExpectedFinalHand.add("S10");
        p2ExpectedFinalHand.add("S10");
        p2ExpectedFinalHand.add("H10");
        p2ExpectedFinalHand.add("E30");

        List<String> p3ExpectedFinalHand = new ArrayList<>();
        p3ExpectedFinalHand.add("F10");
        p3ExpectedFinalHand.add("F25");
        p3ExpectedFinalHand.add("F30");
        p3ExpectedFinalHand.add("F40");
        p3ExpectedFinalHand.add("F50");
        p3ExpectedFinalHand.add("S10");
        p3ExpectedFinalHand.add("S10");
        p3ExpectedFinalHand.add("H10");
        p3ExpectedFinalHand.add("H10");
        p3ExpectedFinalHand.add("L20");

        List<String> p4ExpectedFinalHand = new ArrayList<>();
        p4ExpectedFinalHand.add("F25");
        p4ExpectedFinalHand.add("F25");
        p4ExpectedFinalHand.add("F30");
        p4ExpectedFinalHand.add("F50");
        p4ExpectedFinalHand.add("F70");
        p4ExpectedFinalHand.add("D5");
        p4ExpectedFinalHand.add("D5");
        p4ExpectedFinalHand.add("S10");
        p4ExpectedFinalHand.add("S10");
        p4ExpectedFinalHand.add("B15");
        p4ExpectedFinalHand.add("L20");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:8080/game/player-hand")).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<String>> playerHands = objectMapper.readValue(response.body(), List.class);

        List<String> p1Hand = playerHands.get(0);
        List<String> p2Hand = playerHands.get(1);
        List<String> p3Hand = playerHands.get(2);
        List<String> p4Hand = playerHands.get(3);

        String p1ShieldCount = p1ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p2ShieldCount = p2ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p3ShieldCount = p3ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p4ShieldCount = p4ShieldElement.getText().split(":")[1].split("\\|")[0].trim();

        assertEquals(0, Integer.parseInt(p1ShieldCount));
        assertEquals(5, Integer.parseInt(p2ShieldCount));
        assertEquals(7, Integer.parseInt(p3ShieldCount));
        assertEquals(4, Integer.parseInt(p4ShieldCount));

        assertEquals(p1ExpectedFinalHand, p1Hand);
        assertEquals(12, p1Hand.size());

        assertEquals(p2ExpectedFinalHand, p2Hand);
        assertEquals(9, p2Hand.size());

        assertEquals(p3ExpectedFinalHand, p3Hand);
        assertEquals(10, p3Hand.size());

        assertEquals(p4ExpectedFinalHand, p4Hand);
        assertEquals(11, p4Hand.size());

        Thread.sleep(20000); // wait 20s to review game
        webDriver.quit();
    }
}