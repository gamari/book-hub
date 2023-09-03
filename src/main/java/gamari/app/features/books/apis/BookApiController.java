package gamari.app.features.books.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gamari.app.features.books.mappers.BookMapper;
import gamari.app.features.books.models.Book;

@RestController
@RequestMapping("/api/books")
public class BookApiController {
    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        System.out.println("AAAAAAAAAA");
        System.out.println("keyword: " + keyword);
        return bookMapper.findByTitleContaining(keyword);
    }
}
