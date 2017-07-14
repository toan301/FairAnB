package Common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import common.AbstractTest;
import common.CommonAction;
import common.Constant;
import page.DashboardPage;
import page.MyAccountPage;
import page.RegisterPage;

public class S001_Login extends AbstractTest {

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser" })
	public void setUp(String browser) {
		driver = openBrowser(browser, Constant.URL);
		dashboard = new DashboardPage(driver);
		register = new RegisterPage(driver);
		account = new MyAccountPage(driver);
		
		validUsername = "automation01";
		validPassword = "12345678";
		invalidUsername = "invalid";
		invalidPassword = "invalid";
		registerHeader = "Register";
		myAccountHeader = "My Account";
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		state = "California";
		gender = "male";
		country = "United States";
		email = firstName + CommonAction.getCommon().getRandomNumber() + "@gmail.com";
		username = firstName + lastName + CommonAction.getCommon().getRandomNumber();
		password = "12345678";
		
	}
	
	
	@Test
	public void S001_Login_TC001_Register_With_Valid_Information() {
		log.info("Step 01: Click Login Drop");
		dashboard.clickLoginDrop();
		
		log.info("Step 02: Click 'Not yet registered?' button");
		dashboard.clickRegisterLink();
		
		log.info("VP 01. Is 'Register' header displayed");
		register.isDynamicHeaderDisplayed(registerHeader);
		
		log.info("Step 04: Input First Name");
		register.inputFirstName(firstName);
		
		log.info("Step 05: Input Last Name");
		register.inputLastName(lastName);
		
		log.info("Step 06: Select Genre");
		register.selectGenre(gender);
		
		log.info("Step 07: Select Country");
		register.selectCountry(country);
		
		log.info("Step 08: Input State");
		register.selectState(state);
		
		log.info("Step 09: Input Email Address");
		register.inputEmailAddress(email);
		
		log.info("Step 10: Input username");
		register.inputUsername(username);
		
		log.info("Step 11: Input Password");
		register.inputPassword(password);
		
		log.info("Step 12: Input repeat password");
		register.inputRepeatPassword(password);
		
		log.info("Step 13: Click Submit Register button");
		register.clickSubmitRegisterButton();
		
		log.info("VP 02. Is My Account Header Displayed");
		account.isDynamicHeaderDisplayed(myAccountHeader);
		
		log.info("Step 14: Click My Account Information link");
		account.clickMyAccountInformationLink();
		
		log.info("VP 03. Verify Email, FirstName, LastName, Gender are the same as registered");
		verifyEquals(account.getDynamicMyAccountInformation("email"), email);
		verifyEquals(account.getDynamicMyAccountInformation("First Name"), firstName);
		verifyEquals(account.getDynamicMyAccountInformation("Last Name"), lastName);
		verifyEquals(account.getDynamicMyAccountInformation("Gender"), gender.charAt(0));
		
		log.info("Step 15: Click Billing & Shipping Information link");
		account.clickBillingShippingInformationLink();
		
//		log.info("VP 04. Verify Country, State, ");
	}

	@Test
	public void S001_Login_TC002_Verify_Login_Function() {
		log.info("Step 01: Click Login Drop");
		dashboard.clickLoginDrop();
		
		log.info("Step 04: Click Login button");
		dashboard.clickLoginButton();
		
		log.info("VP 01. Is My Account button Displayed");
		verifyTrue(dashboard.isLoginFailedMessageDisplayed());
		
		log.info("Step 01: Click Login Drop");
		dashboard.clickLoginDrop();
		
		log.info("Step 02: Input invalid username");
		dashboard.inputUserName(invalidUsername);
		
		log.info("Step 03: Input invalid password");
		dashboard.inputPassword(invalidPassword);
		
		log.info("Step 04: Click Login button");
		dashboard.clickLoginButton();
		
		log.info("VP 01. Is My Account button Displayed");
		verifyTrue(dashboard.isLoginFailedMessageDisplayed());
		
		log.info("Step 01: Click Login Drop");
		dashboard.clickLoginDrop();
		
		log.info("Step 02: Input valid username");
		dashboard.inputUserName(validUsername);
		
		log.info("Step 03: Input valid password");
		dashboard.inputPassword(validPassword);
		
		log.info("Step 04: Click Login button");
		dashboard.clickLoginButton();
		
		log.info("VP 01. Is My Account button Displayed");
		verifyTrue(dashboard.isMyAccountButtonDisplayed());
	}

	

	@AfterClass(alwaysRun = true)
	public void tearsDown() {
		closeBrowser();
	}
	Faker faker = new Faker();
	DashboardPage dashboard;
	RegisterPage register;
	MyAccountPage account;
	
	String validUsername, validPassword, invalidUsername, invalidPassword;
	String registerHeader, myAccountHeader;
	String firstName, lastName, state, gender, country, email, username, password;
}
