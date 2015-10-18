package com.example.rohan.podcast;

import java.io.Serializable;

/**
 * Created by rohan on 10/16/15.
 */
public class PodCasts implements Serializable {
    //Title, Description, Publication date, Image URL, Duration, and
//    MP3 file URL

    private String description, publishDate, imageURL, duration, url, title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PodCasts{" +
                "description='" + description + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", duration='" + duration + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
