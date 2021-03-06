package pages;

import framework.PageFactory;
import framework.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReportsPage extends PageFactory {
    private final By startDate = By.xpath("//*[starts-with(@class,'form-field start-date')]");
    private final By endDate = By.xpath("//*[starts-with(@class,'form-field stop-date')]");
    private final By format = By.xpath("//*[starts-with(@class,'ng-pristine ng-untouched ng-valid')]");
    private final By runReport = By.xpath("//*[contains(@class,'run-report confirm')]");

    public void fillReportForm(String startdate, String enddate, String form) {
        Wait.explicitWait(ExpectedConditions.presenceOfElementLocated(startDate));
        WebElement select = driver.findElements(startDate).get(3);

        select.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].type = arguments[1]", select, "text");
        js.executeScript("arguments[0].value = arguments[1]", select, startdate);

        Wait.explicitWait(ExpectedConditions.presenceOfElementLocated(endDate));
        WebElement selectnext = driver.findElements(endDate).get(1);

        selectnext.click();
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].type = arguments[1]", selectnext, "text");
        js2.executeScript("arguments[0].value = arguments[1]", selectnext, enddate);

        Wait.explicitWait(ExpectedConditions.presenceOfElementLocated(format));
        driver.findElements(format).get(2).sendKeys(form);

        Wait.explicitWait(ExpectedConditions.presenceOfElementLocated(runReport));
        driver.findElements(runReport).get(2).click();
        select.click();
        select.clear();
        select.sendKeys(startdate);

        selectnext.click();
        selectnext.clear();
        selectnext.sendKeys(enddate);

        driver.findElements(runReport).get(2).click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public String getTestObservationText() {
        return driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table[1]/tbody/tr[3]/td[2]/span"))
                .getText();
    }
}
