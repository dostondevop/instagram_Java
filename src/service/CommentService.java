package service;

import com.google.gson.reflect.TypeToken;
import models.Comment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentService implements BaseService<Comment> {
    public static final String PATH = "C:\\E\\Wanna_Learn\\Java\\instagram\\src\\Docs\\comments.json";
    Type listType = new TypeToken<List<Comment>>() {}.getType();
    IOUtilsService<Comment> ioUtilsService = new IOUtilsService<>(listType);

    @Override
    public Comment add(Comment comment) {
        List<Comment> comments = ioUtilsService.read(PATH);
        comments.add(comment);
        ioUtilsService.write(PATH, comments);
        return comment;
    }

    public List<Comment> getCommentsByPostId(UUID postId) {
        List<Comment> comments = ioUtilsService.read(PATH);
        List<Comment> commentsByPostId = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getPostId().equals(postId)) {
                commentsByPostId.add(comment);
            }
        }
        return commentsByPostId;
    }

    public List<Comment> getCommentsByReply(int replyId) {
        List<Comment> comments = ioUtilsService.read(PATH);
        List<Comment> commentsByUserId = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getReplyCommentId().equals(replyId)) {
                commentsByUserId.add(comment);
            }
        }
        return commentsByUserId;
    }
}