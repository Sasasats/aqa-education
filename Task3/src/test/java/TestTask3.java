import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import forms.MainPage;
import forms.MyPage;
import forms.WelcomePage;
import models.Comment;
import models.DeletedEntry;
import models.Entry;
import models.Like;
import models.savePhoto.SavedPhoto;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResourcesData;
import utils.Steps;
import utils.UserDataValue;
import utils.VkApiUtils;

import java.util.List;

public class TestTask3 extends BaseTest {
    private final Steps step = new Steps();
    private final WelcomePage welcomePage = new WelcomePage();
    private final MainPage mainPage = new MainPage();
    private final MyPage myPage = new MyPage();

    private final ISettingsFile userData = new JsonSettingsFile("userAuthorizationData.json");

    @Test
    public void testCase() {
        Assert.assertTrue(welcomePage.state().waitForDisplayed(),
                "Welcome page not open, start element not displayed!");

        logger.info("Authorization");
        step.authorization(userData.getValue(UserDataValue.LOGIN.getValue()).toString(),
                userData.getValue(UserDataValue.PASSWORD.getValue()).toString());
        Assert.assertTrue(mainPage.state().waitForDisplayed(), "Main Page not open, authorization failed");

        logger.info("Go to 'My page'");
        mainPage.goToMyPage();
        Assert.assertTrue(myPage.state().waitForDisplayed(), "'My page' not open");

        logger.info("Creating a entry using API");
        String randomString = RandomString.make(5);
        Entry postedEntry = VkApiUtils.postEntry(randomString);

        logger.info("Check that an entry with the correct text has appeared from the correct user");
        Assert.assertTrue(myPage.isEntryExist(postedEntry), "Entry not find");
        Assert.assertEquals(myPage.getTextEntry(postedEntry), randomString, "Text is not correct");

        logger.info("Editing a post using API (change text, add a picture)");
        List<SavedPhoto> savedPhoto = step.pictureSending(ResourcesData.PATH_TO_PHOTO.getValue());

        String newRandomString = RandomString.make(10);
        Entry updatedEntry = VkApiUtils.updateEntry(postedEntry, newRandomString, savedPhoto.get(0));

        logger.info("Сheck that the message text has changed and the uploaded picture has been added");
        Assert.assertEquals(myPage.getTextEntry(updatedEntry), newRandomString, "Text is not correct");
        Assert.assertTrue(myPage.checkPhoto(updatedEntry, savedPhoto.get(0)), "Needed photo has benn added");

        logger.info("Adding a comment with random text using API");
        String randomStringForComment = RandomString.make(15);
        Comment comment = VkApiUtils.createComment(updatedEntry, randomStringForComment);

        logger.info("Check that a comment from the correct user has been added to the desired post");
        Assert.assertEquals(myPage.getCommentText(updatedEntry), randomStringForComment,
                "Text in needed comment not equals");

        logger.info("Like the post using UI");
        myPage.likeTheEntry(postedEntry);

        logger.info("Check for like using API");
        Like like = VkApiUtils.isLiked(postedEntry);
        Assert.assertEquals(like.getLiked(), 1, "Entry is not liked");

        logger.info("Delete created entry using API");
        DeletedEntry deleteEntry = VkApiUtils.deleteEntry(postedEntry);

        logger.info("Check that entry has been deleted using UI");
        Assert.assertFalse(myPage.isEntryDeleted(updatedEntry), "Entry is not deleted");
    }
}