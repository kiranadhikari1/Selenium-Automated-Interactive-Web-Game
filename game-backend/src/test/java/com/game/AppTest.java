package com.game;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest{

    @Test
    void playGameTest(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://127.0.0.1:8083");

        WebElement startButton = webDriver.findElement(By.id("start-game"));
        startButton.click();

        WebElement yesButton = webDriver.findElement(By.id("y-button"));
        yesButton.click();
        webDriver.quit();

    }

    @Test
    void noSponsorTest(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://127.0.0.1:8083");

        WebElement startButton = webDriver.findElement(By.id("start-game"));
        startButton.click();

        WebElement noButton = webDriver.findElement(By.id("n-button"));
        noButton.click();
        webDriver.quit();
    }
}