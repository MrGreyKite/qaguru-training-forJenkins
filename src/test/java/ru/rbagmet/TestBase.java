package ru.rbagmet;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import ru.rbagmet.configs.ConfigCredentials;

import static java.lang.String.format;

public class TestBase {

    public static ConfigCredentials credentials = ConfigFactory.create(ConfigCredentials.class);

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
/*        capabilities.setCapability("browserName", System.getProperty("browserName"));
        capabilities.setCapability("browserVersion", System.getProperty("browserVersion"));
*/

        Configuration.browserCapabilities = capabilities;
		Configuration.browser = System.getProperty("browserName");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.browserSize = "1366x768";
        Configuration.baseUrl = "https://demoqa.com";

        String login = credentials.login();
        String password = credentials.password();
//        String remoteURL = credentials.remoteUrl();
        String URL = System.getProperty("URL");

        Configuration.remote = format("https://%s:%s@%s", login, password, URL);
    }
	
	@AfterEach
	public void tearDown() {
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
    }

}
