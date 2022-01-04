package models;

import lombok.Data;

@Data
public class UploadLink {
    private int album_id;
    private String upload_url;
    private int user_id;
}