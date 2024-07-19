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
        List<User> users = ioUtilsService.read(PATH);
        if (hasUser(user)) {
            return null;
        }
        users.add(user);
        ioUtilsService.write(PATH, users);
        return user;
    }

    public User login(String username, String password) {
        List<User> users = ioUtilsService.read(PATH);
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private boolean hasUser(User user) {
        List<User> users = ioUtilsService.read(PATH);
        for (User u : users) {
            if (u.getUserName().equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    public List<User> getLikedUsers(List<Like> likes) {
        List<User> users = ioUtilsService.read(PATH);
        List<User> likedUsers = new ArrayList<>();
        for (Like like : likes) {
            for (User user : users) {
                if (like.getUserId().equals(user.getId())) {
                    likedUsers.add(user);
                }
            }
        }
        return likedUsers;
    }

    public List<User> getUsers() {
        return ioUtilsService.read(PATH);
    }
}