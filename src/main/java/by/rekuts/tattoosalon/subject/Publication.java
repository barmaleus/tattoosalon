package by.rekuts.tattoosalon.subject;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Publication implements Serializable {
    private int id;
    private String title;
    private String content;
    private boolean textNotPhoto;
    private String author;
    private LocalDateTime publishTime;
    private boolean blocked;

    public Publication() {
    }

    public Publication(String title, String content, boolean textNotPhoto, String author) {
        this.title = title;
        this.content = content;
        this.textNotPhoto = textNotPhoto;
        this.author = author;
    }

    public Publication(int id, String title, String content, boolean textNotPhoto, String author, LocalDateTime publishTime, boolean blocked) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.textNotPhoto = textNotPhoto;
        this.author = author;
        this.publishTime = publishTime;
        this.blocked = blocked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isTextNotPhoto() {
        return textNotPhoto;
    }

    public void setTextNotPhoto(boolean textNotPhoto) {
        this.textNotPhoto = textNotPhoto;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
