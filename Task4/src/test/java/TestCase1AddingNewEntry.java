import models.apiModels.Post;
import models.apiModels.userModel.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.APIUtils;
import utils.JsonUtils;
import utils.RandomUtils;
import utils.SortUtils;
import utils.enums.PostsParams;
import utils.enums.UsersParams;

import java.util.List;

public class TestCase1AddingNewEntry extends BaseTestForTestCase1 {
    private final APIUtils api = new APIUtils();

    @Test
    public void testAPI() {
        logger.info("Получение списка всех постов");
        Assert.assertTrue(SortUtils.isPostsSortedById(api.getPostList()));

        logger.info("Получение поста с id 99");
        Post expectedPost = JsonUtils.setPost(PostsParams.VALID_POST_ID.getValue());
        Post actualPost = api.getPost(expectedPost.getId());

        Assert.assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "userId not equals");
        Assert.assertEquals(actualPost.getId(), expectedPost.getId(), "id not equals");
        Assert.assertFalse(actualPost.getTitle().isEmpty(), "title is empty");
        Assert.assertFalse(actualPost.getBody().isEmpty(), "body is empty");

        logger.info("Получение несуществующего поста с id 150");
        api.getNotValidPostAsString(PostsParams.NOT_VALID_POST_ID.getValue());

        logger.info("Отправка поста");
        expectedPost.setBody(RandomUtils.getRandomString(5));
        expectedPost.setTitle(RandomUtils.getRandomString(5));
        expectedPost.setUserId(1);
        expectedPost.setId(0);

        actualPost = api.getPostedPost(expectedPost);
        Assert.assertEquals(actualPost.getBody(), expectedPost.getBody(), "body not equals");
        Assert.assertEquals(actualPost.getTitle(), expectedPost.getTitle(), "title not equals");
        Assert.assertEquals(actualPost.getUserId(), expectedPost.getUserId(), "userId not equals");
        Assert.assertFalse(String.valueOf(actualPost.getId()).isEmpty(), "id is empty");

        logger.info("Получение списка пользователей");
        List<User> userList = api.getUserList();
        User expectedUser = JsonUtils.setUser(UsersParams.VALID_USER_ID.getValue());
        User actualUser = api.getUserFromList(userList, expectedUser.getId());
        Assert.assertEquals(actualUser, expectedUser, "Users not equals");

        logger.info("Получение пользователя c id 5");
        actualUser = api.getUser(expectedUser.getId());
        Assert.assertEquals(expectedUser, actualUser, "Users not equals");
    }
}

