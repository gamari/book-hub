package gamari.app.features.books.services;

import gamari.app.features.books.models.Book;
import gamari.app.features.users.models.User;

public interface ReadingBookRegistrationService {
    // void registerBook(User user, Book book);
    void unregisterBook(Book book, User user);
}
