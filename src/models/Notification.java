package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Notification {
    private UUID id;
    private String title;
    private UUID userId;
    private UUID postId;
    private Map<UUID, Boolean> isReadMap = new HashMap<>();

    public Notification() {
        this.id = UUID.randomUUID();
    }

    public Notification(UUID userId, UUID postId, String title) {
        this();
        this.userId = userId;
        this.postId = postId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Map<UUID, Boolean> getIsReadMap() {
        return isReadMap;
    }

    public void setIsReadMap(Map<UUID, Boolean> isReadMap) {
        this.isReadMap = isReadMap;
    }
}
