package gamari.app.features.books.services.reading_book;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import gamari.app.features.books.mappers.ReadingBookMapper;
import gamari.app.features.books.models.Book;
import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.users.models.User;

import org.springframework.stereotype.Service;

@Service
public class ReadingBookRegistrationServiceImpl implements ReadingBookRegistrationService {
    @Autowired
    private ReadingBookMapper readingBookMapper;

    @Override
    public void registerBook(User user, Book book) {
        ReadingBook readingBook = new ReadingBook();
        readingBook.setId(UUID.randomUUID().toString());
        readingBook.setTitle(book.getTitle());
        readingBook.setBookId(book.getId());
        readingBook.setUserId(user.getId());
        readingBook.setStartDate(new Date());
        readingBookMapper.insert(readingBook);
    }

    @Override
    public void unregisterBook(Book book, User user) {
        readingBookMapper.unregisterBook(book.getId(), user.getId());
    }

}
