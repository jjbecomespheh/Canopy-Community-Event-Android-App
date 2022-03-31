package com.example.project;

import android.graphics.Bitmap;

import com.example.project.Utils.BitmapEncoder;

public class Encode extends BitmapEncoder {
    public String title;
    public String description;
    public Bitmap photo;
    public String link;
    public String hashtag;
    public String encoded_photo;

    public String getDescription() {
        return description;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    Encode(String title, String hashtag, String description, Bitmap photo, String link){
        this.title = title;
        this.description = description;
        this.photo = photo;
        this.hashtag = hashtag;
        this.link = link;
        this.encoded_photo = BitmapEncoder.encodeImage(this.photo);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEncoded_photo() {
        return encoded_photo;
    }

    public void setEncoded_photo(String encoded_photo) {
        this.encoded_photo = encoded_photo;
    }
}
