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
        List<Notification> notifications = ioService.read(PATH);
        addIsRead(notification);
        notifications.add(notification);
        ioService.write(PATH, notifications);
        return notification;
    }

    private void addIsRead(Notification notification) {
        List<User> users = userService.getUsers();
        for (User user : users) {
            notification.getIsReadMap().put(user.getId(), false);
        }
    }

    public Notification updateNotification(Notification notification) {
        List<Notification> notifications = ioService.read(PATH);
        for (Notification notification1 : notifications) {
            if (notification.getId().equals(notification1.getId())) {
                notification1.setIsReadMap(notification.getIsReadMap());
                ioService.write(PATH, notifications);
                return notification1;
            }
        }
        return null;
    }

    public List<Notification> getNewNotifications(UUID userId) {
        List<Notification> notifications = ioService.read(PATH);
        List<Notification> filteredNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (!notification.getUserId().equals(userId)) {
                if (!notification.getIsReadMap().get(userId)) {
                    filteredNotifications.add(notification);
                }
            }

        }
        return filteredNotifications;
    }
}

}
