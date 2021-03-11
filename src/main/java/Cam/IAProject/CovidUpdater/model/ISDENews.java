package Cam.IAProject.CovidUpdater.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class ISDENews {
    private final UUID id;
    private final String date;
    private final String name;
    private final String link;

    public ISDENews(@JsonProperty("id") UUID id,
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
}
