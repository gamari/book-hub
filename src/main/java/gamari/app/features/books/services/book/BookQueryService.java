package gamari.app.features.books.services.book;

import java.util.Optional;

import gamari.app.features.books.models.Book;

public interface BookQueryService {
    Optional<Book> findBookById(String id);
}
