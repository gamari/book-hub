package gamari.app.features.books.services.reading_book;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.ReadingBookMapper;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.models.ReadingBookWithThumbnail;
import gamari.app.features.users.models.User;

@Service
public class ReadingBookQueryServiceImpl implements ReadingBookQueryService {
    @Autowired
    private ReadingBookMapper readingBookMapper;

    @Override
    public Optional<ReadingBook> findReadingBookById(String id) {
        return readingBookMapper.findById(id);
    }

    @Override
    public List<ReadingBook> findReadingBookByUserId(String userId) {
        return readingBookMapper.findByUserId(userId);
    }

    @Override
    public List<ReadingBookWithThumbnail> findReadingBookWithThumbnailByUserId(String userId) {
        return readingBookMapper.findWithThumbnailByUserId(userId);
    }

    @Override
    public boolean isBookRegisteredByUser(String bookId, User user) {
        return readingBookMapper.countReadingBooksByBookIdAndUser(bookId, user.getId()) > 0;
    }

    @Override
    public int countReadingBooksByBookId(String bookId) {
        return readingBookMapper.countReadingBooksByBookId(bookId);
    }

}
