package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseBrandModel {


    @JsonProperty("id")
    private  String id;
    @JsonProperty("name")
    private  String name;
    @JsonProperty("slug")
    private  String slug;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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


}
