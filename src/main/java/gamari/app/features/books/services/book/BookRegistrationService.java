package gamari.app.features.books.services.book;

import java.util.Optional;

import gamari.app.features.books.models.Book;

public interface BookRegistrationService {
    Optional<Book> findBookByIsbn(String isbn10, String isbn13);

    Book save(Book book);
}
