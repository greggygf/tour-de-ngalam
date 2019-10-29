package com.bluohazard.tourdengalam.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Mountain {
    public int id;
    public String name;

    public Mountain()
    {

    }

    public Mountain(int id, String name) {
        this.id = id;
        this.name = name;
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
        return result;
    }
}
