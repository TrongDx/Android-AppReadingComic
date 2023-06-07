package com.example.appreadingcomic.object;

public class Comic {
    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;
    private String nameComic;

    public int getID() {
        return ID;
    }

    private String nameChap;
    private String linkComic;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public Comic(int id, String content){
        ID = id;

        this.content = content;
    }

    public Comic(int id, String nameComic, String nameChap, String linkComic, String content) {
        ID = id;
        this.nameComic = nameComic;
        this.nameChap = nameChap;
        this.linkComic = linkComic;
        this.content = content;
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
