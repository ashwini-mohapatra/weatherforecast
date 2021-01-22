package com.ashwini.weatherforecast.Model.Free;

public class Weather {
    int id;
    String clear;
    String description;
    String icon;

    public Weather(int id, String clear, String description, String icon) {
        this.id = id;
        this.clear = clear;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
