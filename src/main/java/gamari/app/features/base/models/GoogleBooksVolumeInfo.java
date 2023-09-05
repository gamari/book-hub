package gamari.app.features.base.models;

import gamari.app.features.books.models.Book;
import lombok.Data;

@Data
public class GoogleBooksVolumeInfo {
    private String title;
    private String publisher;
    private String[] authors;
    private String description;
    private ImageLinks imageLinks;
    private Identifier[] industryIdentifiers;

    public Book toBook() {
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.authors[0]);
        book.setSummary(this.description);
        book.setThumbnail(this.imageLinks.getThumbnail());
        book.setIsbn10(this.industryIdentifiers[0].getIdentifier());
        book.setIsbn13(this.industryIdentifiers[1].getIdentifier());

        return book;
    }
}

@Data
class ImageLinks {
    private String smallThumbnail;
    private String thumbnail;
}

@Data
class Identifier {
    private String type;
    private String identifier;
}