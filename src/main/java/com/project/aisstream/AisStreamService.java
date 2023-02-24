package com.project.aisstream;

import org.springframework.stereotype.Service;

@Service
public class AisStreamService {

    private AisStreamWebSocket aisStream;

    public AisStreamService(AisStreamWebSocket aisStream) {
        this.aisStream = aisStream;
        this.aisStream.connect();
    }
}
