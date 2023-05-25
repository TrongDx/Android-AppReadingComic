package com.example.appreadingcomic.object;

public class Comic {
    private String nameComic, nameChap, linkComic;

    public Comic(){

    }

    public Comic(String nameComic, String nameChap, String linkComic) {
        this.nameComic = nameComic;
        this.nameChap = nameChap;
        this.linkComic = linkComic;
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getNameChap() {
        return nameChap;
    }

    public void setNameChap(String nameChap) {
        this.nameChap = nameChap;
    }

    public String getLinkComic() {
        return linkComic;
    }

    public void setLinkComic(String linkComic) {
        this.linkComic = linkComic;
    }
}
