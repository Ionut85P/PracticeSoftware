package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBrandModel {
    @JsonProperty("name")
    private  String name;
    @JsonProperty("slug")
    private  String slug;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public RequestBrandModel(String name, String slug) {
        this.name = name;
        this.slug = slug;
        String random = String.valueOf(System.currentTimeMillis());
        this.name = random +this.name;
        this.slug = random +this.slug;
    }
}
