package gamari.app.features.books.services.reading_book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.ReadingBookMapper;
import gamari.app.features.users.models.User;

@Service
public class ReadingBookQueryServiceImpl implements ReadingBookQueryService {
    @Autowired
    private ReadingBookMapper readingBookMapper;

    @Override
    public boolean isBookRegisteredByUser(String bookId, User user) {
        return readingBookMapper.countReadingBooksByBookIdAndUser(bookId, user.getId()) > 0;
    }

    @Override
    public int countReadingBooksByBookId(String bookId) {
        return readingBookMapper.countReadingBooksByBookId(bookId);

    }
}
