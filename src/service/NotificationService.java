package service;

import com.google.gson.reflect.TypeToken;
import models.Comment;
import models.Notification;
import models.Post;
import models.User;

import java.lang.reflect.Type;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationService implements BaseService<Notification> {
    public static final String PATH = "C:\\E\\Wanna_Learn\\Java\\instagram\\src\\Docs\\notifications.json";
    Type listType = new TypeToken<List<Notification>>() {}.getType();
    IOUtilsService<Notification> ioService = new IOUtilsService<>(listType);
    PostService postService;
    UserService userService;

    public NotificationService(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public Notification add(Notification notification) {
        return null;
    }
}