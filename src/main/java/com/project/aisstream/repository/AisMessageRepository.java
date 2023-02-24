package com.project.aisstream.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.aisstream.model.AISMessage;
import com.project.aisstream.model.MetaData;

@Repository
public class AisMessageRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(AISMessage aisMessage) {
        MetaData metaData = aisMessage.getMetaData();
        jdbcTemplate.update("INSERT INTO test.METADATA (mmsi,shipName,latitude,longitude,timeUTC) VALUES (?,?,?,?,?)",
                metaData.getMmsi(), metaData.getShipName(), metaData.getLatitude(), metaData.getLongitude(),
                metaData.getTimeUTC());
    }
}
