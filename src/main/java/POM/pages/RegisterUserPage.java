package POM.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import utils.ElementActions;

public class RegisterUserPage {

    private By signupFormTitle1 = By.xpath("//div[@class='signup-form']//h2");
    private By signupFormTitle2 = By.xpath("//div[@class='login-form']/h2[@class='title text-center']/b");
    private By userNameField = By.xpath("//input[@data-qa='signup-name']");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By mrTitleRadio = By.id("id_gender1");
    private By mrsTitleRadio = By.id("id_gender2");
    private By passwordInput = By.id("password");
    private By daysSelect = By.id("days");
    private By monthsSelect = By.id("months");
    private By yearsSelect = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By specialOffersCheckbox = By.id("optin");
    private By firstNameInput = By.id("first_name");
    private By lastNameInput = By.id("last_name");
    private By companyInput = By.id("company");
    private By address1Input = By.id("address1");
    private By address2Input = By.id("address2");
    private By countrySelect = By.id("country");
    private By stateInput = By.id("state");
    private By cityInput = By.id("city");
    private By zipcodeInput = By.id("zipcode");
    private By mobileNumberInput = By.id("mobile_number");
    private By createAccountBtn = By.xpath("//button[@data-qa='create-account']");


    public void sighupUser(String userName, String email) {
        ElementActions.typeText(userNameField, userName);
        ElementActions.typeText(emailField, email);
        ElementActions.clickElement(signupButton);
    }

    public void enterAccountInformation(String title, String password, String day,
                                        String month, String year, Boolean Newsletter, Boolean SpecialOffers) {
        selectUserTitle(title);
        ElementActions.typeText(passwordInput, password);
        selectDateOfBirth(day, month, year);
        checkNewsletter(Newsletter);
        checkSpecialOffers(SpecialOffers);
    }

    public void enterAddressInformation(String firstName, String lastName, String company, String address1, String address2,
                                        String country, String state, String city, String zipcode, String mobileNumber) {
        ElementActions.typeText(firstNameInput, firstName);
        ElementActions.typeText(lastNameInput, lastName);
        ElementActions.typeText(companyInput, company);
        ElementActions.typeText(address1Input, address1);
        ElementActions.typeText(address2Input, address2);
        ElementActions.typeText(countrySelect, country);
        ElementActions.typeText(stateInput, state);
        ElementActions.typeText(cityInput, city);
        ElementActions.typeText(zipcodeInput, zipcode);
        ElementActions.typeText(mobileNumberInput, mobileNumber);
        ElementActions.clickElement(createAccountBtn);
    }

    public void assertOnSignupFormTitle1(String signupTitle1) {
        Assert.assertEquals(ElementActions.getText(signupFormTitle1), signupTitle1);
    }

    public void assertOnSignupFormTitle2(String signupTitle2) {
        Assert.assertEquals(ElementActions.getText(signupFormTitle2), signupTitle2);
    }

    private void selectUserTitle(String title) {
        if (title.toLowerCase().trim().equals("mr")) {
            ElementActions.selectRadioButton(mrTitleRadio);
        } else if (title.toLowerCase().trim().equals("mrs")) {
            ElementActions.selectRadioButton(mrsTitleRadio);
        } else {
            System.out.println("Invalid Title");
        }
    }

    private void checkNewsletter(Boolean newsletter) {
        if (newsletter) {
            ElementActions.checkCheckbox(newsletterCheckbox);
        } else {
            ElementActions.uncheckCheckbox(newsletterCheckbox);
        }
    }

    private void checkSpecialOffers(Boolean specialOffers) {
        if (specialOffers) {
            ElementActions.checkCheckbox(specialOffersCheckbox);
        } else {
            ElementActions.uncheckCheckbox(specialOffersCheckbox);
        }
    }

    private void selectDateOfBirth(String day, String month, String year) {
        ElementActions.selectDropdownByVisibleText(daysSelect, day);
        ElementActions.selectDropdownByVisibleText(monthsSelect, month);
        ElementActions.selectDropdownByVisibleText(yearsSelect, year);
    }

}
