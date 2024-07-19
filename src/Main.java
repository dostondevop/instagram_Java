import models.*;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static LikeService likeService = new LikeService();
    static UserService userService = new UserService(likeService);
    static PostService postService = new PostService(userService);
    static CommentService commentService = new CommentService();
    static NotificationService notificationService = new NotificationService(postService, userService);
    static Scanner scStr = new Scanner(System.in);
    static Scanner scInt = new Scanner(System.in);
    public static void main(String[] args) {

        int stepCode = 10;
        while (stepCode != 0) {
            System.out.println("1. Register, 2. Login, 0. Exit");
            stepCode = scInt.nextInt();
            switch (stepCode) {
                case 1 -> {
                    System.out.println("Enter your username: ");
                    String username = scStr.nextLine();
                    System.out.println("Enter your password: ");
                    String password = scStr.nextLine();
                    User user = new User(username, password);

                    user = userService.add(user);
                    if (user != null) {
                        System.out.println("You have successfully registered!");
                    } else {
                        System.out.println("User already exists!");
                    }
                } case 2 -> {
                    System.out.println("Enter your username: ");
                    String username = scStr.nextLine();
                    System.out.println("Enter your password: ");
                    String password = scStr.nextLine();
                    User user = userService.login(username, password);
                    if (user != null) {
                        System.out.println("You have successfully logged in!");
                        instagram(user);
                    } else {
                        System.out.println("User does not exist!");
                    }
                }
            }
        }
    }

    public static void instagram(User loginUser) {
        System.out.println("Welcome to Instagram!");
        int stepCode = 10;
        while (stepCode != 0) {
            List<Notification> notifications = notificationService.getNewNotifications(loginUser.getId());
            System.out.println("1. Post, 2. Posts, 3. Notifications(" + notifications.size() + "), 4. My posts, 0. Exit");
            stepCode = scInt.nextInt();
            switch (stepCode) {
                case 1 -> {
                    System.out.println("Enter title of the post: ");
                    String title = scStr.nextLine();
                    System.out.println("Enter content of the post: ");
                    String content = scStr.nextLine();
                    System.out.println("Enter the picture of the post: ");
                    String picture = scStr.nextLine();
                    Post post = new Post(loginUser.getId(), title, content, picture);
                    Notification notification = new Notification(loginUser.getId(), post.getId(), title);
                    notificationService.add(notification);

                    post = postService.add(post);
                    if (post != null) {
                        System.out.println("You have successfully posted!");
                    } else {
                        System.out.println("Something went wrong!");
                    }
                }
                case 2 -> {
                    List<Post> posts = postService.getPost();
                    int stepCode1 = 10;
                    while (stepCode1 != 0) {
                        System.out.println("Here are all of the posts, pick one to enter. Press 0 to exit");
                        for (int i = 0; i < posts.size(); i++) {
                            if (!posts.get(i).getIsReadMap().get(loginUser.getId())) {
                                System.out.println("-------------------------Unread messages-------------------------");
                                posts.get(i).setRead(true);
                            }
                            System.out.println("➡️" + i + 1 + "⬅️: " + "|~~~~~~~~~~~~`(*>﹏<*)′~~~~~~~~~~~~");
                            System.out.println("   " + posts.get(i).getPicture());
                            System.out.println("   " + posts.get(i).getTitle());
                            System.out.println("   " + posts.get(i).getText());
                            List<Like> likes = likeService.getLikesByPostId(posts.get(i).getId());
                            List<Comment> comments = commentService.getCommentsByPostId(posts.get(i).getId());
                            System.out.print(comments.size() + " comment(s)");
                            boolean wasLiked = likeService.wasLiked(posts.get(i).getId(), loginUser.getId());
                            if (wasLiked) {
                                System.out.println("❤️" + likes.size() + " like(s)");
                            } else {
                                System.out.println(likes.size() + " like(s)");
                            }
                        }
                        int choice = scInt.nextInt();
                        if (choice == 0) {
                            stepCode1 = 0;
                        } else {
                            Post post = posts.get(choice - 1);
                            int stepCode2 = 10;
                            while (stepCode2 != 0) {
                                System.out.println("Here is the post you wanted to see. Press 0 to exit");
                                System.out.println("   " + "|~~~~~~~~~~~~`(*>﹏<*)′~~~~~~~~~~~~|");
                                System.out.println("      " + post.getPicture());
                                System.out.println("      " + post.getTitle());
                                System.out.println("      " + post.getText());
                                List<Like> likes = likeService.getLikesByPostId(post.getId());
                                List<Comment> comments = commentService.getCommentsByPostId(post.getId());
                                post.getIsReadMap().put(loginUser.getId(), true);
                                System.out.print(comments.size() + " comment(s)");
                                boolean wasLiked = likeService.wasLiked(post.getId(), loginUser.getId());
                                if (wasLiked) {
                                    System.out.println("❤️" + likes.size() + " like(s)");
                                } else {
                                    System.out.println(likes.size() + " like(s)");
                                }

                                int stepCode3 = 10;
                                while (stepCode3 != 0) {
                                    System.out.println("What do you want to do? Press 0 to exit");
                                    System.out.println("1. Reply comment, 2. Write comment 3. Like the post");
                                    stepCode3 = scInt.nextInt();
                                    switch (stepCode3) {
                                        case 1 -> {
                                            int stepCode4 = 10;
                                            while (stepCode4 != 0) {
                                                System.out.println("If you want to reply any comments choose one of them! Press 0 to exit");
                                                for (int i = 0; i < comments.size(); i++) {
                                                    System.out.println(i + 1 + ": " + "🔴" + comments.get(i).getUserName() + "🔴");
                                                    System.out.println("    " + comments.get(i).getText());
                                                }
                                                int choice2 = scInt.nextInt();
                                                if (choice2 == 0) {
                                                    stepCode3 = 0;
                                                } else {
                                                    Comment comment = comments.get(choice2 - 1);
                                                    System.out.println("     🔴" + comment.getUserName() + "🔴");
                                                    System.out.println("    " + comment.getText());
                                                    System.out.println("Enter your reply!");
                                                    String reply = scStr.nextLine();
                                                    Comment comment1 = new Comment(loginUser.getId(), reply, comment.getId(), post.getId());
                                                    commentService.add(comment1);
                                                    System.out.println("Successfully replied to the comment!");
                                                }
                                            }
                                        }
                                        case 2 -> {
                                            System.out.println("Enter your comment to the post!");
                                            String comment = scStr.nextLine();
                                            Comment comment1 = new Comment(loginUser.getId(), comment, post.getId());
                                            commentService.add(comment1);
                                            System.out.println("Successfully wrote comment to the post!");
                                        }
                                        case 3 -> {
                                            if (wasLiked) {
                                                likeService.dislike(post.getId(), loginUser.getId());
                                                System.out.println("Disliked the post!");
                                            } else {
                                                Like like = new Like(loginUser.getId(), post.getId());
                                                likeService.add(like);
                                                System.out.println("Liked the post!");
                                            }
                                            System.out.println("List of people who liked this post.");
                                            List<User> likedUsers = userService.getLikedUsers(likes);
                                            for (int i = 0; i < likedUsers.size(); i++) {
                                                System.out.println(i + 1 + ": " + likedUsers.get(i).getUserName());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                case 3 -> {
                    int stepCode4 = 10;
                    while (stepCode4 != 0) {
                        List<Notification> notifications1 = notificationService.getNewNotifications(loginUser.getId());
                        System.out.println("Here are your notifications, pick one to see. Press 0 to exit");
                        for (int i = 0; i < notifications1.size(); i++) {
                            System.out.println(i + 1 + ": " + notifications1.get(i).getTitle());
                        }
                        int choice = scInt.nextInt();
                        if (choice == 0) {
                            stepCode4 = 0;
                        } else {
                            Notification notification = notifications1.get(choice - 1);
                            notification.getIsReadMap().put(loginUser.getId(), true);
                            notificationService.updateNotification(notification);
                            Post post = postService.getPostById(notification.getPostId());
                            int stepCode5 = scInt.nextInt();
                            while (stepCode5 != 0) {
                                System.out.println("Here is the post you wanted to see. Press 0 to exit");
                                System.out.println("   " + "|~~~~~~~~~~~~`(*>﹏<*)′~~~~~~~~~~~~|");
                                System.out.println("       " + post.getPicture());
                                System.out.println("       " + post.getTitle());
                                System.out.println("       " + post.getText());
                                List<Like> likes = likeService.getLikesByPostId(post.getId());
                                List<Comment> comments = commentService.getCommentsByPostId(post.getId());
                                post.getIsReadMap().put(loginUser.getId(), true);
                                postService.updatePost(post);
                                System.out.print(comments.size() + " comment(s)");
                                boolean wasLiked = likeService.wasLiked(post.getId(), loginUser.getId());
                                if (wasLiked) {
                                    System.out.println("❤️" + likes.size() + " like(s)");
                                } else {
                                    System.out.println(likes.size() + " like(s)");
                                }

                                int stepCode3 = scInt.nextInt();
                                if (stepCode3 == 0) {
                                    stepCode5 = 0;
                                } else {
                                    while (stepCode3 != 0) {
                                        System.out.println("What do you want to do? Press 0 to exit");
                                        System.out.println("1. Reply comment, 2. Write comment 3. Like the post");
                                        stepCode3 = scInt.nextInt();
                                        switch (stepCode3) {
                                            case 1 -> {
                                                int stepCode10 = 10;
                                                while (stepCode10 != 0) {
                                                    System.out.println("If you want to reply any comments choose one of them! Press 0 to exit");
                                                    for (int i = 0; i < comments.size(); i++) {
                                                        System.out.println(i + 1 + ": " + "🔴" + comments.get(i).getUserName() + "🔴");
                                                        System.out.println("    " + comments.get(i).getText());
                                                    }
                                                    int choice2 = scInt.nextInt();
                                                    if (choice2 == 0) {
                                                        stepCode10 = 0;
                                                    } else {
                                                        Comment comment = comments.get(choice2 - 1);
                                                        System.out.println("     🔴" + comment.getUserName() + "🔴");
                                                        System.out.println("    " + comment.getText());
                                                        System.out.println("Enter your reply!");
                                                        String reply = scStr.nextLine();
                                                        Comment comment1 = new Comment(loginUser.getId(), reply, comment.getId(), post.getId());
                                                        commentService.add(comment1);
                                                        System.out.println("Successfully replied to the comment!");
                                                    }
                                                }
                                            }
                                            case 2 -> {
                                                System.out.println("Enter your comment to the post!");
                                                String comment = scStr.nextLine();
                                                Comment comment1 = new Comment(loginUser.getId(), comment, post.getId());
                                                commentService.add(comment1);
                                                System.out.println("Successfully wrote comment to the post!");
                                            }
                                            case 3 -> {
                                                if (wasLiked) {
                                                    likeService.dislike(post.getId(), loginUser.getId());
                                                    System.out.println("Disliked the post!");
                                                } else {
                                                    Like like = new Like(loginUser.getId(), post.getId());
                                                    likeService.add(like);
                                                    System.out.println("Liked the post!");
                                                }
                                                System.out.println("List of people who liked this post.");
                                                List<User> likedUsers = userService.getLikedUsers(likes);
                                                for (int i = 0; i < likedUsers.size(); i++) {
                                                    System.out.println(i + 1 + ": " + likedUsers.get(i).getUserName());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                case 4 -> {
                    List<Post> userPosts = postService.getPostsByUserId(loginUser.getId());
                    int stepCode4 = scInt.nextInt();
                    while (stepCode4 != 0) {
                        System.out.println("Here are your posts. Press 0 to exit");
                        for (int i = 0; i < userPosts.size(); i++) {
                            System.out.println(i + 1 + ": " + userPosts.get(i).getTitle());
                        }
                        stepCode4 = scInt.nextInt();
                    }
                }
            }
        }
    }
}