package com.project.aisstream.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.aisstream.util.DateDeserializer;

import lombok.Data;

@Data
public class MetaData {
    @JsonProperty("MMSI")
    private Long mmsi;

    @JsonProperty("ShipName")
    private String shipName;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonDeserialize(using = DateDeserializer.class)
    @JsonProperty("time_utc")
    private Date timeUTC;
}
