package com.example.finalcloudproject.Models;

import java.net.URI;

public class Advice {
    String advice_text;
    String advice_image;
    public Advice(String advice_text, String advice_image) {
        this.advice_text = advice_text;
        this.advice_image= advice_image;
    }
    public String getadvice_text() {
        return advice_text;
    }

    public void setAdvice(String advice_text) {
        this.advice_text = advice_text;
    }

    public String getImage() {
        return advice_image;
    }

    public void setImage(String advice_image) {
        this.advice_image = advice_image;
    }


}
