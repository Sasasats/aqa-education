package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.openqa.selenium.Cookie;
import utils.enums.SettingsFilesValues;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class BrowserUtils {
    private static ISettingsFile authData = new JsonSettingsFile("authorizationData.json");
    private static ISettingsFile configData = new JsonSettingsFile("config.json");

    public static void authorisation(){
        getBrowser().goTo(String.format(
                configData.getValue(SettingsFilesValues.BASE_URI_WEB.getValue()).toString(),
                authData.getValue(SettingsFilesValues.LOGIN.getValue()),
                authData.getValue(SettingsFilesValues.PASSWORD.getValue())));
    }

    public static void sendToken(String token){
        getBrowser().getDriver().manage().addCookie(new Cookie("token", token));
    }
}