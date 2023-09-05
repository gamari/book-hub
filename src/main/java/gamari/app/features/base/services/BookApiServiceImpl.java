package gamari.app.features.base.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gamari.app.features.base.models.GoogleBooksItem;
import gamari.app.features.base.models.GoogleBooksResponse;
import gamari.app.features.books.models.Book;

@Service
public class BookApiServiceImpl {
    private final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=isbn:";

    public Book getBookByIsbn(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + isbn;
        GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class);

        if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
            GoogleBooksItem item = response.getItems().get(0);
            Book book = item.getVolumeInfo().toBook();
            return book;
        }

        return null;
    }

}
