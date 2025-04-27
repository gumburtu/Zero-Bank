package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ZeroBankPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomeWork01 {

    @Test
    public void test01() {
        // 1. "http://zero.webappsecurity.com/" adresine git
        Driver.getDriver().get(ConfigReader.getProperty("zeroBankUrl"));

        // 2. Sign in butonuna bas
        ZeroBankPage zeroBankPage = new ZeroBankPage();
        zeroBankPage.zeroBankLoginButton.click();

        // 3. Login kutusuna "username" yaz
        zeroBankPage.zeroBankUsername.sendKeys(ConfigReader.getProperty("zeroBankUser"));

        // 4. Password kutusuna "password" yaz
        zeroBankPage.zeroBankPassword.sendKeys(ConfigReader.getProperty("zeroBankPassword"));

        // 5. Sign in tuşuna bas
        zeroBankPage.zeroBankSubmitButton.click();

        // Sertifika sayfasını atla ve doğrudan index.html sayfasına git
        Driver.getDriver().get("http://zero.webappsecurity.com/index.html");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 6. Online Banking menüsüne tıkla ve Pay Bills sayfasına git
        zeroBankPage.zeroBankOnlineBanking.click();


        zeroBankPage.zeroBankPayBills.click();

        // Sayfa başlığında "Zero" geçtiğini doğrula
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Zero"), "Title 'Zero' içermiyor!");

        // 7. "Purchase Foreign Currency" sekmesine geç
        zeroBankPage.zeroBankPurchaseForeignCurrency.click();

        // 8. "Currency" dropdown'undan Eurozone (euro) seç
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Select select = new Select(zeroBankPage.zeroBankCurrency);
        select.selectByVisibleText("Eurozone (euro)");

        // 9. SoftAssert ile seçilen opsiyonun doğru olduğunu doğrula
        SoftAssert softAssert = new SoftAssert();
        String selectedOption = select.getFirstSelectedOption().getText();
        softAssert.assertEquals(selectedOption, "Eurozone (euro)", "Seçilen para birimi yanlış!");
        softAssert.assertAll();


        // 10. SoftAssert ile dropdown içeriğinin doğru olduğunu doğrula
        List<String> expectedOptions = List.of(
                "Select One", "Australia (dollar)", "Canada (dollar)", "Switzerland (franc)",
                "China (yuan)", "Denmark (krone)", "Eurozone (euro)", "Great Britain (pound)",
                "Hong Kong (dollar)", "Japan (yen)", "Mexico (peso)", "Norway (krone)",
                "New Zealand (dollar)", "Sweden (krona)", "Singapore (dollar)", "Thailand (baht)"
        );

        List<String> actualOptions = new ArrayList<>();
        for (WebElement option : select.getOptions()) {
            actualOptions.add(option.getText());
        }

        softAssert.assertEquals(actualOptions, expectedOptions);

        // SoftAssert tüm doğrulamaları yap
        softAssert.assertAll();

        // Test sonunda driver kapat
        Driver.closeDriver();
    }
}