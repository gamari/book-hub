package gamari.app.features.books.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import gamari.app.features.books.models.Book;
import lombok.Data;

// TODO isReadingをDoneにする
@Data
public class ReadingBookForm {
    private String title;
    private String bookId;
    private boolean isReading;
    private String isbn10;
    private String isbn13;
    private String thumbnail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Book toBook() {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn10(isbn10);
        book.setIsbn13(isbn13);
        book.setThumbnail(thumbnail);
        return book;
    }
}
