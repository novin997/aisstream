package com.project.aisstream;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.aisstream.model.AISMessage;
import com.project.aisstream.repository.AisMessageRepository;
import com.project.aisstream.util.JsonWriter;

@Component
public class AisStreamWebSocket extends WebSocketClient {
    private final static String URL = "wss://stream.aisstream.io/v0/stream";

    @Autowired
    JsonWriter jsonWriter;

    @Autowired
    AisMessageRepository aisMessageRepository;

    @Value("${aisstream.api-key}")
    private String API_KEY;

    private ObjectMapper objectMapper;
    private Long tracksCount;
    private Set<Long> mmsiSet = new HashSet<Long>();

    public AisStreamWebSocket() throws URISyntaxException {
        super(new URI(URL));
        this.objectMapper = new ObjectMapper();
        this.tracksCount = 0L;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        // send subscription message upon connection
        // send("{\"APIKey\":\"<key>\",\"BoundingBoxes\":[[[2.34120,102.116109],[1.214457,104.641903]]]}");
        send("{\"APIKey\":\"" + API_KEY + "\",\"BoundingBoxes\":[[[-90,-180],[90,180]]]}");
    }

    @Scheduled(fixedRate = 60000)
    public void displayTracksCount() {
        System.out.printf("Total Tracks processed: %d\n", this.tracksCount);
        System.out.printf("Number of Unique Tracks processed: %d\n", mmsiSet.size());
    }

    @Override
    public void onMessage(ByteBuffer message) {
        String jsonString = StandardCharsets.UTF_8.decode(message).toString();
        jsonWriter.appendDataToJson(jsonString);
        this.tracksCount++;
        try {
            AISMessage aisMessage = objectMapper.readValue(jsonString, AISMessage.class);
            // System.out.println(aisMessage.toString());
            aisMessageRepository.save(aisMessage);
            mmsiSet.add(aisMessage.getMetaData().getMmsi());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onMessage(String message) {
        // TODO Auto-generated method stub

    }
}
