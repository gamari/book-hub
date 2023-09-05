package gamari.app.features.base.services;

import gamari.app.features.books.models.Book;

public interface BookApiService {
    public Book fetchBookByIsbn(String isbn);
}
