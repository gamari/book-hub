package gamari.app.features.books.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.ReadingBookMapper;
import gamari.app.features.books.models.Book;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.users.models.User;

@Service
public class ReadingBookService {
    @Autowired
    private ReadingBookMapper readingBookMapper;

    public void unregisterBook(String userId, String bookId) {
        readingBookMapper.unregisterBook(userId, bookId);
    }

    public boolean isBookRegisteredByUser(String bookId, String userId) {
        return readingBookMapper.countReadingBooksByBookIdAndUser(bookId, userId) > 0;
    }

    public int countReadingBooksByBookId(String bookId) {
        return readingBookMapper.countReadingBooksByBookId(bookId);
    }

    public Optional<ReadingBook> findById(String id) {
        return readingBookMapper.findById(id);
    }

    public List<ReadingBook> findByUserId(String userId) {
        return readingBookMapper.findByUserId(userId);
    }

    public void save(ReadingBook newReadingBook) {
        readingBookMapper.insert(newReadingBook);
    }

    public boolean isAlreadyReadingBook(String bookId, String userId) {
        return readingBookMapper.countByBookIdAndUserId(bookId, userId) > 0;
    }

    public void saveReadingBook(User user, Book book) {
        ReadingBook readingBook = new ReadingBook();
        readingBook.setId(UUID.randomUUID().toString());
        readingBook.setTitle(book.getTitle());
        readingBook.setBookId(book.getId());
        readingBook.setUserId(user.getId());
        readingBook.setStartDate(new Date());
        this.save(readingBook);
    }
}
