package com.project.aisstream.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("time_utc")
    private String timeUTC;
}
