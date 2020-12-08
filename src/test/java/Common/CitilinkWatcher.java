package Common;

import io.qameta.allure.Attachment;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CitilinkWatcher extends TestWatcher {

    public static WebDriver driver;

    public CitilinkWatcher(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        saveScreenShot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenShot() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}