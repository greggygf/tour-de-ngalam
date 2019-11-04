package com.bluohazard.tourdengalam.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Playground {
    public int id;
    public String name;
    public String image_url;

    public Playground()
    {}

    public Playground(int id, String name, String image_url) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("name", name);
        result.put("image_url", image_url);
        return result;
    }
}
