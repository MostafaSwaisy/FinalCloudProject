package com.example.finalcloudproject.Models;

public class News {
    String title;
    String URL;
    String description;
    String image_URL;
    String date;
    public News(String title, String URL, String description, String image_URL, String date) {
        this.title = title;
        this.URL = URL;
        this.description = description;
        this.image_URL = image_URL;
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getURL() {
        return URL;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage_URL() {
        return image_URL;
    }
    public void setImage_URL(String image_URL) {
        this.image_URL = image_URL;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
