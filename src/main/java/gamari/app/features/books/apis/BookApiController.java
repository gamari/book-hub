package gamari.app.features.books.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/books")
public class BookApiController {
    @Autowired
    private RestTemplate restTemplate;

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
