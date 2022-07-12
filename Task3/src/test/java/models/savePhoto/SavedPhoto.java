package models.savePhoto;

import lombok.Data;

import java.util.List;

@Data
public class SavedPhoto {
    private int album_id;
    private int date;
    private int id;
    private int owner_id;
    private boolean has_tags;
    private String access_key;
    private List<Size> sizes;
    private String text;
}