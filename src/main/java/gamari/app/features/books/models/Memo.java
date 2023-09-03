package gamari.app.features.books.models;

import java.util.Date;

import lombok.Data;

@Data
public class Memo {
    private String id;
    private String readingBookId;
    private String content;
    private Date createdAt;
}
