package models;

import java.util.UUID;

public class Notification {
    private UUID id;
    private UUID userId;
    private UUID postId;
    private boolean isRead;

    public Notification() {
        this.id = UUID.randomUUID();
    }

    public Notification(UUID userId, UUID postId, boolean isRead) {
        this();
        this.userId = userId;
        this.postId = postId;
        this.isRead = isRead;
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

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
