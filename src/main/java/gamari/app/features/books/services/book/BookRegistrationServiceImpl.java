package gamari.app.features.books.services.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.BookMapper;
import gamari.app.features.books.models.Book;

@Service
public class BookRegistrationServiceImpl implements BookRegistrationService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Optional<Book> findBookByIsbn(String isbn10, String isbn13) {
        return bookMapper.findBookByIsbn(isbn10, isbn13);
    }

    @Override
    public Book save(Book book) {
        bookMapper.save(book);
        return book;
    }
}
