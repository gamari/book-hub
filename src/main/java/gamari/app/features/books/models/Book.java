package gamari.app.features.books.models;

import lombok.Data;
import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

@Data
public class Book {
    private String id;
    private String title;
    private String isbn10;
    private String isbn13;
    private String author;
    private Date publishedDate;
    private String genre;
    private String summary;
}
