package com.gavin.bean;

import org.springframework.web.multipart.MultipartFile;

public class Img {
    private MultipartFile imgFile;

    private String imgName;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }


}
