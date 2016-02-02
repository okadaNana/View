package com.owen.view.model;

import java.util.List;

/**
 * 一条朋友圈
 *
 * Created by Owen on 2016/2/2.
 */
public class ModelMoment {

    private String text;
    private List<ModelImage> mImages;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ModelImage> getmImages() {
        return mImages;
    }

    public void setmImages(List<ModelImage> mImages) {
        this.mImages = mImages;
    }

}
