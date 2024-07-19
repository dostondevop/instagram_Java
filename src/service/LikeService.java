package service;

import com.google.gson.reflect.TypeToken;
import models.Comment;
import models.Like;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LikeService implements BaseService<Like> {
    public static final String PATH = "C:\\E\\Wanna_Learn\\Java\\instagram\\src\\Docs\\likes.json";
    Type listType = new TypeToken<List<Like>>() {}.getType();
    IOUtilsService<Like> io = new IOUtilsService<>(listType);

    @Override
    public Like add(Like like) {
        List<Like> likes = io.read(PATH);
        boolean wasLiked = wasLiked(like.getPostId(), like.getUserId());
        if (!wasLiked) {
            likes.add(like);
            io.write(PATH, likes);
            return like;
        } else {
            likes.remove(like);
            io.write(PATH, likes);
            return null;
        }
    }

    public boolean dislike(UUID postId, UUID userId) {
        List<Like> likes = io.read(PATH);
        for (Like like : likes) {
            if (like.getPostId().equals(postId) && like.getUserId().equals(userId)) {
                likes.remove(like);
                io.write(PATH, likes);
                return true;
            }
        }
        return false;
    }

    public boolean wasLiked(UUID postId, UUID userId) {
        List<Like> likes = io.read(PATH);
        for (Like l : likes) {
            if (l.getPostId().equals(postId) && l.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public List<Like> getLikesByPostId(UUID postId) {
        List<Like> likes = io.read(PATH);
        List<Like> likesByPostId = new ArrayList<>();
        for (Like like : likes) {
            if (like.getPostId().equals(postId)) {
                likesByPostId.add(like);
            }
        }
        return likesByPostId;
    }
}