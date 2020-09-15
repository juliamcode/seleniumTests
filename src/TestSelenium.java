import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSelenium {

    static {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver-v0.27.0-win64\\geckodriver.exe");
    }

    public static WebDriver driver = new FirefoxDriver();
    public static String checkSolution = "solution";


    @Test
    public void excercise1() {

        String excercise1address = "https://antycaptcha.amberteam.pl/exercises/exercise1?seed=6b42fcfa-5b3f-4ebe-8de2-7e260a0f0ef8";
        String btnButton1 = "btnButton1";
        String btnButton2 = "btnButton2";

        driver.get(excercise1address);

        driver.findElement(By.id(btnButton1)).click();
        driver.findElement(By.id(btnButton2)).click();
        driver.findElement(By.id(btnButton1)).click();

        checkSolution();
    }

    @Test
    public void excercise2() {

        String excercise2address = "https://antycaptcha.amberteam.pl/exercises/exercise2?seed=977417bc-98f9-46e5-a0b8-6ae94be225f7";
        String btnButton1 = "btnButton1";
        driver.get(excercise2address);
        String inputID = "t14";
        driver.findElement(By.id(inputID)).clear();
        driver.findElement(By.id(inputID)).sendKeys("Air environment author.");
        driver.findElement(By.id(btnButton1)).click();

        checkSolution();
    }

    @Test
    public void excercise3() {

        String excercise3address = "https://antycaptcha.amberteam.pl/exercises/exercise3?seed=ea74c46a-6546-4ba5-a996-2384bff439a9";
        driver.get(excercise3address);
        String dropdownListId = "s13";
        Select drp = new Select(driver.findElement(By.id(dropdownListId)));
        drp.selectByVisibleText("Freudian Gilt");

        checkSolution();
    }

    @Test
    public void excercise4() {

        String excercise4address = "https://antycaptcha.amberteam.pl/exercises/exercise4?seed=ea74c46a-6546-4ba5-a996-2384bff439a9";
        driver.get(excercise4address);

        clickElementInRadioButtonsList("s0", "v40");
        clickElementInRadioButtonsList("s1", "v01");
        clickElementInRadioButtonsList("s2", "v22");
        clickElementInRadioButtonsList("s3", "v03");

        checkSolution();
    }

    @Test
    public void excercise5() {
        // 404 page not found
    }

    public static void checkSolution() {
        driver.findElement(By.id(checkSolution)).click();
        assertEquals(driver.findElement(By.id("trail")).getText(), "OK. Good answer");
    }

    public static void clickElementInRadioButtonsList(String elementsName, String elementsValue) {
        List radioButtonsList = driver.findElements(By.name(elementsName));

        for(int i=0; i< radioButtonsList.size(); i++){
            WebElement webElement = (WebElement) radioButtonsList.get(i);
            System.out.println();
            String val = webElement.getAttribute("value");

            if(val.equals(elementsValue)) {
                webElement.click();
            }
        }
    }

}