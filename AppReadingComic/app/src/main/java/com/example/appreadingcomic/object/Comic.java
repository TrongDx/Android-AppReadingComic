package com.example.appreadingcomic.object;

public class Comic {
    private int ID;
    private String nameComic;
    private String linkComic;
    private String content;
    private int ID_TK;
    public Comic(int ID, String nameComic, String linkComic, String content) {
        this.ID = ID;
        this.nameComic = nameComic;
        this.linkComic = linkComic;
        this.content = content;
    }
    public Comic(int ID, String nameComic, String linkComic, String content, int ID_TK) {
        this.ID = ID;
        this.nameComic = nameComic;
        this.linkComic = linkComic;
        this.content = content;
        this.ID_TK = ID_TK;
    }
    public Comic(String nameComic, String linkComic, String content, int ID_TK) {
        this.nameComic = nameComic;
        this.linkComic = linkComic;
        this.content = content;
        this.ID_TK = ID_TK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getLinkComic() {
        return linkComic;
    }

    public void setLinkComic(String linkComic) {
        this.linkComic = linkComic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}
