package gamari.app.features.books.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.BookMapper;
import gamari.app.features.books.models.Book;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public Optional<Book> findBookByIsbn(String isbn10, String isbn13) {
        return bookMapper.findBookByIsbn(isbn10, isbn13);
    }

    public Book getOrCreateBook(String title, String isbn10, String isbn13) {
        return findBookByIsbn(isbn10, isbn13).orElseGet(() -> {
            Book newBook = new Book();
            newBook.setId(UUID.randomUUID().toString());
            newBook.setTitle(title);
            newBook.setIsbn10(isbn10);
            newBook.setIsbn13(isbn13);
            save(newBook);
            return newBook;
        });
    }

    public int save(Book book) {
        return bookMapper.save(book);
    }
}
