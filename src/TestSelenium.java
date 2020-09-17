import org.junit.Test;

import org.openqa.selenium.*;
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
    public static String mainSite = "https://antycaptcha.amberteam.pl";
    public static String btnButton1 = "btnButton1";
    public static String btnButton2 = "btnButton2";


    @Test
    public void excercise1() {

        goToExcercise("Three buttons");

        List<WebElement> codeSnippets = driver.findElements(By.tagName("code"));

        for(int i=0; i< 3; i++) {

            String txt = codeSnippets.get(i).getText();

            if("B1".equals(txt)) {
                driver.findElement(By.id(btnButton1)).click();
            } else if("B2".equals(txt)) {
                driver.findElement(By.id(btnButton2)).click();
            }
        }

        checkSolution();
    }


    @Test
    public void excercise2() {

        String inputID = "t14";

        goToExcercise("Editbox");

        List<WebElement> codeSnippets = driver.findElements(By.tagName("code"));

        String txt = codeSnippets.get(0).getText();

        driver.findElement(By.id(inputID)).clear();
        driver.findElement(By.id(inputID)).sendKeys(txt);
        driver.findElement(By.id(btnButton1)).click();

        checkSolution();
    }


    @Test
    public void excercise3() {

        goToExcercise("Dropdown list");

        String dropdownListId = "s13";
        Select drp = new Select(driver.findElement(By.id(dropdownListId)));

        String txt = driver.findElements(By.tagName("code")).get(0).getText();

        drp.selectByVisibleText(txt);

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
        WebElement element = driver.findElement(By.id(checkSolution));
        if(element.isEnabled()) {
            element.click();
            assertEquals(driver.findElement(By.id("trail")).getText(), "OK. Good answer");
        } else {
            System.out.println("ERROR: 'Check Solution button is DISABLED");
        }

    }

    public static void goToExcercise(String txt) {
        driver.get(mainSite);

        WebElement element = driver.findElement(By.xpath("//*[text()[contains(.,'" +
                txt +
                "')]]"));
        element.click();
    }

    public static void clickElementInRadioButtonsList(String elementsName, String elementsValue) {
        List radioButtonsList = driver.findElements(By.name(elementsName));

        for(int i=0; i< radioButtonsList.size(); i++){
            WebElement webElement = (WebElement) radioButtonsList.get(i);
            String txt = webElement.getText();
            System.out.println(txt);
            String val = webElement.getAttribute("value");

            if(val.equals(elementsValue)) {
                webElement.click();
            }
        }
    }

}