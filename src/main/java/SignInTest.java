import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    
    @BeforeSuite
    public void runBeforeSuit()
    {
        LibraryFunctions.setDriverPath();
    }
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        LibraryFunctions.LaunchApp("https://www.cleartrip.com/");
        
        LibraryFunctions.waitFor(2000);

        LibraryFunctions.driver.findElement(By.linkText("Your trips")).click();
        LibraryFunctions.driver.findElement(By.id("SignIn")).click();

        LibraryFunctions.driver.findElement(By.id("signInButton")).click();

        String errors1 = LibraryFunctions.driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        LibraryFunctions.driver.quit();
    }

    


}
