package com.example.finalcloudproject.Models;

public class Info {
    String intro;
    String intro_image;

    public Info() {
    }

    public Info(String intro, String intro_image) {
        this.intro = intro;
        this.intro_image = intro_image;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro_image() {
        return intro_image;
    }

    public void setIntro_image(String intro_image) {
        this.intro_image = intro_image;
    }

}
