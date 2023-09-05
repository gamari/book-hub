package gamari.app.features.books.services.book;

import java.util.Optional;
import java.util.UUID;

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
    public Book register(Book book) {
        String isbn10 = book.getIsbn10();
        String isbn13 = book.getIsbn13();

        Optional<Book> optBook = findBookByIsbn(isbn10, isbn13);
        if (optBook.isPresent()) {
            return optBook.get();
        }

        book.setId(UUID.randomUUID().toString());
        bookMapper.save(book);
        return book;
    }
}
