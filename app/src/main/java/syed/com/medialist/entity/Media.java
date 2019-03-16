package syed.com.medialist.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media {
//    private int id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

//    public Media(int id, String url, int duration, String title, String description, String thumbnail) {
//        this.id = id;
//        this.url = url;
//        this.duration = duration;
//        this.title = title;
//        this.description = description;
//        this.thumbnail = thumbnail;
//    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
