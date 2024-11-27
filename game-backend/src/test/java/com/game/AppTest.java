package com.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest{

    @Test
    @DisplayName("A-TEST JP-Scenario")
    void aTest() throws InterruptedException {
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

        String p1ShieldCount = p1ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p2ShieldCount = p2ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p3ShieldCount = p3ShieldElement.getText().split(":")[1].split("\\|")[0].trim();
        String p4ShieldCount = p4ShieldElement.getText().split(":")[1].split("\\|")[0].trim();

        assertEquals(0, Integer.parseInt(p1ShieldCount));
        assertEquals(0, Integer.parseInt(p2ShieldCount));
        assertEquals(0, Integer.parseInt(p3ShieldCount));
        assertEquals(4, Integer.parseInt(p4ShieldCount));

        Thread.sleep(10000); // wait 20s to review game
        webDriver.quit();
    }

    @Test
    @DisplayName("2winner_game_2winner_quest")
    void scenario2Test() throws InterruptedException {
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



    }
}