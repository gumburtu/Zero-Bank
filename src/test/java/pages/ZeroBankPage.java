package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ZeroBankPage {
    public ZeroBankPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@id='signin_button']")
    public WebElement zeroBankLoginButton;

    @FindBy(xpath = "//input[@id='user_login']")
    public WebElement zeroBankUsername;

    @FindBy(xpath = "//input[@id='user_password']")
    public WebElement zeroBankPassword;

    @FindBy(xpath = "//input[@name='submit']")
    public WebElement zeroBankSubmitButton;

    @FindBy(xpath = "//li[@id='onlineBankingMenu']")
    public WebElement zeroBankOnlineBanking;

    @FindBy(xpath = "//span[@id='pay_bills_link']")
    public WebElement zeroBankPayBills;

    @FindBy(xpath = "//a[@href='#ui-tabs-3']")
    public WebElement zeroBankPurchaseForeignCurrency;

    @FindBy(id = "pc_currency")
    public WebElement zeroBankCurrency;



}
