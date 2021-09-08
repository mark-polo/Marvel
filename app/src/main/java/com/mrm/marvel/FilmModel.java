package com.mrm.marvel;

public class FilmModel {
    private String Poster;
    private String Name;

    public FilmModel() { }

    public FilmModel(String  poster, String name) {
        this.Poster = poster;
        this.Name = name;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
