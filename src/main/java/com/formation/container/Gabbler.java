package com.formation.container;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Gabbler implements Serializable {
    private int id;
    private String message;
    private boolean isLiked;
    private boolean isDeletable;
    private List<String> hashtags;
    private Date date;


    public Gabbler() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isDeletable() {
        return isDeletable;
    }

    public void setDeletable(boolean deletable) {
        isDeletable = deletable;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Gabbler{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", isLiked=" + isLiked +
                ", isDeletable=" + isDeletable +
                ", hashtags=" + hashtags +
                ", date=" + date +
                '}';
    }
}
