package service;

import com.google.gson.reflect.TypeToken;
import models.Comment;
import models.Post;
import models.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostService implements BaseService<Post> {
    public static final String PATH = "C:\\E\\Wanna_Learn\\Java\\instagram\\src\\Docs\\posts.json";
    Type listType = new TypeToken<List<Post>>() {}.getType();
    IOUtilsService<Post> ioUtilsService = new IOUtilsService<>(listType);
    UserService userService;

    public PostService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Post add(Post post) {
        return null;
    }
}
