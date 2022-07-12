package forms;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import models.Entry;
import models.savePhoto.SavedPhoto;
import org.openqa.selenium.By;
import utils.UserDataValue;

public class MyPage extends Form {

    private final ISettingsFile userData = new JsonSettingsFile("userAuthorizationData.json");

    private static final String FORM_LOC = "profile";

    private static final String ENTRY_XPATH = "//div[@id='post%s_%s']%s";
    private static final String ENTRY_TEXT_XPATH = "//div[contains(@class,'wall_post_text')]";
    private static final String ENTRY_PHOTO_XPATH = "//a[@href='/photo%s_%s']";

    private static final String LINK_SHOW_COMMENTS_XPATH = "//span[@class='js-replies_next_label']";
    private static final String COMMENT_TEXT_XPATH = "//div[@class='wall_reply_text']";
    private static final String LIKE_XPATH = "//div[@class='like_button_icon']";

    public MyPage() {
        super(By.id(FORM_LOC), "Profile form");
    }

    private IButton getEntry(Entry entry) {
        return getElementFactory().getButton(By.xpath(String.format(
                ENTRY_XPATH, userData.getValue(UserDataValue.ID.getValue()), entry.getPost_id(),
                ENTRY_TEXT_XPATH)), "Needed entry");
    }

    private IButton getPhoto(Entry entry, SavedPhoto savedPhoto) {
        return getElementFactory().getButton(By.xpath(String.format(
                ENTRY_XPATH, userData.getValue(UserDataValue.ID.getValue()), entry.getPost_id(),
                String.format(ENTRY_PHOTO_XPATH, userData.getValue(UserDataValue.ID.getValue()),
                        savedPhoto.getId()))), "Needed entry");
    }

    private IButton getButtonOpenComments(Entry entry) {
        return getElementFactory().getButton(By.xpath(String.format(
                ENTRY_XPATH, userData.getValue(UserDataValue.ID.getValue()), entry.getPost_id(),
                LINK_SHOW_COMMENTS_XPATH)), "Open comments link");
    }

    private IButton getComment(Entry entry) {
        return getElementFactory().getButton(By.xpath(String.format(
                ENTRY_XPATH, userData.getValue(UserDataValue.ID.getValue()), entry.getPost_id(),
                COMMENT_TEXT_XPATH)), "Needed comment");
    }

    private IButton getLikeButton(Entry entry) {
        return getElementFactory().getButton(By.xpath(String.format(
                ENTRY_XPATH, userData.getValue(UserDataValue.ID.getValue()), entry.getPost_id(),
                LIKE_XPATH)), "Like button");
    }

    public boolean isEntryExist(Entry entry) {
        return getEntry(entry).state().isDisplayed();
    }

    public String getTextEntry(Entry entry) {
        return getEntry(entry).getText();
    }

    public boolean checkPhoto(Entry entry, SavedPhoto savedPhoto) {
        return getPhoto(entry, savedPhoto).state().isClickable();
    }

    public String getCommentText(Entry entry) {
        getButtonOpenComments(entry).clickAndWait();
        return getComment(entry).getText();
    }

    public void likeTheEntry(Entry entry) {
        getLikeButton(entry).clickAndWait();
    }

    public boolean isEntryDeleted(Entry entry) {
        getEntry(entry).state().waitForNotDisplayed();
        return getEntry(entry).state().isDisplayed();
    }
}