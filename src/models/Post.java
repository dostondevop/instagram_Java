package models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Post {
    private UUID id;
    private UUID userId;
    private String title;
    private String text;
    private String picture;
    private boolean isRead;
    private Map<UUID, Boolean> isReadMap = new HashMap<>();

    public Post() {
        this.id = UUID.randomUUID();
    }

    public Post(UUID userId, String title, String text, String picture) {
        this();
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.picture = picture;
    }

    public UUID getId() {
        return id;
    }

    public Map<UUID, Boolean> getIsReadMap() {
        return isReadMap;
    }

    public void setIsReadMap(Map<UUID, Boolean> isReadMap) {
        this.isReadMap = isReadMap;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
