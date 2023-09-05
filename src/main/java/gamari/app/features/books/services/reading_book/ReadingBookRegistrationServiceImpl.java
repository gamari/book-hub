package gamari.app.features.books.services.reading_book;

import org.springframework.beans.factory.annotation.Autowired;

import gamari.app.features.books.mappers.ReadingBookMapper;
import gamari.app.features.books.models.Book;
import gamari.app.features.users.models.User;

import org.springframework.stereotype.Service;

@Service
public class ReadingBookRegistrationServiceImpl implements ReadingBookRegistrationService {
    @Autowired
    private ReadingBookMapper readingBookMapper;

    @Override
    public void unregisterBook(Book book, User user) {
        readingBookMapper.unregisterBook(book.getId(), user.getId());
    }

}
