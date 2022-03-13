package com.fvilla.CourseManagmentSystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "course_content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "videoName")
    private String name;

    @Column(name = "videoURL")
    private String videoURL;

    @Column(name = "video_explanation")
    private String videoExplanation;

    public Content() {
    }

    public Content(String name, String videoURL, String videoExplanation) {
        this.name = name;
        this.videoURL = videoURL;
        this.videoExplanation = videoExplanation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoExplanation() {
        return videoExplanation;
    }

    public void setVideoExplanation(String videoExplanation) {
        this.videoExplanation = videoExplanation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", videoURL='" + videoURL + '\'' +
                ", videoExplanation='" + videoExplanation + '\'' +
                '}';
    }
}
