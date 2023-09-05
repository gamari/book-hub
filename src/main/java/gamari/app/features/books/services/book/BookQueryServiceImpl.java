package gamari.app.features.books.services.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.BookMapper;
import gamari.app.features.books.models.Book;

@Service
public class BookQueryServiceImpl implements BookQueryService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Optional<Book> findBookById(String id) {
        return bookMapper.findBookById(id);
    }
}
