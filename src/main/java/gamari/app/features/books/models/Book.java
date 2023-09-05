package gamari.app.features.books.models;

import lombok.Data;
import java.util.Date;

@Data
public class Book {
    private String id;
    private String title;
    private String isbn10;
    private String isbn13;
    private String thumbnail;
    private String author;
    private Date publishedDate;
    private String genre;
    private String summary;
}
