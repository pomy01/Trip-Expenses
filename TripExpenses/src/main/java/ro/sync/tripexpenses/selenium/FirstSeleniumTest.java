package ro.sync.tripexpenses.selenium
;

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Test Class to test the login method.
 * @author Mihai.
 *
 */
public class FirstSeleniumTest extends TestCase{
  private WebDriver driver;
  private String baseUrl;
//  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/TripExpenses/";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  /**
   * Tests if after the login a friend "Ady" is present.
   * @throws Exception
   */
  @Test
  public void testFirstSelenium() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.id("user")).clear();
    driver.findElement(By.id("user")).sendKeys("dana");
    driver.findElement(By.id("pass")).clear();
    driver.findElement(By.id("pass")).sendKeys("paroladana");
    driver.findElement(By.id("login")).click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.id("home")).click();
    driver.findElement(By.id("friends")).click();
    driver.findElement(By.id("trips")).click();
    driver.findElement(By.linkText("prima excursie")).click();
    assertEquals("ady", driver.findElement(By.linkText("ady")).getText());
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

//  private boolean isElementPresent(By by) {
//    try {
//      driver.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
//
//  private boolean isAlertPresent() {
//    try {
//      driver.switchTo().alert();
//      return true;
//    } catch (NoAlertPresentException e) {
//      return false;
//    }
//  }
//
//  private String closeAlertAndGetItsText() {
//    try {
//      Alert alert = driver.switchTo().alert();
//      String alertText = alert.getText();
//      if (acceptNextAlert) {
//        alert.accept();
//      } else {
//        alert.dismiss();
//      }
//      return alertText;
//    } finally {
//      acceptNextAlert = true;
//    }
//  }
}
