package gamari.app.features.books.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamari.app.features.books.mappers.ReadingBookMapper;
import gamari.app.features.books.models.ReadingBook;

@Service
public class ReadingBookService {
    @Autowired
    private ReadingBookMapper readingBookMapper;

    public List<ReadingBook> findByUserId(String userId) {
        return readingBookMapper.findByUserId(userId);
    }

    public void createReadingBook(ReadingBook newReadingBook, String userId) {
        newReadingBook.setUserId(userId);
        newReadingBook.setId(UUID.randomUUID().toString());
        readingBookMapper.insert(newReadingBook);
    }
}
