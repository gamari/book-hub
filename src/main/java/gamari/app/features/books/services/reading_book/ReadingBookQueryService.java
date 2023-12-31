package gamari.app.features.books.services.reading_book;

import java.util.List;
import java.util.Optional;

import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.models.ReadingBookWithThumbnail;
import gamari.app.features.users.models.User;

public interface ReadingBookQueryService {
    boolean isBookRegisteredByUser(String bookId, User user);

    int countReadingBooksByBookId(String bookId);

    int countReadingBookWithStatusDone(String userId);

    Optional<ReadingBook> findReadingBookById(String id);

    List<ReadingBook> findReadingBookByUserId(String userId);

    List<ReadingBookWithThumbnail> findReadingBookWithThumbnailByUserId(String userId);
}
