package com.project.aisstream.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AISMessage {
    @JsonProperty("MessageType")
    private String messageType;

    @JsonProperty("MetaData")
    private MetaData metaData;

}
