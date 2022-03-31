package com.example.project;

public class Posts {
    public String poster;
    public String description;
    public String encoded_photo;

    public Posts(){

    }

    public Posts(String poster, String description, String encoded_photo, String hashtag, String link, String title) {
        this.poster = poster;
        this.description = description;
        this.encoded_photo = encoded_photo;
        this.hashtag = hashtag;
        this.link = link;
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncoded_photo() {
        return encoded_photo;
    }

    public void setEncoded_photo(String encoded_photo) {
        this.encoded_photo = encoded_photo;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String hashtag;
    public String link;
    public String title;

}
