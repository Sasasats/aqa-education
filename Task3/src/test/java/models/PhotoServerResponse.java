package models;

import lombok.Data;

@Data
public class PhotoServerResponse {
    private int server;
    private String photo;
    private String hash;
}