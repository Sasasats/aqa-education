package utils;

import forms.WelcomePage;
import models.UploadLink;
import models.UploadedPhoto;
import models.savePhoto.SavedPhoto;

import java.util.List;

public class Steps {
    private final WelcomePage welcomePage = new WelcomePage();

    public void authorization(String login, String password) {
        welcomePage.enterPhone(login);
        welcomePage.enterPassword(password);
        welcomePage.signIn();
    }

    public List<SavedPhoto> pictureSending(String photoPath) {
        UploadLink uploadLink = VkApiUtils.getUploadLink();
        UploadedPhoto uploadedPhoto = VkApiUtils.uploadPhoto(uploadLink, photoPath);
        return VkApiUtils.savePhoto(uploadedPhoto);
    }
}
