package gamari.app.features.books.services.reading_book;

import java.util.List;
import java.util.Optional;

import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.users.models.User;

public interface ReadingBookQueryService {
    boolean isBookRegisteredByUser(String bookId, User user);

    int countReadingBooksByBookId(String bookId);
    // Optional<ReadingBook> findById(String id);
    // List<ReadingBook> findByUserId(String userId);
}
