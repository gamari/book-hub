package gamari.app.features.books.apis;

import java.security.Principal;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gamari.app.features.base.BaseController;
import gamari.app.features.books.models.Book;
import gamari.app.features.books.models.Memo;
import gamari.app.features.books.services.book.BookQueryService;
import gamari.app.features.books.services.memo.MemoService;
import gamari.app.features.books.services.reading_book.ReadingBookRegistrationService;
import gamari.app.features.users.models.User;
import gamari.app.features.users.services.UserService;

@RestController
@RequestMapping("/api/reading-books")
public class ReadingBookApiController extends BaseController {
    @Autowired
    UserService userService;

    @Autowired
    BookQueryService bookQueryService;

    @Autowired
    private MemoService memoService;

    @Autowired
    private ReadingBookRegistrationService readingBookRegistrationService;

    // TODO memoを返したい
    @PostMapping("/{id}/memos")
    public ResponseEntity<Memo> createMemo(@PathVariable String id, @RequestBody Memo memo, Principal principal) {
        User user = this.getUserFromPrincipal(principal);
        System.out.println(user.getId());

        // TODO ReadingBookのユーザーのみ登録可能

        memo.setId(UUID.randomUUID().toString());
        memo.setReadingBookId(id);
        memo.setCreatedAt(new Date());
        memo.setAuthor(user.getId());
        memoService.save(memo);
        return ResponseEntity.ok(memo);
    }

    @PostMapping("/unregister")
    public ResponseEntity<Map<String, Boolean>> unregisterBook(@RequestBody Map<String, String> params,
            Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        String bookId = params.get("bookId");
        Optional<Book> optBook = bookQueryService.findBookById(bookId);

        if (optBook.isPresent()) {
            Book book = optBook.get();
            readingBookRegistrationService.unregisterBook(book, user);
            return ResponseEntity.ok(Map.of("success", true));
        } else {
            // TODO エラー処理
            return ResponseEntity.ok(Map.of("success", false));
        }
    }
}
