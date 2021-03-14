package Cam.IAProject.CovidUpdater.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class SHSENews {
    private final UUID id;
    private String date;
    private String name;
    private String link;

    public SHSENews(@JsonProperty("id") UUID id,
                 @JsonProperty("date") String date,
                 @JsonProperty("name") String name,
                 @JsonProperty("link") String link) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.link = link;
    }


    public UUID getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setLink(String link){
        this.link = link;
    }
    public void setDate(String date){
        this.date = date;
    }
}
