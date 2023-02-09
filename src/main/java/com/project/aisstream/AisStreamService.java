package com.project.aisstream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AisStreamService {

    private AisStreamWebSocket aisStream;

    @Autowired
    public AisStreamService(AisStreamWebSocket aisStream) {
        this.aisStream = aisStream;
        this.aisStream.connect();
    }

}
