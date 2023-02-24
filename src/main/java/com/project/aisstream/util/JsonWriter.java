package com.project.aisstream.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonWriter {
    Long count;
    ObjectMapper mapper;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
    String fileName;

    JsonWriter() {
        Date date = new Date();
        this.fileName = "AISData_" + formatter.format(date) + ".json";
        // File file = new File(this.fileName);
        this.count = 0L;
        this.mapper = new ObjectMapper();
    }

    @Async("threadPoolTaskExecutor")
    public synchronized void appendDataToJson(String jsonString) {
        try {
            System.out.println(Thread.currentThread().getName() + " start");
            FileOutputStream fos = new FileOutputStream(new File(this.fileName), true);
            Object jsonObject = this.mapper.readValue(jsonString, Object.class);
            // String jsonPretty =
            // this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            // System.out.println(jsonPretty);
            this.mapper.writerWithDefaultPrettyPrinter().writeValue(fos, jsonObject);
            fos.close();
            fos = new FileOutputStream(new File(this.fileName), true);
            fos.write(',');
            fos.write('\n');
            System.out.println(Thread.currentThread().getName() + " end");
            // this.mapper.writerWithDefaultPrettyPrinter().writeValue(fos, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // this.mapper.writeValue(fos, "test");
    }
}
