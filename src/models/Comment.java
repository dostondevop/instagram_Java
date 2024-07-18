package models;

import java.util.UUID;

public class Comment {
    private UUID id;
    private UUID userId;
    private String text;
    private UUID replyCommentId;
    private UUID postId;

    public Comment() {
        this.id = UUID.randomUUID();
    }

    public Comment(UUID userId, String text, UUID replyCommentId, UUID postId) {
        this();
        this.userId = userId;
        this.text = text;
        this.replyCommentId = replyCommentId;
        this.postId = postId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(UUID replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }
}
