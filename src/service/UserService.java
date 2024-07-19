package service;

import com.google.gson.reflect.TypeToken;
import models.Comment;
import models.Like;
import models.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserService implements BaseService<User> {
    public static final String PATH = "C:\\E\\Wanna_Learn\\Java\\instagram\\src\\Docs\\users.json";
    Type listType = new TypeToken<List<User>>() {}.getType();
    IOUtilsService<User> ioUtilsService = new IOUtilsService<>(listType);
    LikeService likeService;

    public UserService(LikeService likeService) {
        this.likeService = likeService;
    }

    @Override
    public User add(User user) {
        return null;
    }
}
