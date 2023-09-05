package gamari.app.features.books.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import gamari.app.features.books.models.ReadingBook;
import gamari.app.features.books.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public ResponseEntity<Map> searchBooks(@RequestParam String keyword) {
        try {
            String googleBooksAPIUrl = "https://www.googleapis.com/books/v1/volumes?q=" + keyword;
            ResponseEntity<Map> response = restTemplate.getForEntity(googleBooksAPIUrl, Map.class);
            return response;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

}
