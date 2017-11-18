import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest {

    string fromTagId = "FromTag";
    string toTagId = "toTag";
    
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        
        LibraryFunctions.LaunchApp("https://www.cleartrip.com/");
        LibraryFunctions.waitFor(2000);
        LibraryFunctions.driver.findElement(By.id("OneWay")).click();

        LibraryFunctions.driver.findElement(By.id(fromTagId)).clear();
        LibraryFunctions.driver.findElement(By.id(fromTagId)).sendKeys("Bangalore");

        //wait for the auto complete options to appear for the origin

        LibraryFunctions.waitFor(2000);
        List<WebElement> originOptions = LibraryFunctions.driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        LibraryFunctions.driver.findElement(By.id(toTagId)).clear();
        LibraryFunctions.driver.findElement(By.id(toTagId)).sendKeys("Delhi");

        //wait for the auto complete options to appear for the destination

        LibraryFunctions.waitFor(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = LibraryFunctions.driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        LibraryFunctions.driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        LibraryFunctions.driver.findElement(By.id("SearchBtn")).click();

        LibraryFunctions.waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
        LibraryFunctions.driver.quit();

    }


    private boolean isElementPresent(By by) {
        try {
            LibraryFunctions.driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
