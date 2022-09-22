package com.restapi.firstjavacrud.viewers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiBodyResponseViewer {
    private String message = "";
    private final String ZONEID = "America/Sao_Paulo";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String timestamp = ZonedDateTime.now(ZoneId.of(ZONEID))
            .format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z"));

    public ApiBodyResponseViewer(
            String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}