package models;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private int comment_id;
    private List<Object> parents_stack;
}