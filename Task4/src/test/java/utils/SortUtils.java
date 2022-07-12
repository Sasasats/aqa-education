package utils;

import models.apiModels.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortUtils {
    public static boolean isPostsSortedById(List<Post> posts) {
        ArrayList<Integer> postsIdList = new ArrayList<>();

        for (Post post : posts) {
            postsIdList.add(post.getId());
        }

        ArrayList<Integer> sortedPostsIdList = postsIdList;
        Collections.sort(sortedPostsIdList);

        return postsIdList.equals(sortedPostsIdList);
    }
}