package gamari.app.features.books.apis;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import gamari.app.features.base.libs.GoogleBooksQueryBuilder;

@RestController
@RequestMapping("/api/books")
public class BookApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${google.token}")
    private String googleToken;

    @GetMapping("/search")
    public ResponseEntity<Map> searchBooks(@RequestParam String keyword, @RequestParam(required = false) Integer page) {
        try {
            // TODO Bookテーブルから取得してもいいかも

            if (page == null || page < 1) {
                page = 1;
            }

            int maxResults = 5; // 1ページあたりの結果数
            int startIndex = (page - 1) * maxResults; // 開始インデックス

            GoogleBooksQueryBuilder queryBuilder = new GoogleBooksQueryBuilder()
                    .addKeyword(keyword)
                    .setMaxResults(maxResults)
                    .setStartIndex(startIndex)
                    .setLangRestrict("ja")
                    .addToken(googleToken)
                    // .setCountry("jp")
                    // .orderByNewest()
                    .orderByRelevance();

            String query = queryBuilder.build();
            String googleBooksAPIUrl = "https://www.googleapis.com/books/v1/volumes" + query;
            System.out.println(googleBooksAPIUrl);

            ResponseEntity<Map> response = restTemplate.getForEntity(googleBooksAPIUrl, Map.class);
            return response;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

}
